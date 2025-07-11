package pe.edu.upc.center.backendNutriSmart.profiles.domain.model.Entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "objectives")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @Column(name = "objective_name", length = 100, nullable = false)
    private String objectiveName;

    @Column(name = "score", nullable = false)
    private int score;


    public Objective(String objectiveName, int score) {
        this.objectiveName = objectiveName;
        this.score = score;
    }

    //metodos
    public int calculateScore() {

        return this.score;
    }

    public String getDescription() {
        return String.format("Objective: %s, Score: %d", this.objectiveName, this.score);
    }
}