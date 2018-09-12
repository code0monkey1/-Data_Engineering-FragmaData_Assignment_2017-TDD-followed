import controller.Statistics;
import util.FileParsing.CustomerFileParser;
import util.FileParsing.FileParser;
import util.FileParsing.MovieFileParser;
import util.FileParsing.RatingsFileParser;
import view.UserInterface;

public class Application {

    public static void main(String[] args) {

        FileParser movieFileParser = new MovieFileParser("movies.dat", "::", 3);
        FileParser customerFileParser = new CustomerFileParser("users.dat", "::", 5);
        FileParser ratingsFileParser = new RatingsFileParser("ratings.dat", "::", 4);

        Statistics statistics = new Statistics(movieFileParser, customerFileParser, ratingsFileParser);

        UserInterface userInterface = new UserInterface(statistics);

        userInterface.start();
    }
}
