// MacronutrientValuesResourceFromEntityAssembler.java
package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources.MacronutrientValuesResource;

public class MacronutrientValuesResourceFromEntityAssembler {
    public static MacronutrientValuesResource toResource(MacronutrientValues entity) {
        return new MacronutrientValuesResource(
                entity.getId(),
                entity.getCalories(),
                entity.getCarbs(),
                entity.getProteins(),
                entity.getFats()
        );
    }
}