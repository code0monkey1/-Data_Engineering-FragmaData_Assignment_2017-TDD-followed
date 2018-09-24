package model.primary.movie;

import model.helperObjects.RatedMovie;

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

        if (movie == null)
            throw new IllegalArgumentException("movie does not exist");

        return movie.getTitle();

    }

    public List<Genre> getGenreList(int movieID) {
        Movie movie = this.idMovieMap.getOrDefault(movieID, null);

        List<Genre> genreList = new ArrayList<>();

        if (movie == null)
            return genreList;
        else {
            List<Genre> genre = movie.getGenreList();
            genreList.addAll(genre);
        }

        return genreList;
    }


    public void printRatedMovies(List<RatedMovie> topRatedMovies) {

        for (RatedMovie movie : topRatedMovies)
            System.out.printf("Movie : %s  ::  Rating : %.2f:: " +
                            "Views : %d %n",
                    getTitle(movie.getId()),
                    movie.getRating(),
                    movie.getViews());

    }
}
