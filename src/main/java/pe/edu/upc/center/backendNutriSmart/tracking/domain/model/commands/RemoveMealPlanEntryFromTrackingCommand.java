package pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands;

public record RemoveMealPlanEntryFromTrackingCommand(long TrackingId, long MealPlanEntryId) {
}