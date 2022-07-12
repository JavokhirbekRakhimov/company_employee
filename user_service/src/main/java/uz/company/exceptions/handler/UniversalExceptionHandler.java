package uz.company.exceptions.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.company.exceptions.UniversalException;

@ControllerAdvice
public  class UniversalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = UniversalException.class)
    public ResponseEntity<?> exceptionHandler(UniversalException e, WebRequest request) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
