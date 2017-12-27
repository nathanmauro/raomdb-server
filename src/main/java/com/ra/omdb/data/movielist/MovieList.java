package com.ra.omdb.data.movielist;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "movie_list")
public class MovieList {
    @EmbeddedId
    @AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "imdbId", column = @Column(name = "imdb_id")) })
    private MovieListPk id;

    @Column(name = "name", insertable = false, updatable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieList() {
        /* default constructor for hibernate */
    }

    public MovieList(MovieListPk movieListPk) {
        this.id = movieListPk;
    }

    public MovieListPk getId() {
        return id;
    }

    public void setId(MovieListPk id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getImdbId() {
//        return imdbId;
//    }
//
//    public void setImdbId(String imdbId) {
//        this.imdbId = imdbId;
//    }
}
