package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.Sheet;
import cn.episooo.datastructurewebapplication.domain.StudentClass;
import cn.episooo.datastructurewebapplication.utils.IJPA;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:31
 * @Description：
 */
public interface SheetDAO extends IJPA<Sheet> {
    public Sheet findByClassesContainsAndId(StudentClass classId, int sheetId);
}
