package pe.edu.upc.center.backendNutriSmart.tracking.application.internal.queryservices;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingGoal;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetConsumedMacrosQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetMacronutrientValuesByIdQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetTargetMacronutrientsQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.services.MacronutrientValuesQueryService;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.MacronutrientValuesRepository;
import pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories.TrackingGoalRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MacronutrientValuesQueryServiceImpl implements MacronutrientValuesQueryService {

    private final MacronutrientValuesRepository macronutrientValuesRepository;

    public MacronutrientValuesQueryServiceImpl(MacronutrientValuesRepository macronutrientValuesRepository) {
        this.macronutrientValuesRepository = macronutrientValuesRepository;
    }

    // Métodos existentes...

    @Override
    public Optional<MacronutrientValues> handle(GetMacronutrientValuesByIdQuery query) {
        return macronutrientValuesRepository.findById(query.macronutrientValuesId());
    }
}