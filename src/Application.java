import controller.Statistics;
import model.primary.customer.CustomerInfo;
import model.primary.movie.MovieInfo;
import model.primary.rating.RatingInfo;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;
import view.UserInterface;

public class Application {

    public static void main(String[] args) {
        execute();
    }

    private static void execute() {
        Statistics statistics = initiateStatistics();
        initiateUI(statistics);
    }

    private static Statistics initiateStatistics() {
        MovieInfo movieInfo = processMovies();
        CustomerInfo customerInfo = processCustomers();
        RatingInfo ratingInfo = processRatings();

        return new Statistics(customerInfo, movieInfo, ratingInfo);
    }

    private static void initiateUI(Statistics statistics) {
        UserInterface userInterface = new UserInterface(statistics);

        userInterface.start();
    }

    private static RatingInfo processRatings() {
        FileParser ratingsFile = new FileParser("ratings.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(ratingsFile, 4);
        return new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
    }

    private static CustomerInfo processCustomers() {
        FileParser customerFile = new FileParser("users.dat", "::");
        CustomerMapper customerMapper = new CustomerMapper(customerFile, 5);
        return new CustomerInfo(customerMapper.getIdCustomerMap());
    }

    private static MovieInfo processMovies() {
        FileParser movieFile = new FileParser("movies.dat", "::");
        MovieMapper movieMapper = new MovieMapper(movieFile, 3);
        return new MovieInfo(movieMapper.getIdMovieMap());
    }

}
