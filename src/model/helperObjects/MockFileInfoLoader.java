package model.helperObjects;

import model.primary.rating.RatingInfo;
import util.FileParsing.FileParser;
import util.InfoLoader.InfoLoader;

import util.mapping.RatingsMapper;

public class MockFileInfoLoader implements InfoLoader {

    @Override
    public RatingInfo loadRatingInfo() {
        FileParser ratingsFile = new FileParser("mockRatingsForMoviesNMostViewed.dat", "::");
        RatingsMapper ratingsMapper = new RatingsMapper(ratingsFile, 4);
        return new RatingInfo(ratingsMapper.getCustomerIDMovieIDRatingAndTimeMap());
    }

}


