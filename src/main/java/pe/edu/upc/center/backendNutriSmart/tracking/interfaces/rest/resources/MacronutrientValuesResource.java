package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources;
//para get y put
public record MacronutrientValuesResource(
        long id,
        double calories,
        double carbs,
        double proteins,
        double fats) {
}
