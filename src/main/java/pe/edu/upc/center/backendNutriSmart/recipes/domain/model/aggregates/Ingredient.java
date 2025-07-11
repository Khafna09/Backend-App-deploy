package pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.valueobjects.MacronutrientValuesId;
import pe.edu.upc.center.backendNutriSmart.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "ingredients")
@ToString
@NoArgsConstructor
public class Ingredient extends AuditableAbstractAggregateRoot<Ingredient> {

    @Getter
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Getter
    @Column(name = "calories", nullable = false)
    private double calories;

    @Getter
    @Column(name = "carbohydrates", nullable = false)
    private double carbohydrates;

    @Getter
    @Column(name = "proteins", nullable = false)
    private double proteins;

    @Getter
    @Column(name = "fats", nullable = false)
    private double fats;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "macronutrientValuesId", column = @Column(name = "macronutrient_values_id", nullable = false))
    })
    private MacronutrientValuesId macronutrientValuesId = new MacronutrientValuesId();

    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipes = new HashSet<>();

    // -----------------------------------

    public Ingredient(String name, double calories, double carbohydrates, double proteins, double fats, Long macronutrientValuesId) {
        this.name = name;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.proteins = proteins;
        this.fats = fats;
        this.macronutrientValuesId = new MacronutrientValuesId(macronutrientValuesId);
    }

    // Getter explícito para el value object
    public Long getMacronutrientValuesId() {
        return this.macronutrientValuesId.macronutrientValuesId();
    }
}
