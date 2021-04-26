package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.ClassDAO;
import cn.episooo.datastructurewebapplication.domain.StudentClass;
import cn.episooo.datastructurewebapplication.utils.StringUtil;
import cn.episooo.datastructurewebapplication.utils.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 18:29
 * @Description：
 */
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassDAO classDAO;

    @GetMapping("/get/{id}")
    public ResultMsg getClass(@PathVariable int id){
        return new ResultMsg(1,"",classDAO.findById(id));
    }

    @GetMapping("/getClasses")
    public ResultMsg getClasses(){
        List<StudentClass> all = classDAO.findAll();
        for(StudentClass stu : all){
            stu.setStudents(null);
//            stu.setSheets(null);
        }
        return ResultMsg.successResult(all);
    }

    @GetMapping("/getAll")
    public ResultMsg getClases(String query,int pagenum,int pagesize){
        if(!StringUtil.isEmpty(query))
            return ResultMsg.successResult(classDAO.findByClassNameLike("%"+query+"%",PageRequest.of(pagenum-1, pagesize)));
        return ResultMsg.successResult(classDAO.findAll(PageRequest.of(pagenum-1, pagesize)));
    }

    @PostMapping("/add")
    public ResultMsg addClass(StudentClass studentClass){
        classDAO.save(studentClass);
        return ResultMsg.successResult();
    }

    @PostMapping("/update")
    public ResultMsg updateClass(StudentClass studentClass){
        System.out.println(studentClass);
        UpdateUtil.updateById(classDAO,studentClass);
        return ResultMsg.successResult();
    }

    @GetMapping("/delete")
    public ResultMsg deleteCLass(@RequestParam int id){
        classDAO.deleteById(id);
        return ResultMsg.successResult();
    }
}
