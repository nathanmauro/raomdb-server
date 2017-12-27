package com.ra.omdb.rest.movielist;

import com.ra.omdb.rest.movie.MovieVo;

import java.util.List;

public class MovieListVo {

    private String name;

    private List<MovieVo> movies;

    public MovieListVo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieVo> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieVo> movies) {
        this.movies = movies;
    }
}
