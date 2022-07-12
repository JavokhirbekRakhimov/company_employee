
package uz.company.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateDto {
    @NotNull(message = " id must not be null ")
private Short id;
    @NotNull(message = " rank must not be null ")
    private Short rank;
}
