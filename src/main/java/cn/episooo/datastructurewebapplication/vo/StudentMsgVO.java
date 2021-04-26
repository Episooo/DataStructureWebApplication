package cn.episooo.datastructurewebapplication.vo;

import cn.episooo.datastructurewebapplication.domain.Message;

import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/5 0:11
 * @Description：
 */
public class StudentMsgVO {
    private int stuId;
    private String stuUsername;
    private String stuName;
    private int unreadCnt;
    private List<Message> msgs;
    public int getStuId() {
        return stuId;
    }

    public StudentMsgVO setStuId(int stuId) {
        this.stuId = stuId;
        return this;
    }

    public String getStuUsername() {
        return stuUsername;
    }

    public StudentMsgVO setStuUsername(String stuUsername) {
        this.stuUsername = stuUsername;
        return this;
    }

    public String getStuName() {
        return stuName;
    }

    public StudentMsgVO setStuName(String stuName) {
        this.stuName = stuName;
        return this;
    }

    public int getUnreadCnt() {
        return unreadCnt;
    }

    public StudentMsgVO setUnreadCnt(int unreadCnt) {
        this.unreadCnt = unreadCnt;
        return this;
    }

    public List<Message> getMsgs() {
        return msgs;
    }

    public StudentMsgVO setMsgs(List<Message> msgs) {
        this.msgs = msgs;
        return this;
    }
}
