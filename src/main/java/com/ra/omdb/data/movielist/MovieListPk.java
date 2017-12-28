package com.ra.omdb.data.movielist;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MovieListPk implements Serializable {
//    @Id
    @Column(name = "name")
    private String name;

//    @Id
    @Column(name = "imdb_id")
    private String imdbId;

    public MovieListPk() {
        /* default constructor for hibernate */
    }

    public MovieListPk(String name, String imdbId) {
        this.name = name;
        this.imdbId = imdbId;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
