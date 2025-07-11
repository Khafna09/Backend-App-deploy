package pe.edu.upc.center.backendNutriSmart.mealplan.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands.SeedMealPlanTypesCommand;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.entities.MealPlanType;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.MealPlanTypes;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.services.MealPlanTypeCommandService;
import pe.edu.upc.center.backendNutriSmart.mealplan.infrastructure.persistence.jpa.repositories.MealPlanTypeRepository;

import java.util.Arrays;

@Service
public class MealPlanTypeCommandServiceImpl implements MealPlanTypeCommandService {

    private final MealPlanTypeRepository mealPlanTypeRepository;

    public MealPlanTypeCommandServiceImpl(MealPlanTypeRepository mealPlanTypeRepository) {
        this.mealPlanTypeRepository = mealPlanTypeRepository;
    }

    @Override
    public void handle(SeedMealPlanTypesCommand command) {
        Arrays.stream(MealPlanTypes.values())
                .forEach(mealPlanType -> {
                    if (!mealPlanTypeRepository.existsByType(mealPlanType)){
                        mealPlanTypeRepository.save(new MealPlanType(MealPlanTypes.valueOf(mealPlanType.name())));
                    }
                });
    }
}
