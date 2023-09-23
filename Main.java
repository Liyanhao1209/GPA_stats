package org;

import org.Statistic.stats;
import org.Strategy.UserDefinedStrategy;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.Util.ExcelUtils.adaptScores;
import static org.Util.ExcelUtils.readExcel;
import static org.Util.SingletonScanner.getSingletonScanner;

public class Main {
    public static void main(String[] args) {
        Scanner singletonScanner = getSingletonScanner();
        System.out.println("输入文件夹路径(存放有各个学年xls文件的文件夹)");
        String dirPath = singletonScanner.next();
        File dir = new File(dirPath);
        List<File> fileList = new ArrayList<>();
        if(dir.isDirectory()){
            File[] subFiles = dir.listFiles();
            if (subFiles != null) {
                for (File subFile : subFiles) {
                    if(!subFile.isDirectory()){
                        fileList.add(subFile);
                    }
                }
            }
        }else{
            System.out.println("文件夹路径错误");
            return;
        }
        //获取各学年成绩
        List<Map<String,Double>> data = new ArrayList<>();
        fileList.forEach(
                file -> {
                    try {
                        InputStream in = Files.newInputStream(file.toPath());
                        List<Map<Integer, String>> list = readExcel(in);
                        data.add(adaptScores(list));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        //获取学生学号
        System.out.println("输入你想查询的学号");
        String stuNum = singletonScanner.next();
        stats stats = new stats();
        Map<String, List<Double>> comprehensiveSuperior = stats.comprehensiveSuperior(data, stuNum,
                new UserDefinedStrategy());
        //大一上：5+3+1+2+1+4+2
        //大一下：5+4+4+3+1+3+3+2.5+2+2+2
        //大一合计：49.5
        //大二上：1+3+3+4.5+2+4.5+1+2
        //大二下：3+1+4.5+2+2.5+2+4.5+2.5+3+3+2
        //大二合计：51
        System.out.println(1);
    }
}
