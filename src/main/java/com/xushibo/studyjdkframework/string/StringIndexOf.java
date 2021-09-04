package com.xushibo.studyjdkframework.string;

public class StringIndexOf {

    public static void main(String[] args){

        String s = "abcdefgcd";
        int index = s.indexOf("cd");
        System.out.println(index);

        int lastIndex = s.lastIndexOf("cd");
        System.out.println(lastIndex);
    }

}
