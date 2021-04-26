package cn.episooo.datastructurewebapplication.vo;

import cn.episooo.datastructurewebapplication.domain.AnswerSheet;
import cn.episooo.datastructurewebapplication.domain.Sheet;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/3/21 19:42
 * @Description：
 */
public class AnsSheetVO {
    private AnswerSheet ansAheet;
    private Sheet sheet;
    private boolean status;
    private boolean isCorrected;

    public AnsSheetVO(){};
    public AnsSheetVO(AnswerSheet ansAheet, Sheet sheet) {
        this.ansAheet = ansAheet;
        this.sheet = sheet;
        this.status = ansAheet!=null;
        if(ansAheet!=null){
            this.isCorrected = ansAheet.isCorrected();
        }

    }

    public boolean isStatus() {
        return status;
    }

    public AnsSheetVO setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public boolean isCorrected() {
        return isCorrected;
    }

    public AnsSheetVO setCorrected(boolean corrected) {
        isCorrected = corrected;
        return this;
    }

    public AnswerSheet getAnsAheet() {
        return ansAheet;
    }

    public AnsSheetVO setAnsAheet(AnswerSheet ansAheet) {
        this.ansAheet = ansAheet;
        return this;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public AnsSheetVO setSheet(Sheet sheet) {
        this.sheet = sheet;
        return this;
    }
}
