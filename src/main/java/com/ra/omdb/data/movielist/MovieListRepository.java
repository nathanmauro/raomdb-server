package com.ra.omdb.data.movielist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MovieListRepository extends JpaRepository<MovieList, String> {
}
