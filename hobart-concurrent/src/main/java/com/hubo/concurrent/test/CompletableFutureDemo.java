package com.hubo.concurrent.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureDemo {

    private static final List<NetMall> NET_MALLS = Arrays.asList(new NetMall("jd"),
            new NetMall("taobao"),new NetMall("dangdang")
            );

    public static void main(String[] args) throws Exception {
        //创建 CompletableFuture
        //testCreateCompletableFuture();

        //CompletableFuture 运行任务
        //testRunCompletableFuture();

        //测试计算价格
        //testGetPrice();

        //并行计算价格
        //testGetPriceByCompletableFuture();

        //任务结果 传递 串行执行
        //testHandleCompletableFuture();

        //任务结果 传递 消费型执行
        //testConsumerCompletableFuture();

        //线程池选择原理
        //testThreadPoolCompletableFuture();

        //获取2名游戏玩家 中的冠军
        //testFastCompletableFuture();

        //合并计算结果
        testCombineCompletableFuture();
    }


    private static void testCombineCompletableFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("future1计算结果");
            return 20;
        }, executorService);

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("future2计算结果");
            return 30;
        }, executorService);

        //合并任务计算结果
        CompletableFuture<Integer> completableFuture = future1.thenCombine(future2, (f1, f2) -> {
            print("合并f1和f2的计算结果");
            return f1 + f2;
        });

        print(completableFuture.join());
        executorService.shutdown();
    }

    /**
     * 获取最快的任务
     */
    private static void testFastCompletableFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<String> playerA = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("A玩家开始玩游戏");
            return "playerA";
        }, executorService);

        CompletableFuture<String> playerB = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("B玩家开始玩游戏");
            return "playerB";
        }, executorService);

        CompletableFuture<String> completableFuture = playerA.applyToEither(playerB, (result) -> {
            print("获取游戏冠军");
            return result + "is winner";
        });

        print(completableFuture.join());
        executorService.shutdown();
    }

    /**
     *
     * 线程池选择原理
     *
     * 1、没有传入自定义线程池，都是用默认线程池ForkJoinPool
     *
     * 2、传入了一个自定义线程池
     * 如果你执行第一个任务的时候，传入了一个自定义线程池：
     *
     * 调用thenRun方法执行第二个任务时，则第二个任务和第一个任务是共用同一个线程池
     * 调用thenRunAsync执行第二个任务时，则第一个任务使用的你自己传入的线程池，第二个任务使用的是ForkJoin线程池
     *
     * 3、备注
     * 有可能处理太快，系统优先切换原则，直接使用main线程池处理
     *
     * 其他如：thenAccept和thenAcceptAsync，thenApply和thenApplyAsync等，它们之间的区别也是同理
     */
    private static void testThreadPoolCompletableFuture() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("1号任务");
            return "abc";
        }, executorService).thenRunAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("2号任务");
        }, executorService).thenRunAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("3号任务");
        }, executorService).thenRunAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("4号任务");
        }, executorService);

        print(completableFuture.get(3,TimeUnit.SECONDS));
        executorService.shutdown();
    }

    /**
     * 任务计算结果进行消费 传递 串行执行 无结果
     * 线程执行 结果 1 传递 到下一个thenAccept （Consumer）的消费型函数函数中执行，是在同一线程中
     *   thenRun 任务A执行完了 执行任务B，任务B不需要A的结果
     *   thenAccept 任务A执行完了 执行任务B，任务B需要A的结果，但是任务B无返回值
     *   thenApply 任务A执行完了 执行任务B，任务B需要A的结果，但是任务B有返回值
     *
     *
     */
    private static void testConsumerCompletableFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("获取结果111");
            return 1;
        },executorService).thenApply((r)->{
            print("同一线程中获取结果222 = " + r);
            //当这抛出异常，不会执行后续 thenApply中的函数
            return r + 2;
        }).thenApply((r)->{
            print("同一线程中获取结果333 = " + r);
            return r + 3;
        }).thenAccept((r)->{
            //消费型无返回结果
            print("执行结果 = " + r);
        });

        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("任务A执行");
            return "A";
        },executorService).thenRun(()->{
            print("同一线程任务B执行");
        }).thenRunAsync(()->{
            print("任务C执行");
        },executorService);

        print("---main线程处理其他任务...");
        executorService.shutdown();
    }


    /**
     * 任务计算结果进行处理 传递 串行执行 有返回结果
     * 线程执行 结果 1 传递 到下一个thenApply （Function）的函数中执行，是在同一线程中
     *
     */
    private static void testHandleCompletableFuture() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("获取结果111");
            return 1;
        },executorService).thenApply((r)->{
            print("同一线程中获取结果222 = " + r);
            //当这抛出异常，不会执行后续 thenApply中的函数
            int i = 1 / 0;
            return r + 2;
        }).thenApply((r)->{
            print("同一线程中获取结果333 = " + r);
            return r + 3;
        }).whenComplete((result,exception)->{
            if (exception == null) {
                print("同一线程中最终执行结果 = " + result);
            }
        }).exceptionally((exception)->{
            exception.printStackTrace();
            return null;
        });


        //handle 抛出异常 后续的 handle也会执行
        CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print("获取结果111");
            return 1;
        },executorService).handle((r,e)->{
            print("同一线程中获取结果222 = " + r);
            //当这抛出异常，不会执行后续 thenApply中的函数
            int i = 1 / 0;
            return r + 2;
        }).handle((r,e)->{
            print("同一线程中获取结果333 = " + r);
            return r + 3;
        }).whenComplete((result,exception)->{
            if (exception == null) {
                print("同一线程中最终执行结果 = " + result);
            }
        }).exceptionally((exception)->{
            exception.printStackTrace();
            return null;
        });


        print("---main线程处理其他任务...");
        executorService.shutdown();
    }


    /**
     * 模拟从 jd taobao dangdang 获取书的价格
     */
    private static void testGetPriceByCompletableFuture() {
        long start = System.currentTimeMillis();
        String productName = "mysql";
        List<String> results = NET_MALLS.stream()
                .map(item ->
                        CompletableFuture.supplyAsync(
                                ()-> String.format("<<%s>> in %s price is %.2f",
                                                productName, item.getNetMallName(), item.calacPrice(productName))))
                .collect(Collectors.toList())
                .stream().map(CompletableFuture::join)
                .collect(Collectors.toList());


        results.forEach(System.out::println);
        System.out.printf("------CostTime:%.3f秒\n",(System.currentTimeMillis()-start) / 1000.0);

    }

    /**
     * 模拟从 jd taobao dangdang 获取书的价格
     */
    private static void testGetPrice() {
        long start = System.currentTimeMillis();
        String productName = "mysql";
        List<String> results = NET_MALLS.stream()
                .map(item -> String.format("<<%s>> in %s price is %.2f",
                        productName, item.getNetMallName(), item.calacPrice(productName)))
                .collect(Collectors.toList());
        results.forEach(System.out::println);
        System.out.printf("------CostTime:%.3f秒\n",(System.currentTimeMillis()-start) / 1000.0);

    }


    /**
     * 运行 CompletableFuture 示例
     * @throws Exception
     */
    private static void testRunCompletableFuture()  throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try {
            CompletableFuture.supplyAsync(()->{
                CompletableFutureDemo.print("----come in");
                int nextInt = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CompletableFutureDemo.print("-----1秒钟后出结果="+nextInt);
                if (nextInt > 5) {
                    int i = nextInt / 0;
                }
                return nextInt;
            },executorService).whenComplete((result,throwable)->{
                //任务处理完成 执行这个回调
                if (throwable == null) {
                    CompletableFutureDemo.print("-----计算完成，更新系统updateValue="+result);
                }else {
                    //有异常还是会执行
                    CompletableFutureDemo.print("-----计算完成，出现异常="+throwable.getMessage());
                }
            }).exceptionally((throwable)->{
                //只有抛出异常才会 回调这个任务
                //运行任务抛出异常
                throwable.printStackTrace();
                CompletableFutureDemo.print("-----异常情况，exception="+throwable.getMessage());
                return null;
            });
            CompletableFutureDemo.print("主线程先去忙其他的任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }


    /**
     * 创建 CompletableFuture 示例
     * @throws Exception
     */
    private static void testCreateCompletableFuture() throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //创建CompletableFuture，没有指定线程池默认为ForkJoinPool

        //1、无返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行完成");
        }, executorService);
        //join 和get类似 等待获取结果，不会抛出异常
        //以下为4中获取CompletableFuture 任务处理结果：
        //1、阻塞获取结果（直到任务处理完成），不会显示抛出异常的获取结果
        //completableFuture1.join();
        //2、阻塞获取结果（直到任务处理完成），显示抛出异常的获取结果
        //completableFuture1.get()
        //3、等待某个时间获取结果，否则抛出TimeoutException，抛出异常也不会打断 completableFuture1中任务继续执行
        //completableFuture1.get(1,TimeUnit.SECONDS)
        //4、立即获取结果，如果任务还没处理完成 立即返回 参数传入的结果，否则任务处理完成 返回任务完成的结果
        //completableFuture1.getNow(null)
        try {
            //指定时间类获取结果 否则抛出  TimeoutException,并不会打断 completableFuture1中的任务执行
            completableFuture1.get(1,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //completableFuture1.getNow(null);
        print("completableFuture1 等待获取完成");
        //print(completableFuture1.get());

        //2、有返回值；常用
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello supplyAsync";
        }, executorService);

        //completableFuture.

        print("main处理其他任务");
        executorService.shutdown();
    }

    private static void print(Object obj) {
        System.out.println(String.format("[%s]:%s",Thread.currentThread().getName(),(obj == null ? "null" : obj.toString())));
    }


    private static class NetMall{
        private final String netMallName;

        public NetMall(String netMallName) {
            this.netMallName = netMallName;
        }

        public double calacPrice(String productName) {
            //1秒钟获取结果
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
        }

        public String getNetMallName() {
            return netMallName;
        }
    }
}
