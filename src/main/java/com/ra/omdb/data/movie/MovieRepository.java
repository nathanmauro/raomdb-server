package com.ra.omdb.data.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findAllByListName(String listName);
}
