package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 19:57
 * @Description：
 */
@Entity
@Table
public class SheetQuestion implements Comparable<SheetQuestion>{

    @EmbeddedId
    private SheetQuestionKey id;


    @Column
    private double score;

    @OneToOne
    @JoinColumn(name = "questionId", referencedColumnName = "id")
    private Question question;


    @Override
    public int compareTo(SheetQuestion o) {
        return this.getId().getNumber()-o.getId().getNumber();
    }

    public Question getQuestion() {
        return question;
    }

    public SheetQuestion setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public SheetQuestionKey getId() {
        return id;
    }

    public SheetQuestion setId(SheetQuestionKey id) {
        this.id = id;
        return this;
    }

    public double getScore() {
        return score;
    }

    public SheetQuestion setScore(double score) {
        this.score = score;
        return this;
    }
}
