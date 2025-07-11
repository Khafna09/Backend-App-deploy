package pe.edu.upc.center.backendNutriSmart.tracking.domain.services;


import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.CreateTrackingGoalCommand;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.UpdateTrackingGoalCommand;

public interface TrackingGoalCommandService {
    Long handle(CreateTrackingGoalCommand command);
    void handle(UpdateTrackingGoalCommand command);
}
