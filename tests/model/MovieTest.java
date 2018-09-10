package model;

import model.movieFiledEnums.EGenre;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {


    @Test(expected = IllegalArgumentException.class)
    public void genre_undefined() {

        Movie movie = new Movie("234", "pholon ki baraat", "kata");

    }

    @Test
    public void genre_defined() {
        Movie movie = new Movie("234", "pholon ki baraat", " Action ");
        assertEquals(EGenre.ACTION, movie.getGenre());

        movie = new Movie("234", "pholon ki baraat", "   Sci-Fi ");
        assertEquals(EGenre.SCI_FI, movie.getGenre());
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