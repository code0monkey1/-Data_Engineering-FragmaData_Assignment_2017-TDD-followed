package util.FileParsing;

import model.Movie;
import model.movieFiledEnums.EGenre;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MovieFileParserTest {
    private MovieFileParser movieFileParser;

    @Before
    public void setUp() {
        this.movieFileParser = new MovieFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockMovies.dat", "::", 3);
    }

    @Test
    public void map_genreListValid() {

        Map<Integer, Movie> idMovieMap = movieFileParser.getIdMovieMap();
        assertEquals(Arrays.asList(EGenre.ANIMATION, EGenre.CHILDRENS, EGenre.COMEDY), idMovieMap.get(1).getGenre());
        assertEquals(Arrays.asList(EGenre.ACTION, EGenre.ADVENTURE, EGenre.THRILLER), idMovieMap.get(10).getGenre());
        assertEquals(Arrays.asList(EGenre.ADVENTURE, EGenre.CHILDRENS), idMovieMap.get(8).getGenre());

    }

    @Test
    public void map_nameOfMovieValid() {
        Map<Integer, Movie> idMovieMap = movieFileParser.getIdMovieMap();
        assertEquals("Toy Story (1995)", idMovieMap.get(1).getTitle());
        assertEquals("GoldenEye (1995)", idMovieMap.get(10).getTitle());
        assertEquals("Tom and Huck (1995)", idMovieMap.get(8).getTitle());

    }


}