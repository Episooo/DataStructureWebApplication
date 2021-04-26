package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.*;
import cn.episooo.datastructurewebapplication.domain.*;
import cn.episooo.datastructurewebapplication.vo.CorrectSheetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/2/17 9:22
 * @Description：
 */
@RestController
@RequestMapping("/answerSheet")
public class AnswerSheetController{
    @Autowired
    private AnswerSheetDAO answerSheetDAO;

    @Autowired
    private SheetDAO sheetDAO;

    @Autowired
    ClassDAO classDao;

    @Autowired
    StudentDAO studentDAO;
    @Autowired
    RecommendAnswerDAO recommendAnswerDAO;
//    @Authorization(type = TokenType.Teacher)
    @PostMapping("/getAll")
    public ResultMsg getAnswerSheets(int sheetId){
        List<AnswerSheet> answerSheets = answerSheetDAO.findAllByIdSheetId(sheetId);
        HashMap<Integer,AnswerSheet> answerSheetMap = new HashMap<>();
        for (AnswerSheet answerSheet : answerSheets) {
            answerSheetMap.put(answerSheet.getId().getStuId(),answerSheet);
        }
        List<StudentClass> classes = classDao.findAllBySheetsContains(new Sheet().setId(sheetId));
        HashMap<String,List<CorrectSheetVO>> vo = new HashMap<>();
        List<CorrectSheetVO> csvos;
        CorrectSheetVO csvo;
        for (StudentClass clazz : classes) {
            List<Student> students = clazz.getStudents();
            //TODO: 构建csvos
                csvos = new LinkedList<>();
            for (Student student : students) {
                csvo = new CorrectSheetVO();
                student.setPassword(null);
                student.setStudentClass(null);
                csvo.setStudent(student);
                csvo.setAnswerSheet(answerSheetMap.get(student.getId()));
                csvos.add(csvo);
            }
            vo.put(clazz.getClassName(),csvos);
        }
        //HashMap<StudentClass,List<CorrectSheetVO>>
       return ResultMsg.successResult(vo);
    }
    @PostMapping("/correct")
    @Transactional
//        @Authorization(type = TokenType.Teacher)
    public ResultMsg correctAnsSheet(@RequestBody AnswerSheet answerSheet){
        answerSheet.setCorrected(true);
        answerSheetDAO.save(answerSheet);
        Integer sheetId = answerSheet.getId().getSheetId();
        Sheet sheet = sheetDAO.findById(sheetId).get();
        Integer stuId = answerSheet.getId().getStuId();
        HashMap<Integer, SheetQuestion> questionScore = new HashMap<>();
        for (SheetQuestion sheetQuestion : sheet.getSheetQuestions()) {
            questionScore.put(sheetQuestion.getId().getNumber(),sheetQuestion);
        }
        for (AnswerSheetAnswer answer : answerSheet.getAnswers()) {
            Integer ansNumber = answer.getId().getAnsNumber();
            double score = answer.getScore();
            boolean isRight = questionScore.get(ansNumber).getScore()==score;
            int questionId = questionScore.get(ansNumber).getQuestion().getId();
            RecommendAnswer ra = recommendAnswerDAO.findTopByStuIdAndQuestionId(stuId, questionId);
            if(ra!=null){
                ra.setRight(isRight);
                recommendAnswerDAO.save(ra);
            }else {
                RecommendAnswer recommendAnswer = new RecommendAnswer();
                recommendAnswer.setStuId(stuId);
                recommendAnswer.setQuestionId(questionId);
                recommendAnswer.setRight(isRight);
                recommendAnswerDAO.save(recommendAnswer);
            }

        }
        return ResultMsg.successResult();
    }



}
