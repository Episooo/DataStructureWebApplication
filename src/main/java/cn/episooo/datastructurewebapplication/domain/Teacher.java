package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/7 21:20
 * @Description：
 */
@Entity
@Table
public class Teacher implements ID {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer id;

    @Column(name = "teacher_name")
    private String name;

    @Column(name = "teacher_username")
    private String username;

    @Column(name = "teacher_password")
    private String password;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Teacher setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Teacher setName(String name) {
        this.name = name;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Teacher setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Teacher setPassword(String password) {
        this.password = password;
        return this;
    }
}
