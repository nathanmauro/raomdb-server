package com.ra.omdb.rest.movielist;

import com.ra.omdb.data.movie.Movie;
import com.ra.omdb.data.movie.MovieRepository;
import com.ra.omdb.data.movielist.MovieList;
import com.ra.omdb.data.movielist.MovieListPk;
import com.ra.omdb.data.movielist.MovieListRepository;
import com.ra.omdb.rest.movie.MovieVo;
import com.ra.omdb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/rest/movielist", produces = "application/json")
public class MovieListController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieListRepository movieListRepository;

    @Autowired
    MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    public Set<MovieListVo> getAll() {
        Set<MovieList> movieLists = new HashSet<>(this.movieListRepository.findAll());

        Set<MovieListVo> movieListVos = new HashSet<>(movieLists.size());
        movieLists.forEach(movieList -> {
            MovieListVo movieListVo = new MovieListVo(movieList.getId().getName());

            List<MovieVo> movieVos = getMovieVos(movieList.getId().getName());
            movieListVo.setMovies(movieVos);
            movieListVos.add(movieListVo);
        });

        return movieListVos;
    }

    @RequestMapping(value = "/{listName}", method = RequestMethod.GET)
    public MovieListVo getOne(@PathVariable String listName) {
        MovieListVo movieListVo = new MovieListVo(listName);

        List<MovieVo> movieVos = this.getMovieVos(listName);

        movieListVo.setMovies(movieVos);

        return movieListVo;
    }


    @RequestMapping(value = "/create/{listName}", method = RequestMethod.POST)
    public List<MovieListVo> create(@PathVariable String listName) {
        MovieList newMovieList = new MovieList();
        newMovieList.setId(new MovieListPk(listName, "blank"));
        this.movieListRepository.save(newMovieList);

        return this.getMovieListVos();
    }

    @RequestMapping(value = "/{listName}", method = RequestMethod.POST, consumes = "application/json")
    public List<MovieVo> addToMovieList(@PathVariable("listName") String listName, @RequestBody MovieVo movieVo) {
        this.movieService.voToEntity(movieVo);

        MovieList movieList = this.movieListRepository.findById(new MovieListPk(listName, movieVo.getImdbId()));

        if (movieList == null) {
            movieList = new MovieList(new MovieListPk(listName, movieVo.getImdbId()));
        }

        this.movieListRepository.save(movieList);

        return this.getMovieVos(listName);
    }

    // region private methods

    private List<MovieListVo> getMovieListVos() {
        List<MovieList> listOfMovies = this.movieListRepository.findAll();
        List<MovieListVo> movieListVos = new ArrayList<>(listOfMovies.size());
        listOfMovies.forEach(m -> movieListVos.add(new MovieListVo(m.getId().getName())));

        return movieListVos;
    }

    private List<MovieVo> getMovieVos(String listName) {
        List<MovieList> movieListList = this.movieListRepository.findAllByName(listName);
        List<String> movieListListImdbIds = movieListList.stream().map(ml -> ml.getId().getImdbId()).collect(Collectors.toList());
        List<Movie> movies = this.movieRepository.findAllByImdbIdIn(movieListListImdbIds);

        List<MovieVo> movieVos = new ArrayList<>(movies.size());
        this.movieEntityToMovieVoList(movies, movieVos);
        return movieVos;
    }

    private void movieEntityToMovieVoList(List<Movie> movies, List<MovieVo> movieVos) {
        movies.forEach(movie -> movieVos.add(this.movieService.entityToVo(movie)));
    }

    // endregion private methods
}
