package pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.UserId;

public record CreateTrackingGoalCommand(UserId profile, MacronutrientValues macronutrientValues) {
}
