package org.Util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.Listener.ExcelListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExcelUtils {

    public static List<Map<Integer,String>> readExcel(InputStream inputStream){
        ExcelListener excelListener = new ExcelListener();
        ExcelReader excelReader = null;
        try{
            excelReader = EasyExcel.read(inputStream,excelListener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
            List<Map<Integer,String>> excelList =excelListener.getExcelList();
            return excelList;
        }catch (Exception e){
            throw new RuntimeException("文件错误");
        }finally {
            if(excelReader!=null){
                excelReader.finish();
            }
        }
    }

    public static Map<String,Double> adaptScores(List<Map<Integer,String>> list){
        Map<String,Double> res = new HashMap<>();
        list.forEach(
                m->{
                    res.put(
                            m.get(0),
                            Double.parseDouble(m.get(1))
                    );
                }
        );
        return res;
    }

//    public static void main(String[] args) throws IOException {
//        InputStream in = Files.newInputStream(new File("C:\\Users\\Administrator\\Desktop\\2022-2023年度奖学金排名.xls").toPath());
//        List<Map<Integer,String>> list = readExcel(in);
//        Map<String, Double> stringDoubleMap = adaptScores(list);
//    }
}
