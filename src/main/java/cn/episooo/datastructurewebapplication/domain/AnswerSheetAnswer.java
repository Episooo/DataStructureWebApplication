package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:16
 * @Description：
 */
@Entity
@Table
public class AnswerSheetAnswer implements Comparable<AnswerSheetAnswer>{

    @EmbeddedId
    private AnswerSheetAnswerKey id;

    @Column
    private String answer;

    @Column
    private double score;



    public AnswerSheetAnswerKey getId() {
        return id;
    }

    public AnswerSheetAnswer setId(AnswerSheetAnswerKey id) {
        this.id = id;
        return this;
    }

    public String getAnswer() {
        return answer;
    }

    public AnswerSheetAnswer setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    public double getScore() {
        return score;
    }

    public AnswerSheetAnswer setScore(double score) {
        this.score = score;
        return this;
    }

    @Override
    public int compareTo(AnswerSheetAnswer o) {
        return this.getId().getAnsNumber() - o.getId().getAnsNumber();
    }
}
