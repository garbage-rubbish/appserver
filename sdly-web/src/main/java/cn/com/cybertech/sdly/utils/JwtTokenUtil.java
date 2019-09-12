package cn.com.cybertech.sdly.utils;

import cn.com.cybertech.sdly.constants.Constants;
import cn.com.cybertech.sdly.model.po.TpRole;
import cn.com.cybertech.sdly.model.po.TpUser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huangkd on 2019/1/24.
 */
public class JwtTokenUtil {



    public static String createToken(Map<String,Object> map){
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, Constants.TOKEN_SECRET).compact();
    }
    public static String createToken(UserDetails userDetails){
        Map<String,Object> map= Maps.newHashMap();
        map.put(Constants.TOKEN_CLAIMS_USERNAME,userDetails.getUsername());
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, Constants.TOKEN_SECRET).compact();
    }
    public static String createToken(String userDetails){
        Map<String,Object> map= Maps.newHashMap();
        map.put(Constants.TOKEN_CLAIMS_USERNAME,userDetails);
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, Constants.TOKEN_SECRET).compact();
    }
    private static Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public static Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    static Date getExpirationDate(){
        return new Date(System.currentTimeMillis()+(1000*60*Constants.TOKEN_EXPIRATION_TIME));
    }

    public static Claims getClaimsFromToken(String token){
        return  Jwts.parser().setSigningKey(Constants.TOKEN_SECRET).parseClaimsJws(token).getBody();
    }

    public static String getUsernameFromToken(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        return (String) claimsFromToken.get(Constants.TOKEN_CLAIMS_USERNAME);
    }

}
