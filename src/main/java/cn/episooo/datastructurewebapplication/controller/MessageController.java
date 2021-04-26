package cn.episooo.datastructurewebapplication.controller;

import cn.episooo.datastructurewebapplication.config.ResultMsg;
import cn.episooo.datastructurewebapplication.dao.ClassDAO;
import cn.episooo.datastructurewebapplication.dao.MessageDAO;
import cn.episooo.datastructurewebapplication.domain.Message;
import cn.episooo.datastructurewebapplication.domain.Student;
import cn.episooo.datastructurewebapplication.domain.StudentClass;
import cn.episooo.datastructurewebapplication.vo.StudentMsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/21 23:23
 * @Description：
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageDAO dao;

    @Autowired
    ClassDAO classDao;
//    @Override
//    @GetMapping("/getAll")
//    public ResultMsg get(int pagenum, int pagesize) {
//        return super.get(pagenum, pagesize);
//    }
//
//    @Override
//    @PostMapping("/add")
//    public ResultMsg add(Message tObj) {
//        return super.add(tObj);
//    }
//
//    @Override
//    @PostMapping("/update")
//    public ResultMsg update(Message tObj) {
//        return super.update(tObj);
//    }
//
//    @Override
//    @PostMapping("/delete")
//    public ResultMsg delete(@RequestParam int id) {
//        return super.delete(id);
//    }
//
//    @Override
//    @GetMapping("/get/{id}")
//    public ResultMsg getOne(@PathVariable int id) {
//        return super.getOne(id);
//    }
    public static int TEACHER_ID = 10000;
    @GetMapping("/getUnreadMessage")
    public ResultMsg getUnreadMessage(@RequestParam int id){
        List<Message> msgs = dao.findAllByFromIdAndAndIsReadOrderByIdAsc(id, false);
        for(Message msg : msgs){//设置为已读
            msg.setRead(true);
            dao.save(msg);
        }
        if(msgs.size()==0){//没有未读消息 获取最新10条
            List<Message> historyMessages = dao.findTop10ByFromIdOrToIdOrderByIdDesc(id, id);
            historyMessages = reverseList(historyMessages);
            return ResultMsg.successResult(historyMessages);
        }else{//有未读消息 获取未读消息开始的前10条
            Integer firstId = msgs.get(0).getId();
            List<Message> historyMessages = dao.findTop10ByIdLessThanAndFromIdOrIdLessThanAndToIdOrderByIdDesc(firstId, id, firstId, id);
            historyMessages = reverseList(historyMessages);
            if(historyMessages.size()!=0){
                historyMessages.addAll(msgs);
                return ResultMsg.successResult(historyMessages);
            }
        }
        return ResultMsg.successResult(msgs);
    }

    @GetMapping("/getHistoryMessage")
    public ResultMsg getHistoryMessage(@RequestParam int id,int beginId){
        List<Message> historyMessages = dao.findTop10ByIdLessThanAndFromIdOrIdLessThanAndToIdOrderByIdDesc(beginId, id, beginId, id);
        historyMessages = reverseList(historyMessages);
        return ResultMsg.successResult(historyMessages);
    }

    @GetMapping("/getStatus")//统计所有班级所有学生消息状态，从数据库获取所有未读消息，填充给对应的studentMsgVO
    public ResultMsg getStatus(){
        List<Message> unreadMsg = dao.findAllByToIdAndIsRead(TEACHER_ID, false);
        HashMap<Integer,Integer> countMap = new HashMap<>();
        for (Message message : unreadMsg) {
            if(!countMap.containsKey(message.getFromId())){
                countMap.put(message.getFromId(),1);
            }else {
                countMap.put(message.getFromId(),countMap.get(message.getFromId())+1);
            }
        }
        List<StudentClass> classList = classDao.findAll();
        // List< List<StudentMsg> >
        HashMap<String,List<StudentMsgVO>> lists = new HashMap<>();
        for (StudentClass studentClass : classList) {
            ArrayList<StudentMsgVO> studentMsgVOList = new ArrayList<>();
            for (Student student : studentClass.getStudents()) {
                StudentMsgVO svo = new StudentMsgVO();
                svo.setStuId(student.getId());
                svo.setStuUsername(student.getUsername());
                svo.setStuName(student.getName());
                if(countMap.containsKey(student.getId())){
                    svo.setUnreadCnt(countMap.get(student.getId()));
                }else{
                    svo.setUnreadCnt(0);
                }
                studentMsgVOList.add(svo);
            }
            lists.put(studentClass.getClassName(),studentMsgVOList);
        }
        return ResultMsg.successResult(lists);
    }

    @PostMapping("/send")
    public ResultMsg send(@RequestParam int id,@RequestParam String text){
        Message message = new Message();
        message.setFromId(TEACHER_ID);
        message.setToId(id);
        message.setDate(new Date().getTime());
        message.setText(text);
        dao.save(message);
        return ResultMsg.successResult();
    }
    //流程
    // 获取status  轮询status，不覆盖list区域数据
        //getStatus
    //点击单个学生  获取未读消息    轮询 获取endId以后的消息，添加到list
        //   getUnreadMessage     getNewMessage
        //获取该学生的未读消息时，没有就是历史10条 再没有就是null 前端endID传0
    //触发滚动到顶，获取10条历史   beginId从该学生的msgList区域取得
        //getHistoryMessage(id,beginId)
    //发送消息
        // send
    //TODO:getNewMessage
    @GetMapping("/getNewMessage")
    public ResultMsg getNewMessage(@RequestParam int id,@RequestParam int endId){
        List<Message> lastMsg = dao.findAllByIdAfterAndFromIdOrIdAfterAndToId(endId, id, endId, id);
        for (Message message : lastMsg) {
            if(message.getToId()==TEACHER_ID){
                message.setRead(true);
                dao.save(message);
            }
        }
        return ResultMsg.successResult(lastMsg);
    }

    public List<Message> reverseList(List<Message> list){
        List<Message> reverse = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reverse.add(list.get(i));
        }
        return reverse;
    }
}
