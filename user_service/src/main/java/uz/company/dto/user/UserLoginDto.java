package uz.company.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDto {
    @NotNull(message = " userName must not be null ")
    private String user_name; //unique
    @NotNull(message = " password must not be null ")
    private String password;
}
