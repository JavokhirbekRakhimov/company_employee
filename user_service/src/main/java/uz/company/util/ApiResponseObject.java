package uz.company.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseObject<T>{
    private String message;
    private Boolean success;
    private Integer httpStatus;
    private T content;
}
