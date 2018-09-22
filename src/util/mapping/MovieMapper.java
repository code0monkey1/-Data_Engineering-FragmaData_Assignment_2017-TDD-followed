package util.mapping;

import conditions.Condition;
import conditions.FieldCount;
import model.primary.movie.Movie;
import util.FileParsing.FileParser;
import wrappers.MovieMap;

import java.util.List;
import java.util.Map;

public final class MovieMapper {

    private MovieMap idMovieMap;


    public MovieMapper(FileParser fileParser, int fields) {
        this.idMovieMap = returnMovieMap(fileParser, fields);
    }

    private MovieMap returnMovieMap(FileParser fileParser, int fields) {

        MovieMap tempIdMoviesMap = new MovieMap();
        // Entry format : MovieID , Title ,Genres

        List<List<String>> movieEntriesList = fileParser.getRawList();


        for (List<String> movieEntry : movieEntriesList) {
            Condition movieEntryFieldCount = new FieldCount(fields, movieEntry);

            if (!movieEntryFieldCount.isValid()) {
                throw new IllegalArgumentException("fields size illegal");
            }

            Movie movie = returnMovie(movieEntry);
            tempIdMoviesMap.put(movie.getId(), movie);
        }

        return tempIdMoviesMap;
    }

    private Movie returnMovie(List<String> movieEntry) {

        String id = movieEntry.get(0).trim();
        String title = movieEntry.get(1).trim();
        String genre = movieEntry.get(2).trim();

        Movie movie = new Movie(id, title, genre);

        return movie;
    }


    public Map<Integer, Movie> getIdMovieMap() {
        return idMovieMap;
    }
}
