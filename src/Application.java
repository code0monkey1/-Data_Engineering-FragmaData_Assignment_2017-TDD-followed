import controller.Statistics;
import model.primaryObjects.CustomerInfo;
import model.primaryObjects.MovieInfo;
import model.primaryObjects.RatingInfo;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;
import view.UserInterface;

public class Application {

    public static void main(String[] args) {

        MovieMapper movieMapper = new MovieMapper(new FileParser("movies.dat", "::"), 3);
        MovieInfo movieInfo = new MovieInfo(movieMapper.getIdMovieMap());

        CustomerMapper customerMapper = new CustomerMapper(new FileParser("users.dat", "::"), 5);
        CustomerInfo customerInfo = new CustomerInfo(customerMapper.getIdCustomerMap());

        RatingsMapper ratingsMapper = new RatingsMapper(new FileParser("ratings.dat", "::"), 4);
        RatingInfo ratingInfo = new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());


        Statistics statistics = new Statistics(customerInfo, movieInfo, ratingInfo);

        UserInterface userInterface = new UserInterface(statistics);

        userInterface.start();
    }
}
