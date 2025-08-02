package com.example.java_spring_sem6_hometask.exceptions;

public class CharacterNotFoundException extends RuntimeException{
    /**
     * Исключение, которое выбрасывается когда персонаж не найден.
     * @param message сообщение об ошибке.
     */
    public CharacterNotFoundException(String message) {
        super(message);
    }
}
