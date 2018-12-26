package top.kaiccc.kaiboot.user.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 *
 * @author kaiccc
 * @date 2018-12-21 15:46
 */
public class SecurityUser extends org.springframework.security.core.userdetails.User {

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}
