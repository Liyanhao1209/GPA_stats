package org.Entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class StuScore implements Comparable<StuScore>{
    @ExcelProperty("学号")
    private String stuNum;
    @ExcelProperty("平均学分绩点")
    private Double averageGPA;
    @ExcelIgnore
    private Double[] scores;
    @ExcelProperty("大一平均学分绩点")
    private double freshman;
    @ExcelProperty("大二平均学分绩点")
    private double sophomore;
    @ExcelProperty("大三平均学分绩点")
    private double junior;

    public StuScore(String stuNum, Double averageGPA, Double[] scores){
        this.scores = scores;
        this.averageGPA = averageGPA;
        this.stuNum = stuNum;
        try {
            freshman = scores[0];
            sophomore = scores[1];
            junior = scores[2];
        } catch (ArrayIndexOutOfBoundsException ignored){

        }
    }
    @Override
    public int compareTo(StuScore o) {
        return -averageGPA.compareTo(o.averageGPA);
    }
}
