package com.hubo.java.fanxing;

public class NotifyImpl implements Notify<String,String>{

    @Override
    public String send(Builder<String> builder) {
        String param = builder.build();
        

        return "result";
    }
}
