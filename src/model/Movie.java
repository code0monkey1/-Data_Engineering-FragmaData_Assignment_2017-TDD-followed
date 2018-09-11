package model;

import model.movieFiledEnums.EGenre;
import util.Validate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private int id; //MovieIDs range between 1 and 3952
    private String title;
    private List<EGenre> genreList;


    public Movie(String id,
                 String title,
                 List<String> Genre) {


        this.id = returnID(id);
        this.title = title.trim().toUpperCase(); //done to avoid inconsistency
        this.genreList = returnGenreList(Genre);

    }

    private int returnID(String id) {

        id = id.trim();

        int ID = Integer.parseInt(id);

        if (!Validate.movieIdInRange(ID)) {
            throw new IllegalArgumentException("ID out of range");
        } else return ID;
    }


    private List<EGenre> returnGenreList(List<String> Genre) {
        List<EGenre> genreList = new ArrayList<>();

        for (String genre : Genre) {
            int genreIndex = returnGenreIndex(genre);
            genreList.add(returnGenre(genreIndex));
        }
        return genreList;
    }

    private EGenre returnGenre(int index) {
        EGenre[] genreArray = EGenre.values();
        return genreArray[index];
    }


    private int returnGenreIndex(String genre) {
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

    public List<EGenre> getGenre() {
        return genreList;
    }
}
