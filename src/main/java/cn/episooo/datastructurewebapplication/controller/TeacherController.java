package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.domain.Teacher;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 20:21
 * @Description：
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends CommonController<Teacher> {
    @Override
    @GetMapping("/getAll")
    public ResultMsg get(int pagenum, int pagesize) {
        return super.get(pagenum, pagesize);
    }

    @Override
    @PostMapping("/add")
    public ResultMsg add(Teacher tObj) {
        return super.add(tObj);
    }

    @Override
    @PostMapping("/update")
    public ResultMsg update(Teacher tObj) {
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
