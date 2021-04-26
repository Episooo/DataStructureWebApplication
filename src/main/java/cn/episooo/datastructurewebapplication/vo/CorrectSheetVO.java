package cn.episooo.datastructurewebapplication.vo;

import cn.episooo.datastructurewebapplication.domain.AnswerSheet;
import cn.episooo.datastructurewebapplication.domain.Student;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/3 16:55
 * @Description：
 */
//HashMap<StudentClass,List<CorrectSheetVO>>
public class CorrectSheetVO {
    Student student;
    AnswerSheet answerSheet;

    public Student getStudent() {
        return student;
    }

    public CorrectSheetVO setStudent(Student student) {
        this.student = student;
        return this;
    }

    public AnswerSheet getAnswerSheet() {
        return answerSheet;
    }

    public CorrectSheetVO setAnswerSheet(AnswerSheet answerSheet) {
        this.answerSheet = answerSheet;
        return this;
    }
}
