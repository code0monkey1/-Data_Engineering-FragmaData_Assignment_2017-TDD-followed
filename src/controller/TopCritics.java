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
    private final PriorityQueue<Critic> criticsQueue;
    private final Map<Integer, Long> customerIdRating;
    private final Map<Integer, Integer> customerIdView;
    private final List<Integer> customerIds;


    public TopCritics(int top, int minViewCount, RatingInfo ratingInfo) {
        this.top = top;
        this.minViewCount = minViewCount;
        criticsQueue = new PriorityQueue<>();
        customerIdRating = ratingInfo.getCustomerIdRatingMap();
        customerIdView = ratingInfo.getCustomerIdMoviesSeenCountMap();
        customerIds = ratingInfo.getAllCustomerIds();
    }

    public List<Critic> getCritics() {

        PriorityQueue<Critic> criticQueue = criticQueue();

        List<Critic> critics = topCritics(top, criticQueue);

        return critics;
    }

    private PriorityQueue<Critic> criticQueue() {

        for (int customerId : customerIds)
            addCriticToQueue(customerId);

        return criticsQueue;
    }

    private void addCriticToQueue(int customerId) {

        int views = customerIdView.get(customerId);

        CountAtLeast minimumCountCondition = new CountAtLeast(minViewCount, views);

        if (minimumCountCondition.isValid()) {
            Critic critic = critic(customerId);
            criticsQueue.add(critic);
        }

    }

    private Critic critic(int customerId) {

        int views = customerIdView.get(customerId);
        long rating = customerIdRating.get(customerId);

        double averageRating = Calculate.average(rating, views);
        return new Critic(customerId, averageRating, views);
    }


    private List<Critic> topCritics(int top, PriorityQueue<Critic> criticQueue) {

        int count = Math.min(top, criticQueue.size());

        List<Critic> critics = new ArrayList<>();

        for (int i = 0; i < count; i++)
            critics.add(criticQueue.poll());

        return critics;
    }


}