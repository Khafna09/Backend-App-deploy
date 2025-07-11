package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources;

public record FavoriteRecipeResource(Long id, Long userId, int recipeId, String recipeName) {
}
