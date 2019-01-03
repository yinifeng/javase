package com.hubo.java.fanxing;

public interface Notify<R,P> {
    
    R send(Builder<P> builder);
}
