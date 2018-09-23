package model.helperObjects;

public class Critic implements Comparable<Critic> {


    private double rating;
    private int id;
    private int views;

    public Critic(int id, double rating, int views) {
        this.id = id;
        this.rating = rating;
        this.views = views;
    }

    @Override
    public int compareTo(Critic o) {
        return Double.compare(this.rating, o.rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Critic that = (Critic) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public int getViews() {
        return views;
    }

    @Override
    public String toString() {
        return "Critic{" +
                "rating=" + rating +
                ", id=" + id +
                ", views=" + views +
                '}';
    }
}
