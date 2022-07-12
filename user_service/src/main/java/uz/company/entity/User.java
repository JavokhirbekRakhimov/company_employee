package uz.company.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.company.exceptions.UniversalException;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    private UUID id;
    private String first_name;
    private String last_name;
    private String user_name; //unique
    private String password;
    private String email; //unique
    private Boolean verification_email;
    private Boolean active;
    private String phone;  //unique
    private Set<Company>companies;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (Company userRole : companies) {
            try {
                authorities.add(new SimpleGrantedAuthority(new ObjectMapper().writeValueAsString(userRole)));
            } catch (JsonProcessingException e) {
                throw new UniversalException("GrantedAuthority parse to json excetion", HttpStatus.CONFLICT);
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return user_name;
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
        return active;
    }
}
