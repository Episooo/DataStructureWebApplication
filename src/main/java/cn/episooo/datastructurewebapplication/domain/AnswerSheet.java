package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:05
 * @Description：
 */
@Entity
@Table
public class AnswerSheet {

    @EmbeddedId//stuId  sheetId
    private AnswerSheetKey id;

    @OneToMany(targetEntity  = AnswerSheetAnswer.class,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "sheetId",referencedColumnName = "sheetId"),
            @JoinColumn(name = "stuId",referencedColumnName="stuId")
    })
    private List<AnswerSheetAnswer> answers;

    @Column(columnDefinition = "tinyint")
    private boolean isCorrected;//是否批改了

    @Column
    private double score;

    public AnswerSheetKey getId() {
        return id;
    }

    public AnswerSheet setId(AnswerSheetKey id) {
        this.id = id;
        return this;
    }

    public double getScore() {
        return score;
    }

    public AnswerSheet setScore(double score) {
        this.score = score;
        return this;
    }

    public List<AnswerSheetAnswer> getAnswers() {
        return answers;
    }

    public AnswerSheet setAnswers(List<AnswerSheetAnswer> answers) {
        this.answers = answers;
        return this;
    }

    public boolean isCorrected() {
        return isCorrected;
    }

    public AnswerSheet setCorrected(boolean corrected) {
        isCorrected = corrected;
        return this;
    }

    @Override
    public String toString() {
        return "AnswerSheet{" +
                "id=" + id +
                ", answers=" + answers +
                ", isCorrected=" + isCorrected +
                ", score=" + score +
                '}';
    }
}
