package cn.episooo.datastructurewebapplication.controller.student;

import cn.episooo.datastructurewebapplication.annotation.Authorization;
import cn.episooo.datastructurewebapplication.annotation.CurrentStudent;
import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.MessageDAO;
import cn.episooo.datastructurewebapplication.domain.Message;
import cn.episooo.datastructurewebapplication.domain.Student;
import cn.episooo.datastructurewebapplication.utils.security.TokenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/5 14:30
 * @Description：
 */
@RestController
@RequestMapping("/stu/message")
public class StuMessageController {

    public static int TEACHER_ID = 10000;
    @Autowired
    MessageDAO dao;

    @GetMapping("/getUnreadMessage")
    @Authorization(type = TokenType.Student)
    public ResultMsg getUnreadMessage(@CurrentStudent(formDAO = false) Student student){
        List<Message> msgs = dao.findAllByToIdAndAndIsReadOrderByIdAsc(student.getId(), false);
        for(Message msg : msgs){//设置为已读
            msg.setRead(true);
            dao.save(msg);
        }
        if(msgs.size()==0){
            List<Message> historyMessages = dao.findTop10ByFromIdOrToIdOrderByIdDesc(student.getId(), student.getId());
            historyMessages = reverseList(historyMessages);
            return ResultMsg.successResult(historyMessages);
        }else{
            Integer firstId = msgs.get(0).getId();
            List<Message> historyMessages = dao.findTop10ByIdLessThanAndFromIdOrIdLessThanAndToIdOrderByIdDesc(firstId, student.getId(), firstId, student.getId());
            historyMessages = reverseList(historyMessages);
            if(historyMessages.size()!=0){
                historyMessages.addAll(msgs);
                return ResultMsg.successResult(historyMessages);
            }
        }
        return ResultMsg.successResult(msgs);
    }
    @PostMapping("/send")
    @Authorization(type = TokenType.Student)
    public ResultMsg send(@CurrentStudent(formDAO = false) Student student, String text){
        Message message = new Message();
        message.setFromId(student.getId());
        message.setToId(TEACHER_ID);
        message.setDate(new Date().getTime());
        message.setText(text);
        dao.save(message);
        return ResultMsg.successResult();
    }

    @GetMapping("/getNewMessage")
    @Authorization(type = TokenType.Student)
    public ResultMsg getNewMessage(@CurrentStudent(formDAO = false) Student student,@RequestParam int endId){
        List<Message> lastMsgs = dao.findAllByIdAfterAndFromIdOrIdAfterAndToId(endId, student.getId(), endId, student.getId());
        for (Message message : lastMsgs) {
            if(message.getToId()==student.getId()){
                message.setRead(true);
                dao.save(message);
            }
        }
        return ResultMsg.successResult(lastMsgs);
    }
    @GetMapping("/getHistoryMessage")
    @Authorization(type = TokenType.Student)
    public ResultMsg getHistoryMessage(@CurrentStudent(formDAO = false) Student student,int beginId){
        List<Message> historyMessages = dao.findTop10ByIdLessThanAndFromIdOrIdLessThanAndToIdOrderByIdDesc(beginId, student.getId(), beginId, student.getId());
        historyMessages = reverseList(historyMessages);
        return ResultMsg.successResult(historyMessages);
    }

    public List<Message> reverseList(List<Message> list){
        List<Message> reverse = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reverse.add(list.get(i));
        }
        return reverse;
    }
}
