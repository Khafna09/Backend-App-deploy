package pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands;

public record AddIngredientToRecipeCommand(int recipeId, int ingredientId) {
}
