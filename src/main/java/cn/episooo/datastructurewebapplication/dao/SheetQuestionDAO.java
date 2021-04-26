package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.SheetQuestion;
import cn.episooo.datastructurewebapplication.domain.SheetQuestionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:34
 * @Description：
 */
public interface SheetQuestionDAO extends JpaRepository<SheetQuestion, SheetQuestionKey>, JpaSpecificationExecutor<SheetQuestion> {
    @Modifying
    @Transactional
    public void deleteById_SheetId(int id);
}
