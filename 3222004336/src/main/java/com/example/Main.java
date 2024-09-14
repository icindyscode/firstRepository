package com.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;


public class Main {
    public static void main(String[] args) {
        //从命令行获取参数
        String orig = args[0];
        String origDis = args[1];
        String resultFile = args[2];
        String str = "";
        String str2 = "";

        //从文件中读取原文
        try {
            //InputStreamReader reader = new InputStreamReader(new FileInputStream("E:\\Maven\\repository\\firstRepository\\3222004336\\src\\main\\resources\\orig.txt"), "UTF-8");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(orig), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                str+=line;
            }
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
        }

        //从文件中读取抄袭文章
        try {
            //InputStreamReader reader = new InputStreamReader(new FileInputStream("E:\\Maven\\repository\\firstRepository\\3222004336\\src\\main\\resources\\orig_0.8_dis_15.txt"), "UTF-8");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(origDis), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                str2+=line;
            }
        } catch (IOException e) {
            e.printStackTrace(); // 处理异常
        }

        //创建实例对象
        Compute myCompute = new Compute();

        //计算查重率并且结果保留两位小数
        double result = myCompute.calculateCosSimilarity(str, str2);
        String result1 = String.format("%.2f", result);

        //输出原文的分词以及查重率
        System.out.println("分词数据（ansj分词法）：" + myCompute.participleNlp(str));
        System.out.println("重复率：" + result1 + "是否重复（默认阈值0.7）：" + myCompute.ifPlagiarism(str, str2));

        //将查重率写入文件
        try {
            FileWriter writer = new FileWriter(resultFile);
            writer.write(result1);
            writer.close();
            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}