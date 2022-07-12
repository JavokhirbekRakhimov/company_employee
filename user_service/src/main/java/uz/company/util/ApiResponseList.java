package uz.company.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseList<T>{
    private String message;
    private Boolean success;
    private Integer httpStatus;
    private List<T> content;
    private Integer size;
    private Long totalElements;
    private Integer page;
}
