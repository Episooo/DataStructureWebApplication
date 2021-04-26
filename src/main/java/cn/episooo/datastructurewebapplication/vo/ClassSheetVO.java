package cn.episooo.datastructurewebapplication.vo;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/4/11 13:22
 * @Description：
 */
public class ClassSheetVO {
    private int studentUsername;
    private String studentName;
    private String subject;
    private double score;
    private double total;
    private double rate;

    public int getStudentUsername() {
        return studentUsername;
    }

    public ClassSheetVO setStudentUsername(int studentId) {
        this.studentUsername = studentId;
        return this;
    }

    public String getStudentName() {
        return studentName;
    }

    public ClassSheetVO setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ClassSheetVO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public double getScore() {
        return score;
    }

    public ClassSheetVO setScore(double score) {
        this.score = score;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public ClassSheetVO setTotal(double total) {
        this.total = total;
        return this;
    }

    public double getRate() {
        return rate;
    }

    public ClassSheetVO setRate(double rate) {
        this.rate = rate;
        return this;
    }
}
