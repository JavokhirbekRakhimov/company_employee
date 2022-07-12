package uz.company.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.company.entity.Company;
import java.util.Set;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserJwtDto {
    private UUID id;
    private String first_name;
    private String last_name;
    private String user_name; //unique
    private String email; //unique
    private String phone;  //unique
    private Set<Company> companies;
}
