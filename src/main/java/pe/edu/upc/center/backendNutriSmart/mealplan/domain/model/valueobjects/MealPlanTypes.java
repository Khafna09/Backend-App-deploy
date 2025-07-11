package pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects;

public enum MealPlanTypes {
    Breakfast (1),
    Lunch (2),
    Dinner (3),
    Snack (4);

    private final int value;

    MealPlanTypes(int value) {
        this.value = value;
    }
}
