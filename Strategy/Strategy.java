package org.Strategy;

/**
 * 计算评分标准的策略
 */
public interface Strategy {
    double calculateStrategy(Double[] specifiedStuScores);
}
