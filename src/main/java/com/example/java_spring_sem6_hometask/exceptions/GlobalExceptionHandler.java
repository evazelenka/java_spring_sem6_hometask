package com.example.java_spring_sem6_hometask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;


/**
 * Обработчик исключений.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Метод обрабатывает исключение CharacterNotFoundException.
     * @param ex исключение.
     * @return ответ с сообщением об ошибке и статусом "NOT_FOUND".
     */
    @ExceptionHandler(CharacterNotFoundException.class)
    public String handleCharacterNotFoundException(CharacterNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error.html";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientError(HttpClientErrorException ex, Model model) {
        model.addAttribute("errorMessage", "Ошибка при обращении к внешнему API: " + ex.getStatusCode());
        return "error.html"; // это имя Thymeleaf-шаблона
    }

    @ExceptionHandler(RestClientException.class)
    public String handleRestClientException(RestClientException ex, Model model) {
        model.addAttribute("errorMessage", "Внешний сервис недоступен. Попробуйте позже.");
        return "error.html";
    }

    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Внутренняя ошибка: " + ex.getMessage());
        return "error.html";
    }
}
