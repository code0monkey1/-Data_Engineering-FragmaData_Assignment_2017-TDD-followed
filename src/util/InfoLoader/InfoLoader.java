package util.InfoLoader;

import model.primary.customer.CustomerInfo;
import model.primary.movie.MovieInfo;
import model.primary.rating.RatingInfo;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;
import util.mapping.MovieMapper;
import util.mapping.RatingsMapper;

public interface InfoLoader {

    default RatingInfo loadRatingInfo() {
        FileParser ratingsFile = new FileParser("ratings.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(ratingsFile, 4);
        return new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
    }

    default CustomerInfo loadCustomerInfo() {
        FileParser customerFile = new FileParser("users.dat", "::");
        CustomerMapper customerMapper = new CustomerMapper(customerFile, 5);
        return new CustomerInfo(customerMapper.getIdCustomerMap());
    }

    default MovieInfo loadMovieInfo() {
        FileParser movieFile = new FileParser("movies.dat", "::");
        MovieMapper movieMapper = new MovieMapper(movieFile, 3);
        return new MovieInfo(movieMapper.getIdMovieMap());
    }

}
