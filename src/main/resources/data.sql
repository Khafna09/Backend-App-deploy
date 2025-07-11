-- ==============================================
-- LIMPIEZA DE DATOS (TRUNCATE o DELETE)
-- ==============================================
DELETE FROM allergy_ingredients;
DELETE FROM allergies;
DELETE FROM ingredients;
DELETE FROM objectives;
DELETE FROM activity_levels;
DELETE FROM recommendations;
DELETE FROM recommendation_templates;
DELETE FROM categories;
DELETE FROM recipe_types;

-- ==============================================
-- SEED DE CATEGORIES
-- ==============================================
INSERT INTO categories (id, name) VALUES
                                  (1,'Desayuno'),
                                  (2,'Almuerzo'),
                                  (3,'Cena'),
                                  (4,'Snack'),
                                  (5,'Postre');

-- ==============================================
-- SEED DE RECIPE_TYPES
-- ==============================================
INSERT INTO recipe_types (id, name) VALUES
                                    (1,'Postres'),
                                    (2,'Vegana'),
                                    (3,'Omnívora'),
                                    (4,'Vegetariana'),
                                    (5,'Paleo');

-- ==============================================
-- SEED DE OBJECTIVES
-- ==============================================
INSERT INTO objectives (id, objective_name, score) VALUES
                                                       (1, 'ganancia', 1),
                                                       (2, 'perdida', 2),
                                                       (3, 'mantenimiento', 3);

-- ==============================================
-- SEED DE INGREDIENTS
-- ==============================================
INSERT INTO ingredients (id, name, calories, carbohydrates, proteins, fats, macronutrient_values_id, created_at, updated_at) VALUES
                                                                                                                                 (1, 'nueces', 654.0, 21.0, 15.1, 6.0, 1, NOW(), NOW()),
                                                                                                                                 (2, 'tomate', 130.0, 3.5, 1.0, 0.5, 2, NOW(), NOW()),
                                                                                                                                 (3, 'cebolla', 40.0, 9.0, 1.1, 0.0, 3, NOW(), NOW()),
                                                                                                                                 (4, 'Pecanas', 691.0, 14.0, 9.2, 72.0, 3, NOW(), NOW()),
                                                                                                                                 (5, 'Leche', 61.0, 4.8, 3.2, 3.3, 3, NOW(), NOW()),
                                                                                                                                 (6, 'Queso', 402.0, 1.3, 25.0, 33.0, 3, NOW(), NOW());

-- ==============================================
-- SEED DE ALLERGIES
-- ==============================================
INSERT INTO allergies (id, name) VALUES
                                     (1, 'Alergia a las nueces'),
                                     (2, 'Intolerancia a la lactosa');

-- ==============================================
-- SEED DE ALLERGY_INGREDIENTS
-- ==============================================
INSERT INTO allergy_ingredients (allergy_id, ingredient_name) VALUES
                                                                  (1, 'nueces'),
                                                                  (1, 'pecanas'),
                                                                  (2, 'leche'),
                                                                  (2, 'queso');

-- ==============================================
-- SEED DE ACTIVITY LEVELS
-- ==============================================
INSERT INTO activity_levels (id, created_at, updated_at, name, description, activity_factor) VALUES
                                                                                                 (1, NOW(), NOW(), 'Sedentario',         'Poco o ningún ejercicio', 1.2),
                                                                                                 (2, NOW(), NOW(), 'Baja actividad',     'Ejercicio ligero/deporte 1 a 3 días por semana', 1.375),
                                                                                                 (3, NOW(), NOW(), 'Moderada actividad', 'Ejercicio moderado/deporte 3 a 5 días por semana', 1.55),
                                                                                                 (4, NOW(), NOW(), 'Regular activo',     'Ejercicio intenso/deporte 6 a 7 días por semana', 1.725),
                                                                                                 (5, NOW(), NOW(), 'Muy activo',         'Ejercicio muy intenso/trabajo físico o entrenamiento doble', 1.9);

