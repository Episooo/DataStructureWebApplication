package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.SheetQuestionDAO;
import cn.episooo.datastructurewebapplication.domain.SheetQuestion;
import cn.episooo.datastructurewebapplication.domain.SheetQuestionKey;
import cn.episooo.datastructurewebapplication.utils.UpdateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 20:31
 * @Description：
 */
@RestController
@RequestMapping("/sheetQuestion")
public class SheetQuestionController {
    @Autowired
    private SheetQuestionDAO sheetQuestionDAO;


    @PostMapping("/add")
    public ResultMsg add(SheetQuestion sheetQuestion){
        sheetQuestion.setId(null);
        sheetQuestionDAO.save(sheetQuestion);
        return ResultMsg.successResult();
    }

    @GetMapping("/getAll")
    public ResultMsg getAll(){
        List<SheetQuestion> all = sheetQuestionDAO.findAll();
//        for(Student stu : all){
//            stu.setPassword(null);
//        }
        return ResultMsg.successResult(all);
    }

    @GetMapping("/get")
    public ResultMsg get(@PathVariable SheetQuestionKey id){
        SheetQuestion stu = sheetQuestionDAO.getOne(id);
        return ResultMsg.successResult(stu);
    }

    @PostMapping("/update")
    public ResultMsg update(SheetQuestion sheetQuestion){
        SheetQuestionKey id = sheetQuestion.getId();
        SheetQuestion oldValue = sheetQuestionDAO.getOne(id);
        BeanUtils.copyProperties(sheetQuestion,oldValue, UpdateUtil.getNullPropertyNames(sheetQuestion));
        return ResultMsg.successResult();
    }
}
