package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources;

public record CreateRecipeResource(Long userId, String name, String description, int preparationTime,
                                   String difficulty, String category, String recipeType) {
}
