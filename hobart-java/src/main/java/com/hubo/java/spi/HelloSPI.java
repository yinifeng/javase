package com.hubo.java.spi;

public class HelloSPI {
    protected String getName(){
        return "defalut";
    }
   
    protected void println() {
        System.out.println(this.getName() + " Say Hello!!!");
    }
}
