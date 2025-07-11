package pe.edu.upc.center.backendNutriSmart.tracking.application.internal.commandservices;


import org.springframework.context.annotation.Lazy;
import pe.edu.upc.center.backendNutriSmart.mealplan.infrastructure.persistence.jpa.repositories.MealPlanEntryRepository;
import pe.edu.upc.center.backendNutriSmart.mealplan.infrastructure.persistence.jpa.repositories.MealPlanTypeRepository;
import pe.edu.upc.center.backendNutriSmart.tracking.application.internal.outboundservices.acl.ExternalProfileService;
import pe.edu.upc.center.backendNutriSmart.tracking.application.internal.outboundservices.acl.ExternalRecipeService;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MealPlanType;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingGoal;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingMealPlanEntry;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.aggregates.Tracking;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.CreateMealPlanEntryToTrackingCommand;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.CreateTrackingCommand;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.RemoveMealPlanEntryFromTrackingCommand;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.UpdateMealPlanEntryInTrackingCommand;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.services.TrackingCommandService;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TrackingCommandServiceImpl implements TrackingCommandService {
    private final TrackingRepository trackingRepository;
    private final TrackingMealPlanEntryRepository trackingMealPlanEntryRepository;
    private final TrackingGoalRepository trackingGoalRepository;
    private final MacronutrientValuesRepository macronutrientValuesRepository;
    private final TrackingMealPlanTypeRepository trackingMealPlanTypeRepository;
    ExternalProfileService externalProfileService;
    private final ExternalRecipeService externalRecipeService;

    public TrackingCommandServiceImpl(TrackingRepository trackingRepository, TrackingMealPlanEntryRepository mealPlanEntryRepository,
                                      TrackingGoalRepository trackingGoalRepository, MacronutrientValuesRepository macronutrientValuesRepository,
                                      TrackingMealPlanTypeRepository mealPlanTypeRepository, ExternalProfileService externalProfileService,
                                      @Lazy ExternalRecipeService externalRecipeService) {
        this.trackingRepository = trackingRepository;
        this.trackingMealPlanEntryRepository = mealPlanEntryRepository;
        this.trackingGoalRepository = trackingGoalRepository;
        this.macronutrientValuesRepository = macronutrientValuesRepository;
        this.trackingMealPlanTypeRepository = mealPlanTypeRepository;
        this.externalProfileService = externalProfileService;
        this.externalRecipeService = externalRecipeService;
    }

    @Override
    public int handle(CreateMealPlanEntryToTrackingCommand command) {
        if (!externalRecipeService.existsByRecipeId(command.recipeId())) {
            throw new IllegalArgumentException("Recipe not found in Recipe bounded context with id: " + command.recipeId());
        }
        // Buscar el tracking por ID de usuario
        Optional<Tracking> trackingOpt = trackingRepository.findByUserId(command.userId());

        if (trackingOpt.isEmpty()) {
            throw new IllegalArgumentException("Tracking not found for user: " + command.userId());
        }

        MealPlanType mealPlanType = trackingMealPlanTypeRepository.findByName(command.mealPlanType().getName())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de plan de comida inválido: " + command.mealPlanType()));


        Tracking tracking = trackingOpt.get();

        // Crear nuevo MealPlanEntry
        TrackingMealPlanEntry newEntry = new TrackingMealPlanEntry(
                command.recipeId(),
                mealPlanType,
                command.DayNumber()
        );

        newEntry.setTracking(tracking);

        // Agregar al tracking (esto solo lo agrega a la lista en memoria)
        tracking.addMealPlanEntry(newEntry);

        // NUEVO: Guardar el tracking primero
        Tracking savedTracking = trackingRepository.save(tracking);

        return savedTracking.getId();
    }

    @Override
    public void handle(RemoveMealPlanEntryFromTrackingCommand command) {
        // Buscar el tracking
        Optional<Tracking> trackingOpt = trackingRepository.findById(command.TrackingId());

        if (trackingOpt.isEmpty()) {
            throw new IllegalArgumentException("Tracking not found with id: " + command.TrackingId());
        }

        Tracking tracking = trackingOpt.get();

        Optional<TrackingMealPlanEntry> mealPlanEntryOpt = trackingMealPlanEntryRepository.findById(command.MealPlanEntryId());

        if (mealPlanEntryOpt.isEmpty()) {
            throw new IllegalArgumentException("MealPlan not found with id: " + command.MealPlanEntryId());
        }

        TrackingMealPlanEntry mealPlanEntry = mealPlanEntryOpt.get();

        // Remover el MealPlanEntry
        boolean removed = tracking.removeMealPlanEntry(mealPlanEntry);

        if (!removed) {
            throw new IllegalArgumentException("MealPlanEntry not found with id: " + command.MealPlanEntryId());
        }

        // Guardar el tracking
        trackingRepository.save(tracking);
    }

    @Override
    public Optional<Tracking> handle(UpdateMealPlanEntryInTrackingCommand command) {
        // Verificar que la receta existe en el bounded context de Recipe
        if (!externalRecipeService.existsByRecipeId(command.recipeId())) {
            throw new IllegalArgumentException("Recipe not found in Recipe bounded context with id: " + command.recipeId());
        }
        Long trackingId = command.TrackingId();

        // Si el trackingId no viene en el command (es null o 0), lo obtenemos desde el meal plan entry
        if (trackingId == null || trackingId == 0L) {
            Optional<TrackingMealPlanEntry> mealPlanEntryOpt = trackingMealPlanEntryRepository.findById(command.MealPlanEntryId());

            if (mealPlanEntryOpt.isEmpty()) {
                throw new IllegalArgumentException("MealPlan not found with id: " + command.MealPlanEntryId());
            }

            TrackingMealPlanEntry existingEntry = mealPlanEntryOpt.get();
            trackingId = (long) existingEntry.getTracking().getId();
        }

        Optional<Tracking> trackingOpt = trackingRepository.findById(trackingId);

        if (trackingOpt.isEmpty()) {
            return Optional.empty();
        }

        Tracking tracking = trackingOpt.get();

        Optional<TrackingMealPlanEntry> mealPlanEntryOpt = trackingMealPlanEntryRepository.findById(command.MealPlanEntryId());

        if (mealPlanEntryOpt.isEmpty()) {
            throw new IllegalArgumentException("MealPlan not found with id: " + command.MealPlanEntryId());
        }

        TrackingMealPlanEntry mealPlanEntry = mealPlanEntryOpt.get();

        MealPlanType mealPlanType = trackingMealPlanTypeRepository.findByName(command.mealPlanType())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de plan de comida inválido: " + command.mealPlanType()));

        // ACTUALIZAR en lugar de eliminar y crear
        mealPlanEntry.setRecipeId(command.recipeId());
        mealPlanEntry.setMealPlanType(mealPlanType);
        mealPlanEntry.setDayNumber(command.dayNumber());

        // Guardar el entry actualizado
        trackingMealPlanEntryRepository.save(mealPlanEntry);

        // Guardar el tracking también (por si hay cambios en cascada)
        Tracking savedTracking = trackingRepository.save(tracking);

        return Optional.of(savedTracking);
    }

    public int handle(CreateTrackingCommand command) {

        // NUEVA VERIFICACIÓN
        if (!externalProfileService.existsByUserId(command.profile())) {
            throw new IllegalArgumentException("User does not exist in Profile bounded context: " + command.profile().userId());
        }

        if(this.trackingRepository.existsByUserId(command.profile())){
            throw new IllegalArgumentException("Tracking already exists for user: " + command.profile());
        }

        Optional<TrackingGoal> trackingGoalOpt = trackingGoalRepository.findByUserId(command.profile());

        if (trackingGoalOpt.isEmpty()) {
            throw new IllegalArgumentException("Tracking goal not found for user: " + command.profile());
        }
        TrackingGoal trackingGoal = trackingGoalOpt.get();
        LocalDate date = LocalDate.now();
        MacronutrientValues consumed = new MacronutrientValues(0, 0, 0, 0);
        macronutrientValuesRepository.save(consumed);

        var tracking = new Tracking(command.profile(),date,trackingGoal, consumed);

        try{
            trackingRepository.save(tracking);
        } catch(Exception e){
            throw new IllegalArgumentException("Error while saving tracking:" + e.getMessage());
        }
        return tracking.getId();
    }
}