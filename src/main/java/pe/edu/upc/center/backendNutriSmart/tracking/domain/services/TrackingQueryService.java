package pe.edu.upc.center.backendNutriSmart.tracking.domain.services;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingMealPlanEntry;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.aggregates.Tracking;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetAllMealsQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetConsumedMacrosQuery;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.queries.GetTrackingByUserIdQuery;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TrackingQueryService {
    List<TrackingMealPlanEntry> handle(GetAllMealsQuery query);
    Optional<Tracking> handle(GetTrackingByUserIdQuery query);
    Optional<MacronutrientValues> handle(GetConsumedMacrosQuery query);
}
