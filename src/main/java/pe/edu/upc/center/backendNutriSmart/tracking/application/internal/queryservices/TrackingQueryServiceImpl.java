package pe.edu.upc.center.backendNutriSmart.tracking.application.internal.queryservices;

import pe.edu.upc.center.backendNutriSmart.mealplan.infrastructure.persistence.jpa.repositories.MealPlanEntryRepository;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingMealPlanEntry;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.aggregates.Tracking;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetAllMealsQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetConsumedMacrosQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetTrackingByUserIdQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.services.TrackingQueryService;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.MacronutrientValuesRepository;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.TrackingMealPlanEntryRepository;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.TrackingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackingQueryServiceImpl implements TrackingQueryService {
    private final TrackingRepository trackingRepository;
    private final MacronutrientValuesRepository macronutrientValuesRepository;
    private final TrackingMealPlanEntryRepository trackingMealPlanEntryRepository;

    public TrackingQueryServiceImpl(TrackingRepository trackingRepository, MacronutrientValuesRepository macronutrientValuesRepository
            , TrackingMealPlanEntryRepository trackingMealPlanEntryRepository) {
        this.trackingRepository = trackingRepository;
        this.macronutrientValuesRepository = macronutrientValuesRepository;
        this.trackingMealPlanEntryRepository = trackingMealPlanEntryRepository;
    }

    @Override
    public List<TrackingMealPlanEntry> handle(GetAllMealsQuery query) {
        return trackingMealPlanEntryRepository.findAllByTrackingId(query.TrackingId());
    }

    @Override
    public Optional<Tracking> handle(GetTrackingByUserIdQuery query) {
        return this.trackingRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<MacronutrientValues> handle(GetConsumedMacrosQuery query) {
        return trackingRepository.findById(query.trackingId())
                .map(Tracking::getConsumedMacros);
    }
}
