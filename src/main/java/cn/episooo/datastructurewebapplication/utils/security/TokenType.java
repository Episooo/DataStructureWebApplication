package cn.episooo.datastructurewebapplication.utils.security;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/7 20:53
 * @Description： type为用户类型，period指定令牌有效时间，单位小时。
 */
public enum TokenType {
    Teacher(0,24),
    Student(1,24);

    //时间单位：小时
    private static final long TIME_UNIT = 60L * 60L * 1000;

    private int type ;
    private long period;


    TokenType(int type, long period) {
        this.type = type;
        this.period = period;
    }

    public int getType() {
        return type;
    }

    public TokenType setType(int type) {
        this.type = type;
        return this;
    }

    public long getPeriod() {
        return period * TIME_UNIT;
    }


}
