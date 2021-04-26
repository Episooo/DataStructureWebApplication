package cn.episooo.datastructurewebapplication.service;

import cn.episooo.datastructurewebapplication.dao.TeacherDAO;
import cn.episooo.datastructurewebapplication.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/8 0:28
 * @Description：
 */
@Component
public class TeacherService {
    @Autowired
    TeacherDAO teacherDAO;

    public Teacher getTeacher(Integer userId) {
        return teacherDAO.findTopById(userId);
    }
}
