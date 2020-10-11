package com.example.springboot.common;

import java.util.ArrayList;

/**
 * test
 *
 * @author duwenxu
 * @create 2019-10-31 15:49
 */
public class Test {

    //    https://zhidao.baidu.com/question/756326573815936724.html
    public static void main(String[] args) {
//        String a="abcd";
//        String b="ab"+new String("cd");
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(a==b);


        ArrayList<String> colliction = new ArrayList<>();
        colliction.add("string0");
        colliction.add("string1");
        colliction.add("string2");


        String[] strings = colliction.toArray(new String[0]);
        for (String s :
                strings) {
            System.out.println(s);
        }
    }


}

