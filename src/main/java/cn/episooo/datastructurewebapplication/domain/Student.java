package cn.episooo.datastructurewebapplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/7 20:08
 * @Description：
 */
@Entity
@Table
public class Student implements ID {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "student_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "class_id")
    @JsonIgnoreProperties("students")
    private StudentClass studentClass;

    @Column(name = "student_username")
    private String username;

    @Column(name = "student_password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Student setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public Student setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Student setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Student setPassword(String password) {
        this.password = password;
        return this;
    }

}
