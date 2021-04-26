package cn.episooo.datastructurewebapplication.service;

import cn.episooo.datastructurewebapplication.dao.StudentDAO;
import cn.episooo.datastructurewebapplication.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/8 0:28
 * @Description：
 */
@Component
public class StudentService {
    @Autowired
    StudentDAO studentDAO;

    public Student getStudent(Integer userId) {
        return studentDAO.findTopById(userId);
    }
}
