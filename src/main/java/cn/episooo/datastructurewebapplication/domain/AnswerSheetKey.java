package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:11
 * @Description：
 */
@Embeddable
public class AnswerSheetKey implements Serializable {

    @Column
    private Integer stuId;

    @Column
    private Integer sheetId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerSheetKey that = (AnswerSheetKey) o;
        return Objects.equals(stuId, that.stuId) &&
                Objects.equals(sheetId, that.sheetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId, sheetId);
    }

    public Integer getStuId() {
        return stuId;
    }

    public AnswerSheetKey setStuId(Integer stuId) {
        this.stuId = stuId;
        return this;
    }

    public Integer getSheetId() {
        return sheetId;
    }

    public AnswerSheetKey setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
        return this;
    }
}
