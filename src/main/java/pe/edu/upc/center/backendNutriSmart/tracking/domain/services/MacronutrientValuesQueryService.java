package pe.edu.upc.center.backendNutriSmart.tracking.domain.services;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetConsumedMacrosQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetMacronutrientValuesByIdQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetTargetMacronutrientsQuery;

import java.util.Optional;

public interface MacronutrientValuesQueryService {

    // Métodos existentes...

    /**
     * Obtiene MacronutrientValues por ID
     */
    Optional<MacronutrientValues> handle(GetMacronutrientValuesByIdQuery query);
}