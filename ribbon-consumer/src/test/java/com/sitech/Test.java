package com.sitech;

import java.util.*;

public class Test {

    public static void main(String[] args) {


        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();

        Map<String,Object> a = new HashMap<String,Object>();

        a.put("a","a");

        list.add(a);

        Map<String,Object> b = new HashMap<String,Object>();

        b.put("b","b");

        list.add(b);

        System.out.println(list.size() + "---------");
        System.out.println(list);



    }

} 