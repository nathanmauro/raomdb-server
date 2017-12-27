package com.ra.omdb.service;

import com.ra.omdb.data.movie.Movie;
import com.ra.omdb.data.movie.MovieRepository;
import com.ra.omdb.rest.movie.MovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public MovieVo getOne(String imdbId) {
        Movie movie = this.movieRepository.getOne(imdbId);

        return this.entityToVo(movie);
    }

    public Movie voToEntity(MovieVo vo) {
        Movie movie = new Movie();
        movie.setImdbId(vo.getImdbId());
        movie.setName(vo.getName());
        movie.setRating(vo.getRating());
        this.movieRepository.save(movie);

        return movie;
    }

    public MovieVo entityToVo(Movie movie) {
        MovieVo vo = new MovieVo();
        vo.setImdbId(movie.getImdbId());
        vo.setName(movie.getName());
        vo.setRating(movie.getRating());
        return vo;
    }

}
