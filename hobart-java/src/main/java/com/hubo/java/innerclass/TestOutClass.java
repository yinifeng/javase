package com.hubo.java.innerclass;

public class TestOutClass {

    public static void main(String[] args) {
        //并不会加载类部类
        OutClass outClass = new OutClass();

        //outClass.StaticInnerClass staticInnerClass = new outClass.StaticInnerClass();
        //outClass.InnerClass innerClass = new outClass.InnerClass();
        OutClass.InnerClass ic;
        OutClass.StaticInnerClass sic;

        OutClass.InnerType i1=OutClass.InnerType.Inner1;
    }
}
