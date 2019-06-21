package com.hobart.guava.test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import java.util.List;

public class StringDemo {
    public static void main(String[] args) {
        String str1 = Joiner.on(",").skipNulls().join("aaa",null, "bbb");
        System.out.println(str1);
        System.out.println("==================");

        List<String> list = Splitter.on("|").splitToList("AAA|BBB|CCC");
        System.out.println(list);
        System.out.println("=====================");
        String dsfs = Strings.emptyToNull("dsfs");
        
        
    }
}
