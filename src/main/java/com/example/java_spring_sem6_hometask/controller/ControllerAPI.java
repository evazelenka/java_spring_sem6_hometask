package com.example.java_spring_sem6_hometask.controller;

import com.example.java_spring_sem6_hometask.domain.Characters;
import com.example.java_spring_sem6_hometask.service.ServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chars")
public class ControllerAPI {

    @Autowired
    private ServiceAPI serviceAPI;

    @GetMapping()
    public String getCharacters(Model model){
        Characters all = serviceAPI.getAllCharacters();
        model.addAttribute("chars", all.getResults());
        return "characters.html";
    }

    @GetMapping("/{id}")
    public String getChar(@PathVariable Long id, Model model){
        model.addAttribute("char", serviceAPI.getCharById(id));
        return "char.html";
    }
}
