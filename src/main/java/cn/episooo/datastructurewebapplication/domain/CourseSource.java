package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 19:17
 * @Description：
 */
@Entity
@Table
public class CourseSource implements ID{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer courseId;

    @Column
    private String fileName;

    @Column
    private String filePath;

    public CourseSource() {
    }

    public CourseSource(Integer courseId, String fileName, String filePath) {
        this.courseId = courseId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public CourseSource setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public CourseSource setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public CourseSource setCourseId(Integer courseId) {
        this.courseId = courseId;
        return this;
    }

    public String getFilePath() {
        return filePath;
    }

    public CourseSource setFilePath(String filePath) {
        this.filePath = filePath;
        return this;
    }
}
