package pe.edu.upc.center.backendNutriSmart.tracking.application.internal.commandservices;

import pe.edu.upc.center.backendNutriSmart.tracking.application.internal.outboundservices.acl.ExternalProfileService;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingGoal;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.*;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.GoalTypes;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.UserId;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.services.TrackingGoalCommandService;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.MacronutrientValuesRepository;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.TrackingGoalRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class TrackingGoalCommandServiceImpl implements TrackingGoalCommandService {

    private final TrackingGoalRepository trackingGoalRepository;
    private final MacronutrientValuesRepository macronutrientValuesRepository;
    private final ExternalProfileService externalProfileService;

    public TrackingGoalCommandServiceImpl(TrackingGoalRepository trackingGoalRepository,
                                          MacronutrientValuesRepository macronutrientValuesRepository,
                                          ExternalProfileService externalProfileService) {
        this.trackingGoalRepository = trackingGoalRepository;
        this.macronutrientValuesRepository = macronutrientValuesRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public Long handle(CreateTrackingGoalCommand command) {
        // Validar que el perfil existe
        externalProfileService.validateProfileExists(command.profile().userId());

        if (trackingGoalRepository.existsByUserId(command.profile())) {
            throw new IllegalArgumentException("Tracking goal already exists for user: " + command.profile());
        }

        var trackingGoal = new TrackingGoal(command.profile(), command.macronutrientValues());
        trackingGoalRepository.save(trackingGoal);
        return trackingGoal.getId();
    }

    public void handle(UpdateTrackingGoalCommand command) {
        // Validar que el perfil existe
        externalProfileService.validateProfileExists(command.userId().userId());

        Optional<TrackingGoal> trackingGoalOpt = trackingGoalRepository.findByUserId(command.userId());

        if (trackingGoalOpt.isEmpty()) {
            throw new IllegalArgumentException("Tracking goal not found for user: " + command.userId());
        }

        TrackingGoal trackingGoal = trackingGoalOpt.get();

        // Crear nuevos valores de macronutrientes basados en el tipo de objetivo
        MacronutrientValues newMacros = new MacronutrientValues(
                command.goalType().getCalories(),
                command.goalType().getCarbs(),
                command.goalType().getProteins(),
                command.goalType().getFats()
        );

        // Guardar los nuevos valores de macronutrientes
        macronutrientValuesRepository.save(newMacros);

        // Actualizar el tracking goal con los nuevos macros
        TrackingGoal updatedTrackingGoal = new TrackingGoal(command.userId(), newMacros);
        updatedTrackingGoal.setId(trackingGoal.getId()); // Mantener el mismo ID

        trackingGoalRepository.save(updatedTrackingGoal);
    }

    /**
     * Crea un tracking goal automáticamente basado en el objetivo del perfil
     * @param profileId ID del perfil
     * @return ID del tracking goal creado
     */
    public Long createTrackingGoalFromProfile(Long profileId) {
        // Obtener el objetivo del perfil y validar que existe
        String objectiveName = externalProfileService.getValidatedObjectiveName(profileId);

        // Mapear el objetivo del perfil a un GoalType
        GoalTypes goalType = mapObjectiveToGoalType(objectiveName);

        // Crear los macronutrientes basados en el tipo de objetivo
        MacronutrientValues macros = new MacronutrientValues(
                goalType.getCalories(),
                goalType.getCarbs(),
                goalType.getProteins(),
                goalType.getFats()
        );

        // Guardar los macronutrientes
        macronutrientValuesRepository.save(macros);

        // Crear el comando y ejecutarlo
        var command = new CreateTrackingGoalCommand(
                new UserId(profileId),
                macros
        );

        return handle(command);
    }

    /**
     * Actualiza un tracking goal basado en el objetivo actual del perfil
     * @param profileId ID del perfil
     */
    public void updateTrackingGoalFromProfile(Long profileId) {
        // Obtener el objetivo del perfil y validar que existe
        String objectiveName = externalProfileService.getValidatedObjectiveName(profileId);

        // Mapear el objetivo del perfil a un GoalType
        GoalTypes goalType = mapObjectiveToGoalType(objectiveName);

        // Crear el comando y ejecutarlo
        var command = new UpdateTrackingGoalCommand(
                new UserId(profileId),
                goalType
        );

        handle(command);
    }

    /**
     * Mapea el nombre del objetivo del perfil a un GoalType de tracking
     * @param objectiveName Nombre del objetivo del perfil
     * @return GoalType correspondiente
     */
    private GoalTypes mapObjectiveToGoalType(String objectiveName) {
        return switch (objectiveName.toLowerCase()) {
            case "ganancia", "ganancia muscular", "ganar peso", "muscle gain" -> GoalTypes.GANANCIA_MUSCULAR;
            case "perdida", "perdida de peso", "perder peso", "weight loss" -> GoalTypes.PERDIDA_PESO;
            case "mantenimiento", "mantener peso", "maintenance" -> GoalTypes.MANTENIMIENTO;
            default -> throw new IllegalArgumentException("Unknown objective: " + objectiveName +
                    ". Valid objectives are: ganancia, perdida, mantenimiento");
        };
    }

    /**
     * Verifica si un tracking goal existe para un perfil
     * @param profileId ID del perfil
     * @return true si existe, false en caso contrario
     */
    public boolean existsTrackingGoalForProfile(Long profileId) {
        return trackingGoalRepository.existsByUserId(
                new UserId(profileId)
        );
    }
}
