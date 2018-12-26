package top.kaiccc.kaiboot.user.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.kaiccc.kaiboot.admin.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtil {

    /**
     * jwt 超期时间
     */
    @Value("${jwt.expiration}")
    private Long jwtExpiration;
    /**
     * jwt 秘钥
     */
    @Value("${jwt.key}")
    private String jwtKet;

    /**
     * 用户登录成功后生成Jwt
     * 使用Hs256算法
     *
     * @param user      登录成功的user对象
     * @return
     */
    public String createJWT(User user) {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<String, Object>(3);
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());

        //生成签发人
        String subject = user.getUsername();

        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId(UUID.randomUUID().toString())
                //iat: jwt的签发时间
                .setIssuedAt(now)
                //代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setSubject(subject)
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, jwtKet)
                //设置过期时间
                .setExpiration(new Date(nowMillis + jwtExpiration));

        return builder.compact();
    }

    /**
     * Token的解密
     * @param token 加密后的token
     * @return
     */
    public Claims parseJwt(String token) {

        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(jwtKet)
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();

        return claims;
    }

    /**
     * Token 解密 获取username
     * @param token
     * @return username
     */
    public String parseJwtUsername(String token) {
        return this.parseJwt(token).get("username").toString();
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public Boolean isVerify(String token) {
        try {
            this.parseJwt(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
