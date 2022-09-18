package company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionHandler extends Exception {
    public ResourceNotFoundExceptionHandler(String message) {
        super(message);
    }
}