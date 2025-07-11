package pe.edu.upc.center.backendNutriSmart.profiles.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.CreateUserProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.Entities.Objective;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.Entities.ActivityLevel;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.Entities.Allergy;
import pe.edu.upc.center.backendNutriSmart.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_profiles")
public class UserProfile extends AuditableAbstractAggregateRoot<UserProfile> {

    // Atributos del perfil
    @Getter
    @NotNull
    @Column(name = "gender", length = 25, nullable = false)
    private String gender;

    @Getter
    @NotNull
    @Column(name = "height", nullable = false)
    private double height;

    @Getter
    @NotNull
    @Column(name = "weight", nullable = false)
    private double weight;

    @Getter
    @NotNull
    @Column(name = "user_score", nullable = false)
    private int userScore;

    // Relaciones con entidades
    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_level_id", nullable = false)
    private ActivityLevel activityLevel;

    @Getter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "objective_id", nullable = false)
    private Objective objective;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_profile_allergies",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private List<Allergy> allergies = new ArrayList<>();


    public UserProfile() {
    }


    public UserProfile(String gender, double height, double weight, ActivityLevel activityLevel,
                       Objective objective, int userScore) {
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.objective = objective;
        this.userScore = userScore;
        this.allergies = new ArrayList<>();
    }


    public UserProfile(CreateUserProfileCommand command,
                       ActivityLevel activityLevel,
                       Objective objective) {
        this.gender = command.gender();
        this.height = command.height();
        this.weight = command.weight();
        this.userScore = command.userScore();
        this.activityLevel = activityLevel;
        this.objective = objective;
        this.allergies = new ArrayList<>();
    }


    public UserProfile updateProfile(String gender, double height, double weight, ActivityLevel activityLevel,
                                     Objective objective, int userScore) {
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.objective = objective;
        this.userScore = userScore;
        return this;
    }

    // Métodos para manipular alergias
    public void addAllergy(Allergy allergy) {
        this.allergies.add(allergy);
    }

    public void removeAllergy(Allergy allergy) {
        this.allergies.remove(allergy);
    }

    // Métodos de comportamiento orientados a dominio
    public double calculateCalorieNeeds(int age) {
        return this.activityLevel.calculateCalories(this.weight, this.height, age);
    }



    // Métodos para actualizar relaciones individuales
    public void updateActivityLevel(ActivityLevel newLevel) {
        this.activityLevel = newLevel;
    }

    public void updateObjective(Objective newObjective) {
        this.objective = newObjective;
    }

}
