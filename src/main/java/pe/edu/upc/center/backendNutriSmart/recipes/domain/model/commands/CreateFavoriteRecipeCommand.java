package pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands;

public record CreateFavoriteRecipeCommand(Long userId, int recipeId) {
}
