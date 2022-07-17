package uz.company.config.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import reactor.util.annotation.Nullable;
import uz.company.service.AuthService;
import uz.company.service.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class AuthenticationJwtTokenFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal( @Nullable HttpServletRequest request, @Nullable HttpServletResponse response,@Nullable FilterChain filterChain) throws ServletException, IOException {
        assert request != null;
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer")) {
            if (jwtService.validationToken(token)) {
                String username = jwtService.getUsername(token);
                UserDetails userDetails = authService.loadUserByUsername(username);
                if (!Objects.isNull(userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        assert filterChain != null;
        filterChain.doFilter(request, response);
    }
}
