package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.RecommendAnswer;
import cn.episooo.datastructurewebapplication.utils.IJPA;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/23 23:17
 * @Description：
 */
public interface RecommendAnswerDAO extends IJPA<RecommendAnswer> {
    RecommendAnswer findTopByStuIdAndQuestionId(int stuId,int questionId);
}
