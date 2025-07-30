package com.example.java_spring_sem6_hometask.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Characters {
    public Info info;
    List<Result> results;
}
