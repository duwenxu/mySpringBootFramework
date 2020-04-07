package com.example.springboot.summary.java容器总结;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Map测试类
 *
 * @author duwenxu
 * @create 2020-03-20 11:37
 */
public class MapTest {


    public static void main(String[] args) {
        HashMap map = new HashMap<>(15);
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>(100);
        for (int i = 0;i<100;i++){
            map.put(i,i++);
        }
        map.put(null, null);
//        concurrentHashMap.put(null, null);

        ArrayList masterList = new ArrayList<String>();
        masterList.add("one");
        masterList.add("two");
        masterList.add("three");
        masterList.add("four");

        List subList = masterList.subList(0, 2);

        HashMap capaMap = new HashMap<>(15);

        System.out.println(1<<31);
    }
}
