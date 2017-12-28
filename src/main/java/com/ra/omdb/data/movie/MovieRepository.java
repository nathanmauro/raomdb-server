package com.ra.omdb.data.movie;

import com.ra.omdb.data.movielist.MovieListPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findAllByImdbIdIn(Collection<String> imdbId);
}
