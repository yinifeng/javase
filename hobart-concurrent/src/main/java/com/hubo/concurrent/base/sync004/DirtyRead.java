package com.hubo.concurrent.base.sync004;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 * 脏读
 * @author hubo
 *
 */
public class DirtyRead {
    private String username="hubo";
    private String password="123";
    
    public synchronized void setValue(String username,String password){
        this.username=username;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.password=password;
        System.out.println("setValue最终结果：username = "+this.username+", password= "+this.password);
    }
    
    public void getValue(){
        System.out.println("getValue方法得到：username= "+this.username+", password ="+this.password);
    }

    public static void main(String[] args) throws InterruptedException {
        final DirtyRead dr= new DirtyRead();
        //开启的线程
        Thread t1= new Thread(()->dr.setValue("hobart", "456"));
        t1.start();
        
        Thread.sleep(1000);
        //主线程
        dr.getValue();
    }
}
