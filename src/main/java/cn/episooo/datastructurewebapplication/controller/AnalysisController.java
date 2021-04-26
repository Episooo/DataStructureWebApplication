package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.AnalysisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/11 14:15
 * @Description：
 */
@RestController
@RequestMapping("/analy")
public class AnalysisController {

    @Autowired
    AnalysisDAO dao;
    @RequestMapping("/classSheet/score")
    public ResultMsg getClassSheet(int classId, int sheetId){
        return ResultMsg.successResult(dao.getClassSheetTable(classId,sheetId));
    }

    @RequestMapping("/classSheet/answer")
    @ResponseBody
    public ResultMsg getClassSheetAnswerInfo(int classId, int sheetId){
        return ResultMsg.successResult(dao.getClassSheetAnswerInfo(classId,sheetId));
    }
}
