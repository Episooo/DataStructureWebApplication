package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.Course;
import cn.episooo.datastructurewebapplication.utils.IJPA;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:30
 * @Description：
 */
public interface CourseDAO extends IJPA<Course> {
    List<Course> findAllByOrderBySortNumberAsc();
}
