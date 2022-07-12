package uz.company.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.company.dto.user.UserJwtDto;
import uz.company.entity.User;
import uz.company.exceptions.UniversalException;
import uz.company.repository.UserRepository;
import uz.company.util.ApiResponseObject;
import uz.company.util.SecretKeys;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
private final UserRepository userRepository;
   public String createJWTToken(String userName,String password){
       try {
           String byUserNameAndPassword = userRepository.findByUserNameAndPassword(userName, password);
           ApiResponseObject<UserJwtDto> currentUser =new ObjectMapper().readValue(byUserNameAndPassword, new TypeReference<ApiResponseObject<UserJwtDto>>() {});
           if (!currentUser.getSuccess()){
               throw new UniversalException(currentUser.getMessage(), HttpStatus.resolve(currentUser.getHttpStatus()));
           }else {
               String accessToken="";
               Claims claims=Jwts.claims().setSubject(currentUser.getContent().getUser_name());

               accessToken = Jwts.builder()
                       .setClaims(claims)
                       .setIssuedAt(new Date(System.currentTimeMillis()))
                       .setExpiration(new Date(System.currentTimeMillis() + SecretKeys.accessTokenDate))
                       .signWith(SignatureAlgorithm.HS512, SecretKeys.secretWord).compact();

               claims.put("user",currentUser.getContent());
               claims.put("access_token",accessToken);
               return Jwts.builder()
                       .setClaims(claims)
                       .setIssuedAt(new Date(System.currentTimeMillis()))
                       .setExpiration(new Date(System.currentTimeMillis() + SecretKeys.accessTokenDate))
                       .signWith(SignatureAlgorithm.HS512, SecretKeys.secretWord).compact();

           }

       } catch (Exception e) {
           throw new UniversalException(e.getMessage(), HttpStatus.CONFLICT);
       }

   }

    public boolean validationToken(String token) {
        try {
            token = token.substring(7);
            Jwts.parser().setSigningKey(SecretKeys.secretWord).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Claims body = Jwts.parser().setSigningKey(SecretKeys.secretWord).parseClaimsJws(token.substring(7)).getBody();
        return body.getSubject();
    }
}
