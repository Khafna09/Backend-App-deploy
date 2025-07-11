package pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects;

public record UserProfileId(int userProfileId) {
    public UserProfileId {
        if (userProfileId < 0) {
            throw new IllegalArgumentException("Profile profile ID cannot be negative");
        }
    }

    public UserProfileId() {
        this(0);
    }
}
