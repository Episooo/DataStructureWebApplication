package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 19:59
 * @Description：
 */
@Embeddable
public class SheetQuestionKey implements Serializable {

    @Column
    private Integer sheetId;



    @Column
    private Integer number;

    public Integer getSheetId() {
        return sheetId;
    }

    public SheetQuestionKey setSheetId(Integer sheetId) {
        this.sheetId = sheetId;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public SheetQuestionKey setNumber(Integer number) {
        this.number = number;
        return this;
    }
}
