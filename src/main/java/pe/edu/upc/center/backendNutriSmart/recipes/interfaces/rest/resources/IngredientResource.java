package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources;

public record IngredientResource(int id, String name, double calories, double proteins, double fats,
                                 double carbohydrates, Long macronutrientValuesId) {
}
