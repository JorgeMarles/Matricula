package com.marlise.matriculas;

import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatriculasApplication {

    public static final Locale locale = Locale.forLanguageTag("es");
    public static ResourceBundle resourceBundle;

    public static void main(String[] args) {
        resourceBundle = ResourceBundle.getBundle("application_messages", locale);
        SpringApplication.run(MatriculasApplication.class, args);
    }

}
