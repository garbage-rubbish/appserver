import cn.com.cybertech.sdly.utils.JwtTokenUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import org.junit.Test;

import java.util.*;

/**
 * Created by huangkd on 2019/1/24.
 */

public class Test1 {
    @Test
    public void testCreateToken(){
        HashMap<String, Object> map= Maps.newHashMap();
        ArrayList<String> roles= Lists.newArrayList();
        roles.add("role_mj");
        roles.add("role_ld");
        map.put("username","haha");
        map.put("roles",roles);
        String token=JwtTokenUtil.createToken(map);
        System.out.println(token);
        String s=new String(Base64.getDecoder().decode("eyJyb2xlcyI6WyJyb2xlX21qIiwicm9sZV9sZCJdLCJleHAiOjE1NDgzNDUyMTksInVzZXJuYW1lIjoiaGFoYSJ9"));
        String s1=new String(Base64.getEncoder().encode("{\"roles\":[\"role_mj\",\"role_ld\"],\"exp\":1648345219,\"username\":\"haha\"}".getBytes()));
        System.out.println(s1);
        System.out.println(s);
    }
    @Test
    public void testParseToken(){
        Claims claimsFromToken = JwtTokenUtil.getClaimsFromToken("eyJhbGciOiJIUzUxMiJ9.eyJyb2xlcyI6WyJyb2xlX21qIiwicm9sZV9sZCJdLCJleHAiOjE2NDgzNDUyMTksInVzZXJuYW1lIjoiaGFoYSJ9.1b_XMquARKpd-dl3X3nq-xGGTf0pdT-gR39TWDVVIthWN9cEGakziqGcRUme5BcEaiU5-0PETXNKtR8OflcCLg");
        String username= (String) claimsFromToken.get("username");
        List<String> roles= (List<String>) claimsFromToken.get("roles");
        System.out.println(username);
        System.out.println(roles);

    }
}
