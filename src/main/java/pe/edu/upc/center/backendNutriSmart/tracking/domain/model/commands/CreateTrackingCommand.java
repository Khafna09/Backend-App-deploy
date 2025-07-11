package pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingGoal;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.UserId;

import java.time.LocalDate;

public record CreateTrackingCommand(UserId profile) {}