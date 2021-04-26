package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.AnswerSheet;
import cn.episooo.datastructurewebapplication.utils.IJPA;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:28
 * @Description：
 */
@Repository
public interface AnswerSheetDAO extends IJPA<AnswerSheet> {
    public AnswerSheet findByIdSheetIdAndIdStuId(int sheetId,int stuId);

    public List<AnswerSheet> findAllByIdSheetId(int sheetId);
}
