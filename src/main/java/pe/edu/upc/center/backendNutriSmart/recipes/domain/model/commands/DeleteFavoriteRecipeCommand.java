package pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands;

public record DeleteFavoriteRecipeCommand(Long userId, int recipeId) {
}
