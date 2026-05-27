package ecommerce.ecommerce_project.config;

import ecommerce.ecommerce_project.exeptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler {
    //creating logger
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //general handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception e){
        log.error("exception happened", e);
        ErrorResponse errorResponse= new ErrorResponse("Error happened", "Server Error ", LocalDateTime.now());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
    @ExceptionHandler(InvalidPageSizeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPageSize(Exception e){
        log.error("invalid page size");
        ErrorResponse errorResponse=new ErrorResponse("Invalid Page Size", e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    };
    //missing body exception
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e){
        log.error("body is missing {}", e.getMessage());
//        System.out.println(e.)
        ErrorResponse errorResponse=new ErrorResponse("Body Error", e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    //body error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        log.error("invalid body: {}", e.getMessage());
        ErrorResponse errorResponse=new ErrorResponse("invalid body", "something is wrong with body: "+ Arrays.toString(e.getDetailMessageArguments()), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    //product not found error
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(Exception e){
        log.error("Product not found: {}", e.getMessage());
        ErrorResponse errorResponse=new ErrorResponse("Product not found", e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    //quantity error
    @ExceptionHandler(QuantityException.class)
    public ResponseEntity<ErrorResponse> handleQuantity(Exception e){
        log.error("not enough quantity in store");
        ErrorResponse errorResponse=new ErrorResponse("not enough quantity", e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ErrorResponse> handleEmail(Exception e){
        log.error("Email exception");
        ErrorResponse errorResponse=new ErrorResponse("Email already exists", e.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(UsernameException.class)
    public ResponseEntity<ErrorResponse> handleUsername(Exception e){
      log.error("username exception");
      ErrorResponse errorResponse=new ErrorResponse("Username already exists", e.getMessage(), LocalDateTime.now());
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    };
}
