package com.ra.omdb.data.movielist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public interface MovieListRepository extends JpaRepository<MovieList, MovieListPk> {
    List<MovieList> findAllByName(String name);

    Set<MovieList> findDistinctByName(String name);

    MovieList findById(MovieListPk id);
}
