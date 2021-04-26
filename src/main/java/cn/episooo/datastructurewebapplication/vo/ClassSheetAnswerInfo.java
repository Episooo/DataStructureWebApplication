package cn.episooo.datastructurewebapplication.vo;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/11 14:02
 * @Description：
 */
public class ClassSheetAnswerInfo {
    private int number;
    private String subject;
    private double avgScore;
    private double total;

    public int getNumber() {
        return number;
    }

    public ClassSheetAnswerInfo setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ClassSheetAnswerInfo setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public ClassSheetAnswerInfo setAvgScore(double avgScore) {
        this.avgScore = avgScore;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public ClassSheetAnswerInfo setTotal(double total) {
        this.total = total;
        return this;
    }
}
