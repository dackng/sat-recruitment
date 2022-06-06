package sat.recruitment.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler({ MissingServletRequestParameterException.class, MethodArgumentNotValidException.class, HttpRequestMethodNotSupportedException.class
		, HttpMessageNotReadableException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(Exception ex) {
		String message;
        if(ex instanceof MethodArgumentNotValidException) {
        	List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult()
        			.getFieldErrors();
        	message = fieldErrors.stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(" "));
        }else {
        	message = "A general error has ocurred";
        }
        
        return new ResponseEntity<>(new ErrorResponse(message)
        		, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value = {UserDuplicatedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception ex) {
		return new ResponseEntity<>(new ErrorResponse(ex.getMessage())
        		, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    	return new ResponseEntity<>(new ErrorResponse("A general error has ocurred")
        		, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
