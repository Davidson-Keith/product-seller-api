package au.com.keithdavidson.productsellerapi.security;

import au.com.keithdavidson.productsellerapi.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {
    private Long id;
    private String username;
    transient private String password; // transient: doesn't show up on serialized places.
    transient private User user; // Not best practice. Demonstration only. User only for login, don't use for JWT.
    private Set<GrantedAuthority> authorities;

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
        return true; // Default. Handled in JWT.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Default. Handled in JWT.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Default. Handled in JWT.
    }

    @Override
    public boolean isEnabled() {
        return true; // Default. Handled in JWT.
    }
}
