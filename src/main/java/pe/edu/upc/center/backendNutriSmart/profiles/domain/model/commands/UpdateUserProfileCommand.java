package pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands;

public record UpdateUserProfileCommand(
        Long userProfileId,
        String gender,
        double height,
        double weight,
        int userScore,
        Long activityLevelId,
        Long objectiveId


) {
}
