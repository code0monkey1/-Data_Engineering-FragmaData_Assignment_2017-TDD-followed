package util.FileParsing;

import model.primary.movie.Genre;
import model.primary.movie.MovieInfo;
import org.junit.Before;
import org.junit.Test;
import util.mapping.MovieMapper;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MovieFileParserTest {

    MovieInfo movieInfo;

    @Before
    public void setUp() {
        FileParser fileParser = new FileParser("mockMovies.dat", "::");
        MovieMapper movieMapper = new MovieMapper(fileParser, 3);
        movieInfo = new MovieInfo(movieMapper.getIdMovieMap());
    }

    @Test
    public void map_genreListValid() {

        assertEquals(Arrays.asList(Genre.ANIMATION, Genre.CHILDRENS, Genre.COMEDY), movieInfo.getGenreList(1));
        assertEquals(Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.THRILLER), movieInfo.getGenreList(10));
        assertEquals(Arrays.asList(Genre.ADVENTURE, Genre.CHILDRENS), movieInfo.getGenreList(8));

    }

    @Test
    public void map_nameOfMovieValid() {

        assertEquals("Toy Story (1995)", movieInfo.getTitle(1));
        assertEquals("GoldenEye (1995)",movieInfo.getTitle(10));
        assertEquals("Tom and Huck (1995)", movieInfo.getTitle(8));

    }


}