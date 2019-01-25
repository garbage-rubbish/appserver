package cn.com.cybertech.sdly.model;

import cn.com.cybertech.sdly.model.po.User;
import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by huangkd on 2019/1/24.
 */
public class JwtUser implements UserDetails {

    private static final long serialVersionUID = -780584288640773163L;
    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public JwtUser(User user){
        List<String> roles = user.getRoles();
        List<GrantedAuthority> authorities= Lists.newArrayList();
        for(String role:roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        this.id=user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.authorities=authorities;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
