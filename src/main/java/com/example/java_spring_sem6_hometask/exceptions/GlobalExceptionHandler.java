package com.example.java_spring_sem6_hometask.exceptions;

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
     * Метод обрабатывает исключение CharacterNotFoundException, то есть исключение,
     * связанное с указанием неверных данных персонажа.
     * @param ex исключение.
     * @param model модель для передачи атрибута в шаблон Thymeleaf.
     * @return html страницу с сообщением об ошибке.
     */
    @ExceptionHandler(CharacterNotFoundException.class)
    public String handleCharacterNotFoundException(CharacterNotFoundException ex, Model model) {
        model.addAttribute("errorCode", 404);
        model.addAttribute("errorMessage", ex.getMessage());
        return "error.html";
    }

    /**
     * Метод обрабатывает исключение HttpClientErrorException, то есть исключение на стороне клиента.
     * @param ex исключение.
     * @param model модель для передачи атрибута в шаблон Thymeleaf.
     * @return html страницу с сообщением об ошибке.
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientError(HttpClientErrorException ex, Model model) {
        model.addAttribute("errorCode", ex.getStatusCode());
        model.addAttribute("errorMessage", "Ошибка при обращении к внешнему API: " + ex.getStatusCode());
        return "error.html"; // это имя Thymeleaf-шаблона
    }

    /**
     * Метод обрабатывает исключение RestClientException, то есть исключение на стороне внешнего сервиса.
     * @param ex исключение.
     * @param model модель для передачи атрибута в шаблон Thymeleaf.
     * @return html страницу с сообщением об ошибке.
     */
    @ExceptionHandler(RestClientException.class)
    public String handleRestClientException(RestClientException ex, Model model) {
        model.addAttribute("errorMessage", "Внешний сервис недоступен. Попробуйте позже.");
        return "error.html";
    }

    /**
     * Метод обрабатывает исключения на стороне сервиса веб-приложения.
     * @param ex исключение.
     * @param model модель для передачи атрибута в шаблон Thymeleaf.
     * @return html страницу с сообщением об ошибке.
     */
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "Внутренняя ошибка: " + ex.getMessage());
        return "error.html";
    }
}
