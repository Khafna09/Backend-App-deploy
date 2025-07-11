package pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities;


import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.MealPlanTypes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity (name = "Tracking_MealPlanType")
@Table(name = "mealplan_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
@With
public class MealPlanType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 12)
    private MealPlanTypes name;

    public MealPlanType(MealPlanTypes name) { this.name = name; }

    public String ToString() { return name.name(); }

    public static MealPlanType getDefaultMealPlanType() {
        return new MealPlanType(MealPlanTypes.HEALTHY);
    }
}
