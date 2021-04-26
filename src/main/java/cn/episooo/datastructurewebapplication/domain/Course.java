package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 19:12
 * @Description：
 */
@Entity
@Table
public class Course implements ID{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String subject;

    @Column(columnDefinition = "text")
    private String text;

    @Column()
    private Integer sortNumber;

    @OneToMany(targetEntity  = CourseSource.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "courseId")
    private List<CourseSource> courseSources;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", text='" + text + '\'' +
                ", sortNumber=" + sortNumber +
                ", courseSources=" + courseSources +
                '}';
    }

    public List<CourseSource> getCourseSources() {
        return courseSources;
    }

    public Course setCourseSources(List<CourseSource> courseSources) {
        this.courseSources = courseSources;
        return this;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public Course setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Course setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Course setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getText() {
        return text;
    }

    public Course setText(String text) {
        this.text = text;
        return this;
    }
}
