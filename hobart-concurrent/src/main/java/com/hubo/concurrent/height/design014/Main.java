package com.hubo.concurrent.height.design014;

public class Main {
    public static void main(String[] args) {
        FutureClient client=new FutureClient();
        Data data=client.request("请求参数");
        System.out.println("请求发送成功...");
        System.out.println("做其他事情...");

        String result = data.getRequest();
        System.out.println("结果====>"+result);
    }
}
