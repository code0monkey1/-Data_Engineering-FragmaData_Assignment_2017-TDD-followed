package util.FileParsing;

import model.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class MovieFileParser extends FileParser {

    private Map<Integer, Movie> idMovieMap;
    private int fields;

    public Map<Integer, Movie> getIdMovieMap() {
        return idMovieMap;
    }


    public MovieFileParser(String fileName, String parseToken, int fields) {
        super(fileName, parseToken);
        this.fields = fields;
        idMovieMap = returnIdMovieMap();

    }

    private Map<Integer, Movie> returnIdMovieMap() {

        Map<Integer, Movie> tempIdMoviesMap = new HashMap<>();
        // Entry format : MovieID , Title ,Genres

        List<List<String>> movieEntriesList = this.getRawEntriesList();
//        System.out.println(rawEntriesList);
        for (List<String> movieEntry : movieEntriesList) {
            if (!hasValidFields(movieEntry)) {
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

    private boolean hasValidFields(List<String> entry) {
        return entry.size() == fields;
    }


    public static void main(String[] args) {

    }

}
