package cn.episooo.datastructurewebapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/2/10 11:54
 * @Description：
 */
@Entity
@Table
public class Tag implements ID{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String name;


    @ManyToMany(mappedBy="tags")
    @JsonIgnoreProperties("tags")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private List<Question> questions;

    public List<Question> getQuestions() {
        return questions;
    }

    public Tag setQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Tag setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }
}
