package pe.edu.upc.center.backendNutriSmart.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // anotación que indica que esta clase es una clase de configuración de Spring
// clase que configura la documentación de la API utilizando OpenAPI
public class OpenApiConfiguration {
    @Bean // anotación que indica que este método devuelve un bean de Spring
    // un bean es un objeto que es instanciado, ensamblado y administrado por el contenedor de Spring

    // método que devuelve una instancia de OpenAPI configurada para la aplicación Learning Platform
    public OpenAPI learningPlatformOpenApi() {
        // General configuration
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Nutri Smart API")
                        .description("Nutri Smart Backend API - Open Source"));






        final String securitySchemeName = "bearerAuth";

        openApi.addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));

        // Return OpenAPI configuration object with all the settings
        return openApi;
    }
}