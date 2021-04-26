package cn.episooo.datastructurewebapplication.utils.security;

import cn.episooo.datastructurewebapplication.utils.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

/**
 * @Author ：Ep
 * @Date ：Created in 2021/1/7 21:28
 * @Description： 使用构造器生成jwt对象，通过refresh进行更新，toString返回jwt字符串，parseToken将字符串解析为jwt对象
 */
public class JwtObject {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private static String keyWord = "U47hk5guytCI7pB0xN1pNBMPGxhdi8u5x1ue7aZj3CMLgHtGan9Ouyr7h2MSI4al";
    private static Key key = Keys.hmacShaKeyFor(keyWord.getBytes(StandardCharsets.UTF_8));

    private TokenType type;//指定用户的类别与过期时间
    private Integer userId;//用户唯一标识码
    private Date expiredTime;//用户过期时刻

    public JwtObject(TokenType type, Integer userId) {
        this.type = type;
        this.userId = userId;
        expiredTime = new Date(System.currentTimeMillis() + type.getPeriod());
    }

    public static Key getKey() {
        return key;
    }

    public TokenType getType() {
        return type;
    }

    public JwtObject setType(TokenType type) {
        this.type = type;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public JwtObject setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public JwtObject setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
        return this;
    }
    /*
     * @Description : 将jwt对象转为token，在有效期只有一半时先刷新有效期
     * @param ：
     * @Return : java.lang.String
     */
    public String toJwtString() {
        if(expiredTime.getTime() - type.getPeriod()/2 < System.currentTimeMillis()){
            refresh();
        }
        String playload = JSONObject.toJSONString(this);
        return Jwts.builder()
                .setSubject(playload)
                .signWith(key)
                .setExpiration(expiredTime)
                .compact();
    }

    public JwtObject refresh() {
        expiredTime = new Date(System.currentTimeMillis() + type.getPeriod());
        return this;
    }

    public static JwtObject parseToken(String token) {
        if (StringUtil.isEmpty(token)) {
            return null;
        }
        JwtObject jwtObject;
        System.out.println("notEmpty--------------");
        try {
            String jwt = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            jwtObject = JSON.parseObject(jwt, JwtObject.class);
        }catch (Exception e){
            System.out.println("exception---------------");
            return null;
        }
        System.out.println(jwtObject);
        return jwtObject;
    }


}
