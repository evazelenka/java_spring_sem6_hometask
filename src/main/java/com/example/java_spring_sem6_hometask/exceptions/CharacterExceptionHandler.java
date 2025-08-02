package com.example.java_spring_sem6_hometask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Обработчик исключения CharacterNotFoundException.
 */
@ControllerAdvice
public class CharacterExceptionHandler {

    /**
     * Метод обрабатывает исключение CharacterNotFoundException.
     * @param e исключение.
     * @return ответ с сообщением об ошибке и статусом "NOT_FOUND".
     */
    @ExceptionHandler(CharacterNotFoundException.class)
    ResponseEntity<String> handleCharacterNotFoundException(CharacterNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
