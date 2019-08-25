package graduation.raitrest.model.to;

public class Rating {
    private Integer restaurantId;
    private String restaurantName;
    private Long countVotes;

    public Rating(Integer restaurantId, String restaurantName, Long countVotes) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.countVotes = countVotes;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Long getCountVotes() {
        return countVotes;
    }

    public void setCountVotes(Long countVotes) {
        this.countVotes = countVotes;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", countVotes=" + countVotes +
                '}';
    }
}
