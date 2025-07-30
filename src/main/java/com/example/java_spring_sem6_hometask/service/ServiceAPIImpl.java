package com.example.java_spring_sem6_hometask.service;

import com.example.java_spring_sem6_hometask.domain.Characters;
import com.example.java_spring_sem6_hometask.domain.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@Service
public class ServiceAPIImpl implements ServiceAPI{

    @Autowired
    private RestTemplate template;

    @Autowired
    private HttpHeaders headers;

    private static final String CHARACTER_API = "https://rickandmortyapi.com/api/character";

    @Override
    public Characters getAllCharacters() {
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> response = template.exchange(CHARACTER_API, HttpMethod.GET, entity, Characters.class);
        return response.getBody();
    }

    @Override
    public Result getCharById(Long id){
        ResponseEntity<Result> response
                = template.getForEntity(CHARACTER_API + "/" + id.toString(), Result.class);
        return response.getBody();

    }

    @Override
    public Characters getCharactersByPageNumber(int page) {
        String url = CHARACTER_API + "?page=" + page;
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Characters> response = template.exchange(url, HttpMethod.GET, entity, Characters.class);
        return response.getBody();
    }

    @Override
    public Characters getFilteredCharacters(String name, String status, int page) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://rickandmortyapi.com/api/character")
                .queryParam("page", page);

        if (name != null && !name.isBlank()) {
            builder.queryParam("name", name);
        }
        if (status != null && !status.isBlank()) {
            builder.queryParam("status", status);
        }

        String url = builder.toUriString();
        System.out.println(url);
        ResponseEntity<Characters> response = template.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), Characters.class);
        return response.getBody();
    }
}
