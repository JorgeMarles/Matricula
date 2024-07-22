package com.marlise.matriculas;

import com.marlise.matriculas.storage.StorageProperties;
import com.marlise.matriculas.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class MatriculasApplication {

    

    public static void main(String[] args) {
        LangManager.setLang("es");
        SpringApplication.run(MatriculasApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }

}
