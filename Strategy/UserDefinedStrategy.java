package org.Strategy;

import java.util.Scanner;

import static org.Util.SingletonScanner.getSingletonScanner;

public class UserDefinedStrategy implements Strategy{
    private static double[] credits;

    /**
     * 根据用户自定义的学分数计算平均学分绩点
     * @param specifiedStuScores 当前学生所有指定学年的平均学分绩点
     * @return 指定学生指定学年内的综合平均学分绩点
     */
    @Override
    public synchronized double calculateStrategy(Double[] specifiedStuScores) {
        if(credits==null){
            credits = new double[specifiedStuScores.length];
            Scanner singletonScanner = getSingletonScanner();
            System.out.println("请按顺序输入你各个学年的学分(必修+限选,其他的不算)");
            for (int i = 0; i < credits.length; i++) {
                credits[i] = singletonScanner.nextDouble();
            }
        }
        double ans =0.0;
        double totalCredits = 0.0;
        for (int i = 0; i < specifiedStuScores.length; i++) {
            ans += credits[i]*specifiedStuScores[i];
            totalCredits += credits[i];
        }
        return totalCredits>0?ans/totalCredits:0;
    }
}
