package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.AnswerSheetAnswer;
import cn.episooo.datastructurewebapplication.domain.AnswerSheetAnswerKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:29
 * @Description：
 */
public interface AnswerSheetAnswerDAO extends JpaRepository<AnswerSheetAnswer, AnswerSheetAnswerKey>, JpaSpecificationExecutor<AnswerSheetAnswer> {
    List<AnswerSheetAnswer> findAllById_SheetIdAndId_StuId(int sheetId, int userId);
}
