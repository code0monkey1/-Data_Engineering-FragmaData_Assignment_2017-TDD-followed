package model.primary.movie;

import conditions.Condition;
import conditions.MovieID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public final class Movie {
    private final int id;
    private final String title;
    private final List<Genre> genreList;


    public Movie(String id,
                 String title,
                 String Genre) {

        this.id = ID(id);
        this.title = title;
        this.genreList = generateList(Genre);

    }


    private int ID(String id) {

        int ID = Integer.parseInt(id);
        Condition movieID = new MovieID(ID);

        if (!movieID.isValid()) {
            throw new IllegalArgumentException("ID out of range (MovieIDs range between 1 and 3952 ) :" + ID);
        } else return ID;
    }


    private List<Genre> generateList(String GenreString) {
        List<String> GenreStringList = genreStringList(GenreString);
        List<Genre> genreList = new ArrayList<>();

        for (String genre : GenreStringList) {
            int genreIndex = genreIndex(genre);
            genreList.add(genre(genreIndex));
        }
        return genreList;
    }

    private List<String> genreStringList(String genre) {
        List<String> genreList = new ArrayList<>();

        String[] genreArray = genre.split("\\|");

        for (String genreElement : genreArray) {
            genreList.add(genreElement.trim());
        }

        return genreList;
    }

    private Genre genre(int index) {
        Genre[] genreArray = Genre.values();
        return genreArray[index];
    }


    private int genreIndex(String genre) {
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
            throw new IllegalArgumentException("Genre index not found" +
                    " genre : " + genre + " movie title : " +
                    this.title);
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

    public List<Genre> getGenreList() {
        return genreList;
    }
}
