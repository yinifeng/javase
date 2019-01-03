package com.hubo.java.fanxing;

import java.util.Arrays;

public class Test {
    private static Notify<String, String[]> server;

    static {
        server = builder -> {
            String[] array = builder.build();
            System.out.println(Arrays.toString(array));
            return "sucess";
        };
    }

    public static void main(String[] args) {
        String result = server.send(() -> {
            return new String[]{"AAA", "BBB"};
        });
        System.out.println("result=" + result);
    }
}
