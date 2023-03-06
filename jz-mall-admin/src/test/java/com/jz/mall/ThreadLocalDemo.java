package com.jz.mall;

public class ThreadLocalDemo {
    private String message;
    private static final ThreadLocal<ThreadLocalDemo> threadLocal = ThreadLocal.withInitial(ThreadLocalDemo::new);//所有线程共享一个threadLocal?
    public static void main(String[] args) {


        Thread thread01 = new Thread(()->{
            System.out.println(threadLocal.get().getMessage());
            threadLocal.get().add("this is thread01");
            System.out.println( threadLocal.get().getMessage());
        });
        Thread thread02 = new Thread(()->{
            System.out.println(threadLocal.get().getMessage());
            threadLocal.get().add("this is thread02");
            System.out.println(threadLocal.get().getMessage());
            threadLocal.get().getMessage();
            System.out.println(threadLocal.get().getMessage());
        });
        thread01.run();
        thread02.run();
        System.out.println("main: " + threadLocal.get().getMessage()); //main: this is thread02 two

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    private static void add(String message){
       threadLocal.get().setMessage(message);
    }
}
