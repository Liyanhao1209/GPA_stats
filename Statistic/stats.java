package org.Statistic;

import org.Entity.StuScore;
import org.Exception.StuNotFoundException;
import org.Strategy.Strategy;

import java.util.*;

import static org.Constant.ExceptionConstant.StuNotFoundMsg;

public class stats implements Statistic{
    @Override
    public Map leastOneSuperior(List<Map<String,Double>> stuScores, String stuNum) {
        return null;
    }

    @Override
    public Map allSuperior(List<Map<String,Double>> stuScores, String stuNum) {
        return null;
    }

    /**
     * @param stuScores 每个学年提交的成绩表格
     * @param stuNum 指定学生学号
     * @param strategy 计算策略
     * @return 统计结果
     */
    @Override
    public Map<String,List<Double>> comprehensiveSuperior(List<Map<String,Double>> stuScores, String stuNum, Strategy strategy)  {
        Map<String,List<Double>> res = new HashMap<>();
        List<String> intersection = numKeyToList(stuScores.get(0));
        //获取指定学生综合绩点
        List<Double> speStuSc = new ArrayList<>();
        //统计所有学年都有成绩信息的学生
        stuScores.forEach(
                termScore->{
                    try {
                        //确保有指定学生的成绩
                        Double score = termScore.get(stuNum);
                        if(score ==null){
                            throw new StuNotFoundException(StuNotFoundMsg);
                        }
                        speStuSc.add(score);
                    } catch (StuNotFoundException e) {
                        e.printStackTrace();
                    }
                    //该学年成绩单
                    List<String> nums = numKeyToList(termScore);
                    intersection.retainAll(nums); //求交
                }
        );
        //计算得到该学生综合绩点
        double speGPA = strategy.calculateStrategy(speStuSc.toArray(new Double[0]));
        //查询交集中优于当前学生的所有学生
        intersection.forEach(
                stu->{
                    Double[] specScore = getStuScores(stu, stuScores);
                    if(Double.compare(speGPA,strategy.calculateStrategy(specScore))<=0){
                        res.put(stu,Arrays.asList(specScore));
                    }
                }
        );
        return res;
    }

    /**
     * 统计全排名
     * @param stuScores 学生各学年成绩
     * @param strategy 计算策略
     * @return 全排名
     */
    @Override
    public List<StuScore> rank(List<Map<String, Double>> stuScores, Strategy strategy) {
        List<StuScore> ans = new ArrayList<>();
        List<String> intersection = intersection(stuScores);
        intersection.forEach(
                stu->{
                    Double[] scores = getStuScores(stu, stuScores);
                    double avg = strategy.calculateStrategy(scores);
                    ans.add(
                            new StuScore(stu,avg,scores)
                    );
                }
        );
        Collections.sort(ans);
        return ans;
    }

    @Override
    public List<StuScore> majorTrans(List<Map<String, Double>> stuScores, Strategy strategy) {
        List<String> mTrans = difference(stuScores);
        stuScores.remove(0);
        List<StuScore> ans = new ArrayList<>();
        mTrans.forEach(
                stu->{
                    Double[] scores = getStuScores(stu, stuScores);
                    double avg = strategy.calculateStrategy(scores);
                    ans.add(
                            new StuScore(stu,avg,scores)
                    );
                }
        );
        Collections.sort(ans);
        return ans;
    }

    private List<String> difference(List<Map<String,Double>> stuScores){
        List<String> diff = numKeyToList(stuScores.get(0));
        for (int i = 1; i < stuScores.size(); i++) {
            Map<String, Double> termScore = stuScores.get(i);
            List<String> nums = numKeyToList(termScore);
            nums.removeAll(diff);
            diff=nums;
        }
        return diff;
    }

    private List<String> intersection(List<Map<String,Double>> stuScores){
        List<String> intersection = numKeyToList(stuScores.get(0));
        stuScores.forEach(
                termScore->{
                    //该学年成绩单
                    List<String> nums = numKeyToList(termScore);
                    intersection.retainAll(nums); //求交
                }
        );
        return intersection;
    }


    private List<String> numKeyToList(Map<String,Double> m){
        return new ArrayList<>(m.keySet());
    }

    /**
     * 统计一个指定学生的所有成绩
     * @param stu 指定学生
     * @param m 各个学年成绩表
     * @return 该学生成绩
     */
    private Double[] getStuScores(String stu,List<Map<String,Double>> m){
        List<Double> ans = new ArrayList<>();
        m.forEach(
                termScore->{
                    Double score = termScore.get(stu);
                    try {
                        if(score==null){
                            throw new StuNotFoundException(StuNotFoundMsg);
                        }
                        ans.add(score);
                    } catch (StuNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        );
        return ans.toArray(new Double[0]);
    }
}
