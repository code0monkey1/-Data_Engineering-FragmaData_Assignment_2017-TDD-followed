package controller;

import conditions.CountAtLeast;
import model.helperObjects.CustomerRating;
import model.primary.rating.RatingInfo;
import util.math.Calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopCritics {
    private final int top;
    private final int minViewCount;
    private final RatingInfo ratingInfo;

    public TopCritics(int top, int minViewCount, RatingInfo ratingInfo) {
        this.top = top;
        this.minViewCount = minViewCount;
          this.ratingInfo=ratingInfo;
    }

    public List<CustomerRating> getCriticsList() {

        Map<Integer, Long> customerIdRatingMap = ratingInfo.getCustomerIdRatingMap();

        Map<Integer, Integer> customerIdViewershipMap = ratingInfo.getCustomerIdMoviesSeenCountMap();

        PriorityQueue<CustomerRating> customerRatingsQueue = customerRatingObjectsQueueAfterFulfillingViewCountCriteria(minViewCount, customerIdRatingMap, customerIdViewershipMap);

        List<CustomerRating> customers = customerRatingObjectList(top, customerRatingsQueue);


        return customers;
    }


    private PriorityQueue<CustomerRating> customerRatingObjectsQueueAfterFulfillingViewCountCriteria(int minViewCount,
                                                                                                     Map<Integer, Long> customerIdRatingMap,
                                                                                                     Map<Integer, Integer> customerIdViewershipMap) {

        PriorityQueue<CustomerRating> customerRatingsQueue = new PriorityQueue<>();

        List<Integer> customerIds = ratingInfo.getAllCustomerIds();

        for (int customerId : customerIds) {

            long customerRatingCount = customerIdRatingMap.get(customerId);
            int customerViewershipCount = customerIdViewershipMap.get(customerId);

            CountAtLeast count = new CountAtLeast(minViewCount, customerViewershipCount);
            if (count.isValid()) {
                double average = Calculate.returnAverage(customerRatingCount, customerViewershipCount);
                customerRatingsQueue.add(new CustomerRating(customerId, average, customerViewershipCount));
            }

        }

        return customerRatingsQueue;
    }

    private List<CustomerRating> customerRatingObjectList(int top, PriorityQueue<CustomerRating> customerRatingsQueue) {
        int count = Math.min(top, customerRatingsQueue.size());

        List<CustomerRating> customers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            customers.add(customerRatingsQueue.poll());
        }
        return customers;
    }


}