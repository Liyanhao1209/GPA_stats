package org.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class ExcelListener extends AnalysisEventListener<Map<Integer,String>>{

    private List<Map<Integer,String>> excelList =new ArrayList<Map<Integer,String>>();
    private Map<Integer,String> headMap = new HashMap<Integer, String>();
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {
        excelList.add(data);

    }
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        this.headMap=headMap;
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("已解析");

    }
    public List<Map<Integer,String>> getExcelList(){
        return excelList;
    }
}

