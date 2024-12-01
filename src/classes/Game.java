package classes;

import java.util.ArrayList;
public class Game {
    private String title;
    private String publisher;

    private ArrayList<Integer> ratings;

    private boolean noRatings;


    public Game(String title, String publisher) {
        this.setTitle(title);
        this.publisher = publisher;
        this.ratings = new ArrayList<>(5);
        for (int i = 0; i<5; i++) { ratings.add(0); }
        this.noRatings = true;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Game game = (Game) object;
        if (!this.getTitle().equals(game.getTitle())) return false;
        if (!this.getPublisher().equals(game.getPublisher())) return false;
        for (Integer thisCount : this.ratings) {
            for (Integer thatCount : game.getRatings()) {
                if (!thisCount.equals(thatCount)) return false;
            }
        }
        return true;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getPublisher() {
        return this.publisher;
    }
    public void setPublisher(String pub) {
        this.publisher = pub;
    }

    public void addRating(Integer rating) {
        if (rating >= 1 && rating <= 5) {
            this.noRatings = false;
            int index = rating - 1;
            ratings.set(index, ratings.get(index)+1);
        }
    }

    public ArrayList<Integer> getRatings(){
        return this.ratings;
    }

    public String printRatings() {
        if (this.noRatings) {
            String noRatings = "This game has no ratings.\n";
            System.out.println(noRatings);
            return noRatings;
        } else {
            double bayesianAverage = bayesianAverageRating(2, 3);
            String average = "Average rating: " + bayesianAverage + "\n";
            System.out.println(average);
            String starString = "";
            for (int i = 5; i>0; i--) {
                starString += i + " stars: " + ratings.get(i-1) + "\n";
            }
            System.out.println(starString);
            return average + starString;
        }
    }

    public double bayesianAverageRating(int extra, int value) {
        if (this.noRatings) return 0.0;
        if (extra <= 0) {
            System.out.println("The amount of extra values must be greater than 0\n");
            return -1.0;
        } else if (value > 5 || value < 1) {
            System.out.println("The rating value must be in [1,5]\n");
            return -1.0;
        }
        int bayesianVal = extra * value;
        ArrayList<Integer> counts = this.ratings;
        for (Integer count : counts) {
            extra += count;
            }
        double sum = bayesianAverageHelper(counts) + bayesianVal;
        if (sum == 0.0 || extra == 0.0) {
            return 0.0;
        }
        double result = sum / extra;
        return Math.round(result * 10.0) / 10.0;
    }
    public double bayesianAverageHelper(ArrayList<Integer> ratings) {
        double sum = 0.0;
        Integer oneStars = ratings.get(0);
        Integer twoStars = ratings.get(1) * 2;
        Integer threeStars = ratings.get(2) * 3;
        Integer fourStars = ratings.get(3) * 4;
        Integer fiveStars = ratings.get(4) * 5;
        sum = oneStars+twoStars+threeStars+fourStars+fiveStars;
        return sum;
    }

}
