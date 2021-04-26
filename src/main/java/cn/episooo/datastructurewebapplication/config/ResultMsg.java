package cn.episooo.datastructurewebapplication.config;

import java.io.Serializable;

/**
 * @Author ：Ep
 * @Date ：Created in 2020/6/14 20:50
 * @Description：
 */
public class ResultMsg implements Serializable {
    private int code;
    private String msg;
    private Object data;
    public ResultMsg() {
    }

    public ResultMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }



    public int getCode() {
        return code;
    }

    public ResultMsg setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultMsg setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    public static ResultMsg successResult() {
        return new ResultMsg(1,"success");
    }

    public static ResultMsg successResult(Object data){
        return new ResultMsg(1,"success",data);
    }

    public static ResultMsg failedResult(String msg){
        return new ResultMsg(-1,msg);
    }

    public static ResultMsg failedResult(int code, String msg){
        return new ResultMsg(code,msg);
    }
}
