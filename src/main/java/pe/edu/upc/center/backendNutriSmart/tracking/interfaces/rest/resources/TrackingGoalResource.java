package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources;
//para get y put
public record TrackingGoalResource(
        Long id,
        Long userId,
        MacronutrientValuesResource targetMacros
){ }
