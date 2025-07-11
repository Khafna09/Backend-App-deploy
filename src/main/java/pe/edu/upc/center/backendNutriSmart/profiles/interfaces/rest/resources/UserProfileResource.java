package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources;
import lombok.Getter;

import java.util.List;
public record UserProfileResource(
        int id,
        String gender,
        double height,
        double weight,
        int userScore,
        Long activityLevelId,
        String activityLevelName,
        int objectiveId,
        String objectiveName,
        List<String> allergyNames
) {


    public int getId() {
        return id;
    }
}