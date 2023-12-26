package com.HongShen.test;

import lombok.Data;

import java.io.File;

@Data
public class filetest {
    String path = "D:\\PROJECT\\emils-system\\emils-server\\src\\main\\resources\\template\\admin\\template1.html";
    File file = new File(path);

    public void printFileName() {
        String fileName = file.getName();
        System.out.println(fileName);
    }

    public static void main(String[] args) {
        filetest test = new filetest();
        test.printFileName();
    }
}