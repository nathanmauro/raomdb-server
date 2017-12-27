package com.ra.omdb.rest.movie;

import com.ra.omdb.data.movie.Movie;
import com.ra.omdb.data.movie.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/movie", produces = "application/json")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(value = "/{imdbId}", method = RequestMethod.GET)
    public MovieVo getOne(@PathVariable String imdbId) {
        Movie movie = this.movieRepository.getOne(imdbId);

        return this.entityToVo(movie);
    }

    private MovieVo entityToVo(Movie movie) {
        MovieVo vo = new MovieVo();
        vo.setImdbId(movie.getImdbId());
        vo.setListName(movie.getListName());
        vo.setName(movie.getName());
        vo.setRating(movie.getRating());
        return vo;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public MovieVo save(@RequestBody Movie movie) {
        this.movieRepository.save(movie);

        return this.entityToVo(movie);
    }
}
