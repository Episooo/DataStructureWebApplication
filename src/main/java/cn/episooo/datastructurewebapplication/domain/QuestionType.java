package cn.episooo.datastructurewebapplication.domain;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/2/17 9:59
 * @Description：
 */
public enum QuestionType {
    OPTIONAL(0),UN_OPTIONAL(1);
    private int type;

    QuestionType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public QuestionType setType(int type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }
}
