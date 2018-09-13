package util.FileParsing;

import conditions.Condition;
import conditions.FieldCount;
import model.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MovieFileParser extends FileParser {

    private Map<Integer, Movie> idMovieMap;

    public Map<Integer, Movie> getIdMovieMap() {
        return idMovieMap;
    }


    public MovieFileParser(String fileName, String parseToken, int fields) {
        super(fileName, parseToken);
        idMovieMap = returnIdMovieMap(fields);
    }

    private Map<Integer, Movie> returnIdMovieMap(int fields) {

        Map<Integer, Movie> tempIdMoviesMap = new HashMap<>();
        // Entry format : MovieID , Title ,Genres

        List<List<String>> movieEntriesList = this.getRawList();
//        System.out.println(rawEntriesList);

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


    public static void main(String[] args) {

    }

}
