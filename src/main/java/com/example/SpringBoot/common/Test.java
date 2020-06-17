package com.example.springboot.common;

/**
 * test
 *
 * @author duwenxu
 * @create 2019-10-31 15:49
 */
public class Test {

//    https://zhidao.baidu.com/question/756326573815936724.html
    public static void main(String[] args) {
        String a="abcd";
        String b="ab"+new String("cd");
        System.out.println(a);
        System.out.println(b);
        System.out.println(a==b);
    }


}

