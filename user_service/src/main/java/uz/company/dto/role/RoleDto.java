
package uz.company.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @NotNull(message = " id must not be null ")
private Short id;
    @NotNull(message = " name must not be null ")
    private String name;
    @NotNull(message = " rank must not be null ")
    private Short rank;
}
