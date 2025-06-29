package com.hubo.concurrent.test;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * 在我经历的几个真实项目中，“异步状态回查”是永恒的痛点。
 *
 * 你一定遇到过这种场景
 * 系统 A 调用系统 B 请求处理任务，系统 B 响应“处理中”，需要系统 A 后续自行回查状态。问题来了：
 *
 * • 怎么优雅地做轮询，而不是 Thread.sleep？
 * • 怎么设置重试上限、间隔、超时？
 * • 怎么在轮询过程中不阻塞线程，还能做补偿？
 *
 * 答案是：把回查封装成一个递归触发的异步流，再借助 ScheduledExecutor 实现延时调度，既非阻塞、又可控
 *
 * 常用轮训逻辑：查询业务状态，睡眠几秒，再查询业务状态，睡眠几秒
 * 轮训逻辑：CompletableFuture + ScheduledExecutor
 *
 * 这是一种系统的“声明式异步治理方式”，它不仅提升代码质量，还提升架构表达力。
 */
public class AsyncPolling {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);


    public static void main(String[] args) {
        String invoiceReqId = "abc";
        //定义查询状态函数
        Supplier<CompletableFuture<InvoiceStatus>> statusSupplier = () -> CompletableFuture.supplyAsync(() -> AsyncPolling.queryStatus(invoiceReqId));
        //定义成功条件
        Predicate<InvoiceStatus> isDone = status ->
         status !=null && ("SUCCESS".equals(status.getCode()) ||"FAILED".equals(status.getCode()));

        //调用轮训
        AsyncPolling polling = new AsyncPolling();
        //你可以继续在 .thenAccept() 或 .whenComplete() 中处理最终结果。
        CompletableFuture<InvoiceStatus> resultFuture = polling.pollAsync(
                    statusSupplier,
                    isDone,
                    5,                        // 最多尝试10次
                    Duration.ofSeconds(3),              // 初始间隔3秒
                    attempt -> Duration.ofSeconds(3),   // 固定间隔策略（可用指数退避）
                        //attempt -> Duration.ofSeconds((long) Math.pow(2, attempt))  // 2^n 秒
                    error -> {
                        System.err.println("轮询失败：" + error.getMessage());
                        error.printStackTrace();
                        //也可以实现一个失败回调来触发报警或补偿：
                        //执行失败回调补偿处理
                    }
        );
    }

    /**
     * 根据业务主键查询出来状态
     *
     * @param invoiceReqId 提交的异步任务关联的业务主键id
     * @return
     */
    private static InvoiceStatus queryStatus(String invoiceReqId) {
        return null;
    }

    public <T> CompletableFuture<T> pollAsync(
            Supplier<CompletableFuture<T>> taskSupplier,
            Predicate<T> successCondition,
            int maxRetries,
            Duration interval,
            Function<Integer, Duration> backoffStrategy,
            Consumer<Throwable> onFailure) {

        CompletableFuture<T> resultFuture = new CompletableFuture<>();
        doPoll(taskSupplier, successCondition, maxRetries, interval, 1, resultFuture, backoffStrategy, onFailure);
        return resultFuture;
    }

    private <T> void doPoll(
            Supplier<CompletableFuture<T>> taskSupplier,
            Predicate<T> successCondition,
            int maxRetries,
            Duration interval,
            int attempt,
            CompletableFuture<T> resultFuture,
            Function<Integer, Duration> backoffStrategy,
            Consumer<Throwable> onFailure) {

        taskSupplier.get().whenComplete((result, error) -> {
            if (error != null) {
                resultFuture.completeExceptionally(error);
                return;
            }

            if (successCondition.test(result)) {
                resultFuture.complete(result);
            } else if (attempt >= maxRetries) {
                resultFuture.completeExceptionally(new RuntimeException("Max retry exceeded"));
                onFailure.accept(new RuntimeException("Failed after " + attempt + " attempts"));
            } else {
                Duration nextInterval = backoffStrategy.apply(attempt);
                scheduler.schedule(() ->
                        doPoll(taskSupplier, successCondition, maxRetries, interval, attempt + 1,
                                resultFuture, backoffStrategy, onFailure), nextInterval.toMillis(), TimeUnit.MILLISECONDS);
            }
        });
    }


    public static enum InvoiceStatus {
        RUNNING("RUNNING","执行中"),
        SUCCESS("SUCCESS","成功"),
        FAILED("FAILED","失败"),

        ;

        private final String code;

        private final String desc;

        InvoiceStatus(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }
}
