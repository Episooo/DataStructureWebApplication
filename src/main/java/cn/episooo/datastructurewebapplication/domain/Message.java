package cn.episooo.datastructurewebapplication.domain;

import javax.persistence.*;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/20 20:20
 * @Description：
 */
@Entity
@Table
public class Message implements ID{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private Integer fromId;

    @Column
    private Integer toId;

    @Column(columnDefinition = "text")
    private String text;

    @Column(columnDefinition = "bigint(20)")
    private long date;

    @Column(columnDefinition = "tinyint")
    private boolean isRead;

    public boolean isRead() {
        return isRead;
    }

    public Message setRead(boolean read) {
        isRead = read;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getFromId() {
        return fromId;
    }

    public Message setFromId(Integer fromId) {
        this.fromId = fromId;
        return this;
    }

    public Integer getToId() {
        return toId;
    }

    public Message setToId(Integer toId) {
        this.toId = toId;
        return this;
    }

    public String getText() {
        return text;
    }

    public Message setText(String text) {
        this.text = text;
        return this;
    }

    public long getDate() {
        return date;
    }

    public Message setDate(long date) {
        this.date = date;
        return this;
    }
}
