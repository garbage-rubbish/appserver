package com.hkd.model;

import com.hkd.model.po.TpRole;
import com.hkd.model.po.TpUser;
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


    public JwtUser(TpUser user, List<TpRole> roles){
        List<GrantedAuthority> authorities= Lists.newArrayList();
        for(TpRole role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        this.id=user.getId();
        this.username=user.getMjjh();
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