-- ==============================================
-- SEED DE RECOMMENDATION TEMPLATES
-- ==============================================
INSERT INTO recommendation_templates (id, title, content, category) VALUES
                                                                        (1, 'Beber más agua', 'Recuerda beber al menos 8 vasos de agua al día para mantenerte hidratado.', 'Salud General'),
                                                                        (2, 'Ejercicio regular', 'Realiza al menos 30 minutos de actividad física moderada, cinco días a la semana.', 'Actividad Física'),
                                                                        (3, 'Más verduras', 'Incluye al menos una porción de verduras en cada comida.', 'Nutrición'),
                                                                        (4, 'Menos comida ultraprocesada', 'Reduce el consumo de alimentos ultraprocesados para mejorar tu salud.', 'Nutrición'),
                                                                        (5, 'Dormir bien', 'Procura dormir entre 7 y 8 horas cada noche para una recuperación adecuada.', 'Salud General');

-- ==============================================
-- SEED DE RECOMMENDATIONS
-- ==============================================
INSERT INTO recommendations (id, user_id, template_id, reason, notes, time_of_day, score, status, assigned_at, created_at, updated_at) VALUES
                                                                                                                                           (1, NULL, 1, 'Baja hidratación detectada en el perfil.', 'Se recomienda aumentar el consumo de agua.', 'MORNING', 9.5, 'ACTIVE', NULL, NOW(), NOW()),
                                                                                                                                           (2, NULL, 2, 'Reporta poco movimiento semanal.', 'Implementar rutina de caminatas diarias.', 'AFTERNOON', 8.0, 'ACTIVE', NULL, NOW(), NOW()),
                                                                                                                                           (3, NULL, 3, 'Consumo bajo de micronutrientes.', 'Agregar verduras variadas a cada comida.', 'EVENING', 7.0, 'ACTIVE', NULL, NOW(), NOW()),
                                                                                                                                           (4, NULL, 4, 'Ingesta elevada de ultraprocesados.', 'Reducir la cantidad de snacks ultraprocesados.', 'EVENING', 6.5, 'ACTIVE', NULL, NOW(), NOW()),
                                                                                                                                           (5, NULL, 5, 'Indicios de fatiga y sueño irregular.', 'Ajustar horario y condiciones para dormir mejor.', 'EVENING', 9.0, 'ACTIVE', NULL, NOW(), NOW());

-- ==============================================
-- SEED DE MEAL PLANS
-- ==============================================
INSERT INTO meal_plans (
    is_current,
    total_calories,
    total_carbs,
    total_fats,
    total_proteins,
    user_profile_id,
    created_at,
    updated_at,
    category,
    name,
    description
) VALUES
-- 1
(true, 2200, 250, 70, 130, 1, NOW(), NOW(), 'Ganar masa muscular', 'Plan Hipercalórico', 'Plan diseñado para subir de masa muscular en 8 semanas.'),
-- 2
(false, 1800, 150, 60, 110, 2, NOW(), NOW(), 'Perder peso', 'Plan Definición', 'Ideal para reducir grasa y mantener masa magra.'),
-- 3
(true, 2000, 200, 65, 120, 3, NOW(), NOW(), 'Balanceado', 'Plan Equilibrado', 'Plan de mantenimiento con proporciones balanceadas.'),
-- 4
(false, 1600, 130, 45, 100, 4, NOW(), NOW(), 'Vegetariano', 'Plan Veggie', 'Alimentación basada en vegetales y proteínas vegetales.'),
-- 5
(true, 2400, 270, 80, 140, 5, NOW(), NOW(), 'Deportistas', 'Plan Atleta', 'Diseñado para alto rendimiento físico.'),
-- 6
(false, 1900, 160, 50, 110, 6, NOW(), NOW(), 'Keto', 'Plan Cetogénico', 'Bajo en carbohidratos, alto en grasas buenas.'),
-- 7
(false, 2100, 200, 70, 125, 7, NOW(), NOW(), 'Balanceado', 'Plan Diario', 'Usado para consumo diario promedio.'),
-- 8
(true, 1750, 140, 55, 105, 8, NOW(), NOW(), 'Diabético', 'Plan Control Glucosa', 'Optimizado para control de azúcar en sangre.'),
-- 9
(false, 2300, 250, 75, 130, 9, NOW(), NOW(), 'Fitness', 'Plan Fit', 'Mezcla de ganancia muscular y baja grasa.'),
-- 10
(true, 1500, 120, 40, 90, 10, NOW(), NOW(), 'Ayuno intermitente', 'Plan 16/8', 'Plan adaptado a horarios de ayuno intermitente.');

