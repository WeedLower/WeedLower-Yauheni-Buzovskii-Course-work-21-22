package com.example.backend.controller;



import com.example.backend.entity.ItemsEntity;
import com.example.backend.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Controller
@RestController
@RequestMapping("api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/{searchString}", method = RequestMethod.GET)
    public List<ItemsEntity> search(@PathVariable(name="searchString") String searchString) {
            return searchService.searchItems(searchString);
    }
}
