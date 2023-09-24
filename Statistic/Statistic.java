package org.Statistic;

import org.Exception.StuNotFoundException;
import org.Strategy.Strategy;

import java.util.List;
import java.util.Map;

/**
 * 统计至少一个学年比当前学生绩点高的学生
 * 统计所有学年均比当前学生绩点高的学生
 * 根据指定策略统计综合绩点比当前学生高的学生
 *
 * 全部都是直接修改入参
 */
public interface Statistic {
    Map leastOneSuperior(List<Map<String,Double>> stuScores,String stuNum);
    Map allSuperior(List<Map<String,Double>> stuScores,String stuNum);
    Map comprehensiveSuperior(List<Map<String,Double>> stuScores, String stuNum, Strategy strategy);
    List rank(List<Map<String,Double>> stuScores,Strategy strategy);
    List majorTrans(List<Map<String,Double>> stuScores,Strategy strategy);
}
