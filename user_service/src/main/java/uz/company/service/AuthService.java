package uz.company.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.company.dto.user.UserLoginDto;
import uz.company.entity.User;
import uz.company.exceptions.UniversalException;
import uz.company.repository.UserRepository;
import uz.company.service.JwtService;
import uz.company.util.ApiResponseObject;

import java.lang.reflect.Type;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            String byUserName = userRepository.findByUserName(username);
            ApiResponseObject<User>userApiResponseObject=new ObjectMapper().readValue(byUserName, new TypeReference<ApiResponseObject<User>>() {});
            if (userApiResponseObject.getSuccess()){
                return userApiResponseObject.getContent();
            }else
                throw new UniversalException(userApiResponseObject.getMessage(),HttpStatus.resolve(userApiResponseObject.getHttpStatus()));
        }catch (Exception e){
            throw new UniversalException("loadUserByUsername exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> loginUsrNameAndPassword(UserLoginDto loginDto) {
        String jwtToken = jwtService.createJWTToken(loginDto.getUser_name(), loginDto.getPassword());
        return ResponseEntity.ok(jwtToken);
    }

    public ResponseEntity<?> generateTokenWithAccessToken(String access_token) {
        String jwtToken =jwtService.createJWTTokenWithAccessToken(access_token);
        return ResponseEntity.ok(jwtToken);
    }
}
