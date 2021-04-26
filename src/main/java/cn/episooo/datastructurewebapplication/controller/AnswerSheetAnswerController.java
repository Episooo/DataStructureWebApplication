package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.AnswerSheetAnswerDAO;
import cn.episooo.datastructurewebapplication.domain.AnswerSheetAnswer;
import cn.episooo.datastructurewebapplication.domain.AnswerSheetAnswerKey;
import cn.episooo.datastructurewebapplication.utils.UpdateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 23:28
 * @Description：
 */
@RestController
@RequestMapping("/answerSheetAnswer")
public class AnswerSheetAnswerController {

    @Autowired
    private AnswerSheetAnswerDAO answerSheetAnswerDAO;


    @PostMapping("/add")
    public ResultMsg add(AnswerSheetAnswer answerSheetAnswer){
        answerSheetAnswerDAO.save(answerSheetAnswer);
        return ResultMsg.successResult();
    }

//    @GetMapping("/getAll")
//    public ResultMsg getAll(){
//        List<SheetForClass> all = answerSheetAnswerDAO.findAll();
////        for(Student stu : all){
////            stu.setPassword(null);
////        }
//        return ResultMsg.successResult(all);
//    }
    @GetMapping("/get/sheetId/{sheetId}/stuId/{stuId}")
    public ResultMsg get(@PathVariable int sheetId,@PathVariable int stuId){
        List<AnswerSheetAnswer> all = answerSheetAnswerDAO.findAllById_SheetIdAndId_StuId(sheetId,stuId);
        return ResultMsg.successResult(all);
    }

    @PostMapping("/update")
    public ResultMsg update(AnswerSheetAnswer answerSheetAnswer){
        AnswerSheetAnswerKey id = answerSheetAnswer.getId();
        AnswerSheetAnswer oldValue = answerSheetAnswerDAO.getOne(id);
        BeanUtils.copyProperties(answerSheetAnswer,oldValue, UpdateUtil.getNullPropertyNames(answerSheetAnswer));
        return ResultMsg.successResult();
    }

    @GetMapping("/delete")
    public ResultMsg delete(AnswerSheetAnswerKey id){
        answerSheetAnswerDAO.deleteById(id);
        return ResultMsg.successResult();
    }
}
