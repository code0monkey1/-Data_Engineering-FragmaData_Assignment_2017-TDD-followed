import controller.Statistics;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;
import view.UserInterface;

public class Application {

    public static void main(String[] args) {

        MovieMapper movieMapper = new MovieMapper(new FileParser("movies.dat", "::"), 3);

        CustomerMapper customerMapper = new CustomerMapper(new FileParser("users.dat", "::"), 5);
        RatingsMapper ratingsMapper = new RatingsMapper(new FileParser("ratings.dat", "::"), 4);

        Statistics statistics = new Statistics(movieMapper, customerMapper, ratingsMapper);

        UserInterface userInterface = new UserInterface(statistics);

        userInterface.start();
    }
}
