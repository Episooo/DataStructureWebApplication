package cn.episooo.datastructurewebapplication.controller.student;

import cn.episooo.datastructurewebapplication.annotation.Authorization;
import cn.episooo.datastructurewebapplication.annotation.CurrentStudent;
import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.AnswerSheetDAO;
import cn.episooo.datastructurewebapplication.dao.ClassDAO;
import cn.episooo.datastructurewebapplication.dao.SheetDAO;
import cn.episooo.datastructurewebapplication.domain.*;
import cn.episooo.datastructurewebapplication.utils.security.TokenType;
import cn.episooo.datastructurewebapplication.vo.AnsSheetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/3/21 17:42
 * @Description：
 */
@RestController
@RequestMapping("/stu/sheet")
public class StuSheetController {
    @Autowired
    SheetDAO sheetDAO;

    @Autowired
    AnswerSheetDAO answerSheetDAO;

    @Autowired
    ClassDAO classDao;

    @RequestMapping("/getSheets")
    @Authorization(type = TokenType.Student)
    public ResultMsg getSheets(@CurrentStudent(formDAO = true) Student student,int type){
        List<Sheet> sheets = student.getStudentClass().getSheets();
        Iterator<Sheet> iterator = sheets.iterator();
        List<AnsSheetVO> list = new ArrayList<>();
        while(iterator.hasNext()){
            Sheet sheet = iterator.next();
            if(sheet.getType()!=type){
                iterator.remove();
            }else{
                Integer sheetId = sheet.getId();
                AnswerSheet ansSheet = answerSheetDAO.findByIdSheetIdAndIdStuId(sheetId, student.getId());
                list.add(new AnsSheetVO(ansSheet,sheet));
            }
        }
        return ResultMsg.successResult(list);
    }

    @RequestMapping("/getSheet")
    @Authorization(type = TokenType.Student)
    public ResultMsg getSheet(@CurrentStudent(formDAO = true) Student student, @RequestParam int sheetId){
        Sheet sheet = sheetDAO.findByClassesContainsAndId(student.getStudentClass(), sheetId);
        if(sheet==null){
            return ResultMsg.failedResult(-1,"找不到试卷或没有权限");
        }
        if(new Date().getTime()<sheet.getStartTime()){
            return ResultMsg.failedResult(-2,"未到答题时间");
        }
        //去除不必要的内容
        sheet.setClasses(null);
        List<SheetQuestion> sheetQuestions = sheet.getSheetQuestions();
        for(SheetQuestion q:sheetQuestions){
            q.getQuestion().setCorrectAnswer(null);
        }
        return ResultMsg.successResult(sheet);
    }

    @RequestMapping("/getAnsSheet")
    @Authorization(type = TokenType.Student)
    public ResultMsg getAnsSheet(@CurrentStudent(formDAO = false) Student student, @RequestParam int sheetId){
        return ResultMsg.successResult(answerSheetDAO.findByIdSheetIdAndIdStuId(sheetId, student.getId()));
    }

//    @RequestMapping("/putAnsSheet")
//    @Authorization(type = TokenType.Student)
//    public ResultMsg putAnsSheet(@CurrentStudent(formDAO = false) Student student,@RequestBody AnswerSheet answerSheet){
//        System.out.println(answerSheet);
//        AnswerSheetKey id = answerSheet.getId();
//        id.setStuId(student.getId());
//        for(AnswerSheetAnswer ans:answerSheet.getAnswers()){
//            ans.getId().setStuId(student.getId());
//        }
//        answerSheet.setCorrected(false);
//        answerSheet.setScore(0);
//        //TODO：批改
//
//        answerSheetDAO.save(answerSheet);
//        return ResultMsg.successResult();
//    }
public static AnswerSheetAnswer newEmptyASA(int sheetId,int stuId,int number){
    return new AnswerSheetAnswer()
            .setId(
                    new AnswerSheetAnswerKey()
                            .setSheetId(sheetId)
                            .setStuId(stuId)
                            .setAnsNumber(number)
            ).setScore(0);
}

    public List<AnswerSheetAnswer> correctAns(List<AnswerSheetAnswer> answers, List<SheetQuestion> questions, int stuId, int sheetId){
        Collections.sort(answers);
        Collections.sort(questions);
        List<AnswerSheetAnswer> temps = new ArrayList<>(questions.size());
        //重新创建答案list是为了处理用户攻击性行为
        for(int i = 0; i < questions.size(); i++){
            SheetQuestion quest = questions.get(i);
            temps.add(newEmptyASA(sheetId, stuId, quest.getId().getNumber()));
        }
        for(int i = 0,j = 0; i < answers.size() && j < questions.size(); i++,j++){
            AnswerSheetAnswer ans = answers.get(i);
            SheetQuestion quest = questions.get(j);
            if(quest.getId().getNumber() == ans.getId().getAnsNumber()){
                temps.get(j).setAnswer(ans.getAnswer());
                if(quest.getQuestion().getCorrectAnswer().equals(ans.getAnswer())  ){
                    temps.get(j).setScore(quest.getScore());
                }
            }else if(quest.getId().getNumber() > ans.getId().getAnsNumber()){
                j--;
            }
        }
        return temps;
    }

    @PostMapping("/add")
    @Transactional
    @Authorization(type=TokenType.Student)
    public ResultMsg addAnsSheet(@RequestBody AnswerSheet answerSheet, @CurrentStudent(formDAO = false) Student student){
        Sheet sheet = sheetDAO.findById(answerSheet.getId().getSheetId()).get();
        if(sheet == null) return ResultMsg.failedResult("sheet id not found");
        if(new Date().getTime()<sheet.getStartTime() || new Date().getTime()-3*60*1000>sheet.getEndTime()){
            return ResultMsg.failedResult("不在提交时间");
        }
        //填充学生信息
        AnswerSheetKey id = answerSheet.getId();
        id.setStuId(student.getId());
        for(AnswerSheetAnswer ans:answerSheet.getAnswers()){
            ans.getId().setStuId(student.getId());
        }
        answerSheet.setCorrected(false);
        answerSheet.setScore(0);
        //批改
        List<AnswerSheetAnswer> answerSheetAnswers = correctAns(answerSheet.getAnswers(), sheet.getSheetQuestions(), student.getId(), answerSheet.getId().getSheetId());
        answerSheet.setAnswers(answerSheetAnswers);
        double totalScore = 0;
        for( AnswerSheetAnswer ans : answerSheetAnswers){
            totalScore += ans.getScore();
        }
        answerSheet.setScore(totalScore);
        answerSheet.setCorrected(false);
        //持久化

        answerSheetDAO.save(answerSheet);
        return ResultMsg.successResult();
    }
}
