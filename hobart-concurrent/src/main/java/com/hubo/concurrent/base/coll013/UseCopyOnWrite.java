package com.hubo.concurrent.base.coll013;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class UseCopyOnWrite {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> cwa1=new CopyOnWriteArrayList<>();
        CopyOnWriteArraySet<String> cwa2=new CopyOnWriteArraySet<>();
    }
}
