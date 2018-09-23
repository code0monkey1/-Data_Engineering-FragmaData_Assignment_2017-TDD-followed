package model;

import model.primary.movie.Genre;
import model.primary.movie.Movie;
import org.junit.Test;

import java.util.Arrays;


import static org.junit.Assert.*;

public class MovieTest {


    @Test(expected = IllegalArgumentException.class)
    public void genre_undefined() {

        Movie movie = new Movie("234", "pholon ki baraat", "kata");

    }

    @Test
    public void genre_defined() {
        Movie movie = new Movie("234", "pholon ki baraat", " Action|Sci-Fi ");
        assertEquals(Arrays.asList(Genre.ACTION,Genre.SCI_FI), movie.getGenreList());

        movie = new Movie("234", "pholon ki baraat","  Sci-Fi|Children's ");
        assertEquals(Arrays.asList(Genre.SCI_FI,Genre.CHILDRENS), movie.getGenreList());
    }

    @Test
    public void id_inRange() {
        Movie movie = new Movie("10", "pholon ki baraat", " Action ");
    }

    @Test(expected = IllegalArgumentException.class)
    public void id_outOfRange() {
        Movie movie = new Movie("8928298", "pholon ki barat", "Action");

    }

    @Test(expected = NumberFormatException.class)
    public void id_notANumber() {
        Movie movie = new Movie("23a", "pholon ki barat", "Action");
    }


}