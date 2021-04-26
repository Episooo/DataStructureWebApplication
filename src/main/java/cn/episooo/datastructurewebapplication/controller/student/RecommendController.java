package cn.episooo.datastructurewebapplication.controller.student;

import cn.episooo.datastructurewebapplication.annotation.Authorization;
import cn.episooo.datastructurewebapplication.annotation.CurrentStudent;
import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.QuestionDAO;
import cn.episooo.datastructurewebapplication.dao.RecommendAnswerDAO;
import cn.episooo.datastructurewebapplication.domain.Question;
import cn.episooo.datastructurewebapplication.domain.RecommendAnswer;
import cn.episooo.datastructurewebapplication.domain.Student;
import cn.episooo.datastructurewebapplication.service.RecommendService;
import cn.episooo.datastructurewebapplication.utils.security.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/18 18:29
 * @Description：
 */
@RestController
@RequestMapping("stu/rcm")
public class RecommendController {

    @Autowired
    RecommendService recommendService;

    @Autowired
    RecommendAnswerDAO recommendAnswerDAO;

    @Autowired
    QuestionDAO questionDAO;

    @RequestMapping("/recommendOne")
    @Authorization(type = TokenType.Student)
    public ResultMsg recommendOne(@CurrentStudent(formDAO = false)Student student) throws Exception {
        System.out.println(recommendService==null);
        HashSet<Question> questions1 = recommendService.recommendByTag(student.getId());
//        HashSet<Question> questions2 = recommendService.recommendedItems(student.getId());
//        questions1.addAll(questions2);
        return ResultMsg.successResult(questions1);

    }
    @RequestMapping("/recommendTwo")
    @Authorization(type = TokenType.Student)
    public ResultMsg recommendTwo(@CurrentStudent(formDAO = false)Student student) throws Exception {
//        HashSet<Question> questions1 = recommendService.recommendByTag(student.getId());
        HashSet<Question> questions2 = recommendService.recommendedItems(student.getId());
//        questions1.addAll(questions2);
        return ResultMsg.successResult(questions2);

    }
    @RequestMapping("/randOne")
    public ResultMsg randOne(){
        return ResultMsg.successResult();
    }
    @RequestMapping("/submit")
    @Authorization(type = TokenType.Student)
    public ResultMsg submit(@CurrentStudent(formDAO = false)Student student,@RequestParam Integer questionId,@RequestParam String answer){
        Question question = questionDAO.findById(questionId).get();
        if(question==null){
            return ResultMsg.failedResult("unknown questionId");
        }
        boolean isRight = answer.equals(question.getCorrectAnswer());
        RecommendAnswer ra = recommendAnswerDAO.findTopByStuIdAndQuestionId(student.getId(), questionId);
        if(ra!=null){
            ra.setRight(isRight);
            recommendAnswerDAO.save(ra);
        }else {
            RecommendAnswer recommendAnswer = new RecommendAnswer();
            recommendAnswer.setStuId(student.getId());
            recommendAnswer.setQuestionId(questionId);
            recommendAnswer.setRight(isRight);
            recommendAnswerDAO.save(recommendAnswer);
        }
        HashMap<String,Object> data = new HashMap<>();
        data.put("isRight",isRight);
        data.put("answer",question.getCorrectAnswer());
        return ResultMsg.successResult(data);
    }

}
