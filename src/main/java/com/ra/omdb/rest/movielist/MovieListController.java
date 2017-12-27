package com.ra.omdb.rest.movielist;

import com.ra.omdb.data.movie.Movie;
import com.ra.omdb.data.movie.MovieRepository;
import com.ra.omdb.data.movielist.MovieList;
import com.ra.omdb.data.movielist.MovieListRepository;
import com.ra.omdb.rest.movie.MovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/movielist", produces = "application/json")
public class MovieListController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieListRepository movieListRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<MovieListVo> getAll() {
        List<MovieList> movieLists = this.movieListRepository.findAll();

        List<MovieListVo> movieListVos = new ArrayList<>(movieLists.size());
        movieLists.forEach(movieList -> {
            MovieListVo movieListVo = new MovieListVo(movieList.getName());
            List<Movie> movies = this.movieRepository.findAllByListName(movieList.getName());
            List<MovieVo> movieVos = new ArrayList<>(movies.size());
            this.movieEntityToMovieVoList(movies, movieVos);
            movieListVo.setMovies(movieVos);
            movieListVos.add(movieListVo);
        });

        return movieListVos;
    }

    @RequestMapping(value = "/{listName}", method = RequestMethod.GET)
    public MovieListVo getOne(@PathVariable String listName) {
        MovieList movieList = this.movieListRepository.getOne(listName);
        MovieListVo movieListVo = new MovieListVo(movieList.getName());

        List<Movie> movies = this.movieRepository.findAllByListName(listName);
        List<MovieVo> movieVos = new ArrayList<>(movies.size());
        this.movieEntityToMovieVoList(movies, movieVos);

        movieListVo.setMovies(movieVos);

        return movieListVo;
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json")
    public List<MovieListVo> create(@RequestBody MovieList movieList) {
        MovieList newMovieList = new MovieList();
        newMovieList.setName(movieList.getName());
        this.movieListRepository.save(newMovieList);

        List<MovieList> listOfMovies = this.movieListRepository.findAll();

        List<MovieListVo> movieListVos = new ArrayList<>(listOfMovies.size());
        listOfMovies.forEach(m -> movieListVos.add(new MovieListVo(m.getName())));

        return movieListVos;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public List<MovieVo> addToMovieList(@RequestBody MovieVo movieVo) {
        Movie movie = new Movie();
        movie.setImdbId(movieVo.getImdbId());
        movie.setListName(movieVo.getListName());
        movie.setName(movieVo.getName());
        movie.setRating(movieVo.getRating());

        this.movieRepository.save(movie);

        List<Movie> movies = this.movieRepository.findAllByListName(movieVo.getListName());

        List<MovieVo> movieVos = new ArrayList<>(movies.size());
        this.movieEntityToMovieVoList(movies, movieVos);

        return movieVos;
    }

    // region private methods

    private void movieEntityToMovieVoList(List<Movie> movies, List<MovieVo> movieVos) {
        movies.forEach(m -> {
            MovieVo vo = new MovieVo();
            vo.setImdbId(m.getImdbId());
            vo.setListName(m.getListName());
            vo.setName(m.getName());
            vo.setRating(m.getRating());
            movieVos.add(vo);
        });
    }

    // endregion private methods
}
