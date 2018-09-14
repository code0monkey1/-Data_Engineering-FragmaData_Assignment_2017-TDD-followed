package model.primaryObjects;

import model.movieFiledEnums.EGenre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovieInfo {
    private final Map<Integer, Movie> idMovieMap;

    public MovieInfo(Map<Integer, Movie> idMovieMap) {
        this.idMovieMap = idMovieMap;
    }

    public String getTitle(int movieID) {

        Movie movie = this.idMovieMap.getOrDefault(movieID, null);

        if (movie == null) {
            return "NO DOES NOT EXIST";
        }
        return movie.getTitle();


    }

    public List<EGenre> getGenreList(int movieID) {
        Movie movie = this.idMovieMap.getOrDefault(movieID, null);

        List<EGenre> genreList = new ArrayList<>();

        if (movie == null) {
            return genreList;
        } else {
            List<EGenre> genre = movie.getGenreList();
            genreList.addAll(genre);
        }

        return genreList;
    }
}
