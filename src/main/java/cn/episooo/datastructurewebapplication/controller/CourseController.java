package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.CourseDAO;
import cn.episooo.datastructurewebapplication.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 20:08
 * @Description：
 */
@RestController
@RequestMapping("/course")
public class CourseController extends CommonController<Course> {
    @Autowired
    private CourseDAO dao;
    @GetMapping("/getCourses")
    public ResultMsg getCourses(){
        return ResultMsg.successResult(dao.findAllByOrderBySortNumberAsc());
    }

    @Override
    @GetMapping("/getAll")
    public ResultMsg get(int pagenum, int pagesize) {
        return super.get(pagenum, pagesize);
    }

    @Override
    @PostMapping("/add")
    public ResultMsg add(Course tObj) {
        return super.add(tObj).setData(tObj.getId());
    }

    @Override
    @PostMapping("/update")
    public ResultMsg update(Course tObj) {
        System.out.println(dao.findById(tObj.getId()));
        return super.update(tObj);
    }

    @Override
    @PostMapping("/delete")
    public ResultMsg delete(@RequestParam int id) {
        return super.delete(id);
    }

    @Override
    @GetMapping("/get/{id}")
    public ResultMsg getOne(@PathVariable int id) {
        return super.getOne(id);
    }
}
