package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources;

import java.util.List;

public record CreateUserProfileResource(
        String gender,
        double height,
        double weight,
        int userScore,
        Long activityLevelId,
        Long objectiveId,
        List<Long> allergyIds
) {}
