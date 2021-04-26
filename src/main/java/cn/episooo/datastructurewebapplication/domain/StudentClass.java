package cn.episooo.datastructurewebapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/19 19:20
 * @Description：
 */
@Entity
@Table(name = "stu_class")
public class StudentClass implements ID{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_id")
    private Integer id;

    @Column(name = "class_name")
    private String className;

    @OneToMany(mappedBy = "studentClass",cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    @JsonIgnoreProperties({"studentClass","password"})
    private List<Student> students;

    @ManyToMany(mappedBy="classes")
    @JsonIgnoreProperties("classes")
    @org.hibernate.annotations.ForeignKey(name = "none")
    private List<Sheet> sheets;

    @Transient
    private Integer classSize = null;
    //vo 班级人数
    public Integer getClassSize() {
        if(students==null) return null;
        return students.size();
    }

    public List<Sheet> getSheets() {
        return sheets;
    }

    public StudentClass setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
        return this;
    }

    public List<Student> getStudents() {
        return students;
    }

    public StudentClass setStudents(List<Student> students) {
        this.students = students;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public StudentClass setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public StudentClass setClassName(String className) {
        this.className = className;
        return this;
    }

}
