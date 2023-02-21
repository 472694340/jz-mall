package com.jz.mall;


import java.util.Random;


public class MathDemo {
    public static void main(String[] args) {
        int num = (int) Math.random();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            System.out.println(random.nextInt(10));
        }

    }
//
}
