package midianet.tripmanager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(EntityNotFoundException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(ErrorResponse.builder()
                .message("Registro não encontrado")
                .details(List.of(e.getLocalizedMessage())).build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(ErrorResponse.builder()
                .message("Ocorreu um erro de Integridade Referêncial do Repositório")
                .details(List.of(e.getLocalizedMessage())).build(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(ErrorResponse.builder()
                .message("Ocorreu um erro de Violação de Regra do Repositório")
                .details(List.of(e.getLocalizedMessage())).build(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(Exception e, WebRequest request) {
        log.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(ErrorResponse.builder()
                .message("Ocorreu um erro Não Esperado")
                .details(List.of(e.getLocalizedMessage())).build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .message("Ocorreu um erro de Validação nos Dados")
                .details(e.getBindingResult().getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.toList())).build(), HttpStatus.NOT_ACCEPTABLE);
    }

}
