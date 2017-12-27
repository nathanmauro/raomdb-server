package com.ra.omdb.rest.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SearchController {

    private static String omdbSearch = "http://www.omdbapi.com/?apikey=155ebdfc&s=";

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/rest/search/{title}", method = RequestMethod.GET, produces = "application/json")
    public String search(@PathVariable("title") String title) {
        return this.restTemplate.getForObject(omdbSearch + title, String.class);
    }
}
