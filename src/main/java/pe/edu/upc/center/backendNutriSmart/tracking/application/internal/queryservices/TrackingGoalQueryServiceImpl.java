package pe.edu.upc.center.backendNutriSmart.tracking.application.internal.queryservices;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingGoal;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetTargetMacronutrientsQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetTrackingGoalByUserIdQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.services.TrackingGoalQueryService;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.TrackingGoalRepository;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.TrackingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackingGoalQueryServiceImpl implements TrackingGoalQueryService {
    TrackingGoalRepository trackingGoalRepository;

    public TrackingGoalQueryServiceImpl(TrackingGoalRepository trackingGoalRepository) {
        this.trackingGoalRepository = trackingGoalRepository;
    }

    public Optional<TrackingGoal> handle(GetTrackingGoalByUserIdQuery query) {
        return this.trackingGoalRepository.findByUserId(query.userId());
    }

    public Optional<MacronutrientValues> handle(GetTargetMacronutrientsQuery query) {
        // En lugar de usar el repository directamente, busca el TrackingGoal y obtén sus targetMacros
        Optional<TrackingGoal> trackingGoalOpt = trackingGoalRepository.findById(query.TrackingGoalId());

        if (trackingGoalOpt.isPresent()) {
            return Optional.ofNullable(trackingGoalOpt.get().getTargetMacros());
        }

        return Optional.empty();
    }
}

