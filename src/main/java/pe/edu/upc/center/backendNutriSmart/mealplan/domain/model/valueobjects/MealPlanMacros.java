package pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MealPlanMacros {

    private float calories;
    private float carbs;
    private float proteins;
    private float fats;

}
