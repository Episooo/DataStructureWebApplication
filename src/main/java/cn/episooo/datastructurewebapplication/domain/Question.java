package cn.episooo.datastructurewebapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 19:28
 * @Description：
 */
@Entity
@Table
public class Question implements ID{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(columnDefinition = "tinyint")
    private QuestionType type;//选择、填空

    @Column(columnDefinition = "text")
    private String subject;//题目名

    @Column
    private String a;

    @Column
    private String b;

    @Column
    private String c;

    @Column
    private String d;

    @Column
    private String correctAnswer;//正确答案：选择题则填选项，填空题填答案

    @Column
    private double correctRate;//正确率

    @Column(columnDefinition = "tinyint")
    private boolean isPublic;

    @ManyToMany()
    @JsonIgnoreProperties("questions")
    @JoinTable(name="question_tag",inverseJoinColumns=@JoinColumn(name="tagId"),joinColumns=@JoinColumn(name="QuestId"))
    @org.hibernate.annotations.ForeignKey(name = "none")
    private List<Tag> tags;

    public List<Tag> getTags() {
        return tags;
    }

    public Question setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Question setId(Integer id) {
        this.id = id;
        return this;
    }

    public QuestionType getType() {
        return type;
    }

    public Question setType(QuestionType type) {
        this.type = type;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Question setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getA() {
        return a;
    }

    public Question setA(String a) {
        this.a = a;
        return this;
    }

    public String getB() {
        return b;
    }

    public Question setB(String b) {
        this.b = b;
        return this;
    }

    public String getC() {
        return c;
    }

    public Question setC(String c) {
        this.c = c;
        return this;
    }

    public String getD() {
        return d;
    }

    public Question setD(String d) {
        this.d = d;
        return this;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Question setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public double getCorrectRate() {
        return correctRate;
    }

    public Question setCorrectRate(double correctRate) {
        this.correctRate = correctRate;
        return this;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public Question setPublic(boolean aPublic) {
        isPublic = aPublic;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
