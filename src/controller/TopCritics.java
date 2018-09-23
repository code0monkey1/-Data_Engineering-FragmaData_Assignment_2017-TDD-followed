package controller;

import conditions.CountAtLeast;
import model.helperObjects.Critic;
import model.primary.rating.RatingInfo;
import util.math.Calculate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopCritics {
    private final int top;
    private final int minViewCount;
    private final PriorityQueue<Critic> critics;
    private final Map<Integer, Long> customerIdRating;
    private final Map<Integer, Integer> customerIdView;
    private final List<Integer> customerIds;


    public TopCritics(int top, int minViewCount, RatingInfo ratingInfo) {
        this.top = top;
        this.minViewCount = minViewCount;
        critics = new PriorityQueue<>();
        customerIdRating = ratingInfo.getCustomerIdRatingMap();
        customerIdView = ratingInfo.getCustomerIdMoviesSeenCountMap();
        customerIds = ratingInfo.getAllCustomerIds();
    }

    public List<Critic> getCriticsList() {

        PriorityQueue<Critic> customerQueue = customerQueue();

        List<Critic> customers = customers(top, customerQueue);

        return customers;
    }

    private PriorityQueue<Critic> customerQueue() {

        return processCustomersQueue();
    }


    private PriorityQueue<Critic> processCustomersQueue() {

        for (int customerId : customerIds)
            processCustomer(customerId);

        return critics;
    }

    private void processCustomer(int customerId) {

        long customerRatingCount = customerIdRating.get(customerId);
        int customerViewershipCount = customerIdView.get(customerId);

        CountAtLeast minimumCountCondition = new CountAtLeast(minViewCount, customerViewershipCount);

        if (minimumCountCondition.isValid())
            addCriticToQueue(customerId, customerRatingCount, customerViewershipCount);

    }

    private void addCriticToQueue(int customerId,
                                  long rating,
                                  int views) {

        double averageRating = Calculate.average(rating, views);

        Critic customer = new Critic(customerId, averageRating, views);
        critics.add(customer);
    }

    private List<Critic> customers(int top,
                                   PriorityQueue<Critic> customerRatingsQueue) {

        int count = Math.min(top, customerRatingsQueue.size());

        List<Critic> customers = new ArrayList<>();

        for (int i = 0; i < count; i++)
            customers.add(customerRatingsQueue.poll());

        return customers;
    }


}