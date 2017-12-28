package com.ra.omdb.rest.movie;

import com.ra.omdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/movie", produces = "application/json")
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping(value = "/{imdbId}", method = RequestMethod.GET)
    public MovieVo getOne(@PathVariable String imdbId) {
        return this.movieService.getOne(imdbId);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public MovieVo save(@RequestBody MovieVo vo) {
        return this.movieService.entityToVo(this.movieService.voToEntity(vo));
    }
}
