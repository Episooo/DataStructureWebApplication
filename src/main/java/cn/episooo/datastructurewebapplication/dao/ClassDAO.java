package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.Sheet;
import cn.episooo.datastructurewebapplication.domain.StudentClass;
import cn.episooo.datastructurewebapplication.utils.IJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/19 20:03
 * @Description：
 */
@Repository
public interface ClassDAO extends IJPA<StudentClass> {
    Page<StudentClass> findByClassNameLike(String className, Pageable of);
    public List<StudentClass> findAllBySheetsContains(Sheet sheetId);
}
