package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources;
//para post
public record CreateTrackingGoalResource(
        Long userId,
        CreateMacronutrientValuesResource targetMacros
) {
}
