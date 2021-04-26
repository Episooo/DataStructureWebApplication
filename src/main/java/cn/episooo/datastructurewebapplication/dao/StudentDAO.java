package cn.episooo.datastructurewebapplication.dao;

import cn.episooo.datastructurewebapplication.domain.Student;
import cn.episooo.datastructurewebapplication.utils.IJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/8 0:33
 * @Description：
 */
@Repository
public interface StudentDAO extends IJPA<Student> {
    Student findTopById(Integer id);

    Student findByUsernameAndPassword(String username, String password);


    Page<Student> findByStudentClassIdAndNameLike(int classId, String s, Pageable of);

    Page<Student> findByStudentClassId(int classId, Pageable of);

}
