package com.jz.mall;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        //1.ClassLoaderDemo's ClassLoader is: sun.misc.Launcher$AppClassLoader@18b4aac2  应用程序类加载器
        System.out.println("ClassLoaderDemo's ClassLoader is: " + ClassLoaderDemo.class.getClassLoader());

        //2.The Parent of ClassLoaderDemo's ClassLoader is: sun.misc.Launcher$ExtClassLoader@42110406 扩展类加载器
        System.out.println("The Parent of ClassLoaderDemo's ClassLoader is: " + ClassLoaderDemo.class.getClassLoader().getParent());

        //3.The GrandParent of ClassLoaderDemo's ClassLoader is: null BootstrapClassLoader 顶层类加载器
        System.out.println("The GrandParent of ClassLoaderDemo's ClassLoader is: " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
    }
}
