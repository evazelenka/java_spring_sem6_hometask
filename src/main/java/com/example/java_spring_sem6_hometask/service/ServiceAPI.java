package com.example.java_spring_sem6_hometask.service;

import com.example.java_spring_sem6_hometask.domain.Characters;
import com.example.java_spring_sem6_hometask.domain.Result;

public interface ServiceAPI {
    Characters getAllCharacters();
    Result getCharById(Long id);
}
