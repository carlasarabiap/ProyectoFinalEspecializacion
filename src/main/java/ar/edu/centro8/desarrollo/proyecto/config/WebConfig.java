package ar.edu.centro8.desarrollo.proyecto.config;

import org.springframework.lang.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://127.0.0.1:5501") // Origen del front-end
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos los headers
                .allowCredentials(true); // Si necesitas enviar cookies o autenticación
    }
}



// @Configuration
// public class CorsConfig {
//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 registry.addMapping("/**") // Permite todos los endpoints
//                         .allowedOrigins("http://127.0.0.1:5501") // Permite solicitudes solo desde el frontend
//                         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos permitidos
//                         .allowedHeaders("*") // Permite todos los headers
//                         .allowCredentials(true); // Permite envío de cookies si es necesario
//             }
//         };
//     }
// }
