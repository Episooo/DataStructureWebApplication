package cn.episooo.datastructurewebapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 19:34
 * @Description：
 */
@Entity
@Table
public class Sheet implements ID{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(columnDefinition = "tinyint")
    private Integer type;

    @Column
    private String subject;//考卷名

    @ManyToMany()
    @JsonIgnoreProperties({"sheets","students"})
    @JoinTable(name="sheet_class",inverseJoinColumns=@JoinColumn(name="classId"),joinColumns=@JoinColumn(name="SheetId"))
    @org.hibernate.annotations.ForeignKey(name = "none")
    private List<StudentClass> classes;

    @Column(columnDefinition = "bigint(20)")
    private long startTime;

    @Column(columnDefinition = "bigint(20)")
    private long endTime;

    @OneToMany(targetEntity  = SheetQuestion.class ,cascade ={CascadeType.REFRESH,CascadeType.DETACH})
    @JoinColumn(name = "sheetId")
    private List<SheetQuestion> sheetQuestions;

    public List<SheetQuestion> getSheetQuestions() {
        return sheetQuestions;
    }

    public Sheet setSheetQuestions(List<SheetQuestion> sheetQuestions) {
        this.sheetQuestions = sheetQuestions;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Sheet setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public Sheet setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Sheet setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public long getStartTime() {
        return startTime;
    }

    public Sheet setStartTime(long startTime) {
        this.startTime = startTime;
        return this;
    }

    public long getEndTime() {
        return endTime;
    }

    public Sheet setEndTime(long endTime) {
        this.endTime = endTime;
        return this;
    }

    public List<StudentClass> getClasses() {
        return classes;
    }

    public Sheet setClasses(List<StudentClass> classes) {
        this.classes = classes;
        return this;
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "id=" + id +
                ", type=" + type +
                ", subject='" + subject + '\'' +
                ", classes=" + classes +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", sheetQuestions=" + sheetQuestions +
                '}';
    }
}
