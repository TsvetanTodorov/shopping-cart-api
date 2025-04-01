package shopapi.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public String handleException() {

        return "Some text for the client!";
    }

}
