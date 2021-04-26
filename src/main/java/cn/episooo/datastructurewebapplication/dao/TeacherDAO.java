package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.Teacher;
import cn.episooo.datastructurewebapplication.utils.IJPA;
import org.springframework.stereotype.Repository;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/8 0:33
 * @Description：
 */
@Repository
public interface TeacherDAO extends IJPA<Teacher> {
    Teacher findByUsernameAndPassword(String username, String password);

    Teacher findTopById(Integer userId);
}
