package cn.zing.boot.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import sun.misc.BASE64Encoder;

import javax.crypto.SecretKey;

/**
 * @description:
 * @author: dcy
 * @create: 2023-07-07 21:16
 */
public class JwtUtil {


    private static SecretKey generalKey(String key) {

        String encode = new BASE64Encoder().encode(key.getBytes());
        return Keys.hmacShaKeyFor(encode.getBytes());
    }

    /**
     * 生成JWT
     * <p>
     * <p>
     * JWT分成3部分：1.头部（header),2.载荷（payload, 类似于飞机上承载的物品)，3.签证（signature)
     * <p>
     * 加密后这3部分密文的字符位数为：
     * 1.头部（header)：36位，Base64编码
     * 2.载荷（payload)：没准，BASE64编码
     * 3.签证（signature)：43位，将header和payload拼接生成一个字符串，
     * 使用HS256算法和我们提供的密钥（secret,服务器自己提供的一个字符串），
     * 对str进行加密生成最终的JWT
     */
    public static String createJWT(String subject, String key) {

        JwtBuilder builder = getJwtBuilder(subject, StringUtils.uuid(), key);
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject,String uuid, String key) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey(key);
        return Jwts.builder()
                .setId(uuid)//设置id
                .setSubject(subject)//设置用户名
                .setIssuer("dcyyy")
                .signWith(secretKey, signatureAlgorithm);//设置签名使用的签名算法和签名使用的秘钥

    }



    /**
     * 解析JWT
     */
    public static Claims parseJWT(String jwt, String key) {
        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(generalKey(key)).build();
        return jwtParser.parseClaimsJws(jwt).getBody();

    }

    /**
     * 刷新token
     */
    public static void refreshToken(String userId) {


    }


}


