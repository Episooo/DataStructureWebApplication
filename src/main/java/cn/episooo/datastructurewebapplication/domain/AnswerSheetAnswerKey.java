package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:16
 * @Description：
 */
@Embeddable
public class AnswerSheetAnswerKey implements Serializable {

    @Column
    private Integer sheetId;

    @Column
    private Integer stuId;

    @Column
    private Integer ansNumber;

    @Override
    public String toString() {
        return "AnswerSheetAnswerKey{" +
                "sheetId=" + sheetId +
                ", stuId=" + stuId +
                ", ansNumber=" + ansNumber +
                '}';
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public AnswerSheetAnswerKey setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    public Integer getStuId() {
        return stuId;
    }

    public AnswerSheetAnswerKey setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public Integer getAnsNumber() {
        return ansNumber;
    }

    public AnswerSheetAnswerKey setAnsNumber(Integer ansNumber) {
        this.ansNumber = ansNumber;
        return this;
    }
}
