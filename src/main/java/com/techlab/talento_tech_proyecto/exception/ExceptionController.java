package com.techlab.talento_tech_proyecto.exception;

import com.techlab.talento_tech_proyecto.dto.ErrorDto;
import com.techlab.talento_tech_proyecto.dto.ErrorValidationDto;
import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> personaNotFound(NotFoundException ex) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.NOT_FOUND.value(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(ClonException.class)
//    public ResponseEntity<?> personaClonada(ClonException ex){
//        ErrorDto err = new ErrorDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> errorInterno(Exception ex) {
        return new ResponseEntity<>(new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> fallaValidacion(MethodArgumentNotValidException ex) {
        //cuando hay varias excepciones ex.getFieldError() devuelve una aleatoria
        //String mensaje = Objects.requireNonNullElse(ex.getFieldError().getDefaultMessage(), "Parametro enviado invalido");
        /*st<String> arrErrores = ex.getAllErrors().stream()
                .map(e->Objects.requireNonNullElse(e.getDefaultMessage(), "Parametro enviado invalido"))
                .toList();
        String mensaje = String.join(", ", arrErrores);
*/

        HashMap<String, String> errores = new HashMap<>();
        ex.getFieldErrors()
                .forEach(field -> errores.put(field.getField(), field.getDefaultMessage()));
        return new ResponseEntity<>(new ErrorValidationDto(HttpStatus.BAD_REQUEST.value() ,errores), HttpStatus.BAD_REQUEST);
    }
}
