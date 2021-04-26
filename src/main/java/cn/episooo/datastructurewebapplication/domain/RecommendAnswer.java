package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/18 17:57
 * @Description：
 */
@Entity
@Table(
        uniqueConstraints = {
        @UniqueConstraint(name = "stu_que_unique", columnNames = {"stuId","questionId"})
},
        indexes = {
        @Index(name = "stu_que_index", columnList = "stuId,questionId")
})
public class RecommendAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer stuId;
    @Column
    private Integer questionId;
    @Column(columnDefinition = "tinyint")
    private boolean isRight;

    public Integer getId() {
        return id;
    }

    public RecommendAnswer setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getStuId() {
        return stuId;
    }

    public RecommendAnswer setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public RecommendAnswer setQuestionId(Integer questionId) {
        this.questionId = questionId;
        return this;
    }

    public boolean isRight() {
        return isRight;
    }

    public RecommendAnswer setRight(boolean right) {
        isRight = right;
        return this;
    }
}
