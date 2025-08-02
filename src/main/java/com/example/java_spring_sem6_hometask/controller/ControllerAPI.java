package com.example.java_spring_sem6_hometask.controller;

import com.example.java_spring_sem6_hometask.domain.Characters;
import com.example.java_spring_sem6_hometask.service.ServiceAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerAPI {

    // region Fields
    @Autowired
    private ServiceAPI serviceAPI;
    // endregion

    // region Methods with mapping

    /**
     * Метод для получения страницы с персонажами.
     * @param page номер страницы
     * @param model модель для передачи атрибутов в шаблон thymeleaf
     * @return возвращет имя thymeleaf шаблона
     */
    @GetMapping("/characters")
    public String getCharactersPage(@RequestParam(defaultValue = "1") int page, Model model) {
        Characters characters = serviceAPI.getCharactersByPageNumber(page);

        model.addAttribute("chars", characters.getResults());
        model.addAttribute("currentPage", page);

        // Получаем номер следующей страницы из URL characters.getInfo().getNext()
        Integer nextPage = null;
        if (characters.getInfo().getNext() != null) {
            String nextUrl = characters.getInfo().getNext();
            nextPage = extractPageNumberFromUrl(nextUrl);
        }

        Integer prevPage = null;
        if (characters.getInfo().getPrev() != null) {
            String prevUrl = characters.getInfo().getPrev();
            prevPage = extractPageNumberFromUrl(prevUrl);
        }

        model.addAttribute("nextPage", nextPage);
        model.addAttribute("prevPage", prevPage);

        return "chars.html"; // имя thymeleaf шаблона
    }

    /**
     * Метод для получения страницы персонажа.
     * @param id идентификатор персонажа
     * @param model модель для передачи атрибутов в шаблон thymeleaf
     * @return возвращает имя thymeleaf шаблона
     */
    @GetMapping("/{page}/{id}")
    public String getChar(@PathVariable int page, @PathVariable Long id, Model model){
        model.addAttribute("char", serviceAPI.getCharById(id));
        model.addAttribute("page", page);
        return "char.html";
    }

    /**
     * Метод для получения страницы персонажей с определенными характеристиками.
     * @param name имя персонажа для фильтрации
     * @param status статус персонажа для фильтрации
     * @param page текущая страница
     * @param model модель для передачи атрибутов в шаблон thymeleaf
     * @return возвращает имя thymeleaf шаблона
     */
    @GetMapping("/filter")
    public String filterCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {
        Characters characters = serviceAPI.getFilteredCharacters(name, status,page);

        model.addAttribute("chars", characters.getResults());
        model.addAttribute("currentPage", page);
        model.addAttribute("prevPage", extractPageNumberFromUrl(characters.getInfo().getPrev()));
        model.addAttribute("nextPage", extractPageNumberFromUrl(characters.getInfo().getNext()));

        // Для отображения текущих значений в форме
        model.addAttribute("name", name);
        model.addAttribute("status", status);

        return "chars";
    }
    // endregion

    // region Private methods

    /**
     * Приватный метод для получения идентификатора персонажа из ссылки
     * @param url ссылка на данные о персонаже
     * @return возвращает идентификатор персонажа
     */
    private Integer extractPageNumberFromUrl(String url) {
        // Предполагаем, что URL имеет вид https://rickandmortyapi.com/api/character?page=X
        try {
            String query = url.split("\\?")[1]; // берём часть после ?
            for (String param : query.split("&")) {
                String[] keyValue = param.split("=");
                if (keyValue[0].equals("page")) {
                    return Integer.parseInt(keyValue[1]);
                }
            }
        } catch (Exception e) {
            // игнорируем ошибку, возвращаем null
        }
        return null;
    }
    // endregion
}
