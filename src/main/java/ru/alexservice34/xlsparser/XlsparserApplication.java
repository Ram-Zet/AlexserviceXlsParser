package ru.alexservice34.xlsparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.alexservice34.xlsparser.menu.MainMenu;

@SpringBootApplication
public class XlsparserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(XlsparserApplication.class, args);
        applicationContext.getBean(MainMenu.class).startMenu();
    }

}
