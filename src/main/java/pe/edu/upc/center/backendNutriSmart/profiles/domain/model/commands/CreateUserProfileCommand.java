package pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands;


import java.util.List;

public record CreateUserProfileCommand(
        String gender,
        double height,
        double weight,
        int userScore,
        Long activityLevelId,
        Long objectiveId,
        List<Long> allergyIds

) {
}
