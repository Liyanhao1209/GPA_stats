package org.Entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class StuScore {
    @ExcelProperty("学号")
    private String stuNum;
    @ExcelProperty("平均学分绩点")
    private Double averageGPA;
}
