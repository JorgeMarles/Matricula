package com.marlise.matriculas;

import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatriculasApplication {

    

    public static void main(String[] args) {
        LangManager.setLang("es");
        SpringApplication.run(MatriculasApplication.class, args);
    }

}
