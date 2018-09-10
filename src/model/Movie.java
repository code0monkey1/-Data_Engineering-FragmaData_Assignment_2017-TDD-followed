package model;

import model.movieFiledEnums.EGenre;
import util.Validate;

import java.util.Arrays;
import java.util.List;

public class Movie {
    private int id; //MovieIDs range between 1 and 3952
    private String title;
    private EGenre genre;


    public Movie(String id,
                 String title,
                 String genre) {
        assignID(id);
        this.title = title.trim().toUpperCase(); //done to avoid inconsistency
        assignGenre(genre);

    }

    private void assignID(String id) {

        id = id.trim();

        int ID = Integer.parseInt(id);

        if (!Validate.movieIdInRange(ID)) {
            throw new IllegalArgumentException("ID out of range");
        } else this.id = ID;
    }


    private void assignGenre(String genre) {
        int genreIndex = getValidGenreIndex(genre);
        this.genre = processAndGetGenre(genreIndex);

    }

    private EGenre processAndGetGenre(int index) {
        EGenre[] genreArray = EGenre.values();
        return genreArray[index];
    }


    private int getValidGenreIndex(String genre) {
        genre = genre.trim();

        List<String> genreList = Arrays.asList("Action",
                "Adventure",
                "Animation",
                "Children's",
                "Comedy",
                "Crime",
                "Documentary",
                "Drama",
                "Fantasy",
                "Film-Noir",
                "Horror",
                "Musical",
                "Mystery",
                "Romance",
                "Sci-Fi",
                "Thriller",
                "War",
                "Western");

        int genreIndex = genreList.indexOf(genre);

        if (!genreIndexFound(genreIndex)) {
            throw new IllegalArgumentException("Movie index not found");
        }
        return genreIndex;
    }

    private boolean genreIndexFound(int index) {
        return index >= 0;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public EGenre getGenre() {
        return genre;
    }
}
