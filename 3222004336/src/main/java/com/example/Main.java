package com.example;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
public class Main {
    public static void main(String[] args) {
        String str = "";
        String str2 = "";
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream("E:\\Maven\\repository\\demo\\src\\main\\resources\\orig.txt"), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
            	// 此时输出应为正确中文
                str+=line;
            }
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
        }

        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream("E:\\Maven\\repository\\demo\\src\\main\\resources\\orig_0.8_add.txt"), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
            	// 此时输出应为正确中文
                str2+=line;
            }
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
        }
     
        Compute myCompute = new Compute();

        System.out.println("分词数据（ansj分词法）：" + myCompute.participleNlp(str));

        System.out.println("重复率：" + myCompute.calculateCosSimilarity(str, str2) + "是否重复（默认阈值0.7）：" + myCompute.ifPlagiarism(str, str2));
    }

}