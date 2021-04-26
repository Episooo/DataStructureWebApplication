package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.domain.Question;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 20:25
 * @Description：
 */
@RestController
@RequestMapping("/question")
public class QuestionController extends CommonController<Question> {

    @GetMapping("/getQuestions")
    public ResultMsg getQuestions(){
        return ResultMsg.successResult(dao.findAll());
    }

    @Override
    @GetMapping("/getAll")
    public ResultMsg get(int pagenum, int pagesize) {
        return super.get(pagenum, pagesize);
    }

    @Override
    @PostMapping("/add")
    public ResultMsg add(Question tObj) {
        return super.add(tObj);
    }

    @Override
    @PostMapping("/update")
    public ResultMsg update(Question tObj) {
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
