package ru.alexservice34.xlsparser.menu;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.alexservice34.xlsparser.menu.MenuItem.EXIT;
import static ru.alexservice34.xlsparser.menu.MenuItem.READ_XLS_FILE;

@Service
public class MainMenu {
    private Map<MenuItem, Runnable> menuMethods;
    private List<MenuItem> menues;

    private final ConsoleHelper consoleHelper;

    public MainMenu(ConsoleHelper consoleHelper) {
        this.consoleHelper = consoleHelper;
    }

    @PostConstruct
    public void init() {

        menuMethods = ImmutableMap.<MenuItem, Runnable>builder()
                .put(READ_XLS_FILE, this::readXlsFile)
                .put(EXIT, this::exit)
                .build();

        menues = ImmutableList.<MenuItem>builder()
                .add(READ_XLS_FILE)
                .add(EXIT)
                .build();
    }

    public void startMenu() {
        while (true) {
            consoleHelper.printMenu(menues);
            MenuItem choosedItem = consoleHelper.chooseMenuItem(menues);
            menuMethods.get(choosedItem).run();
        }
    }

    private void readXlsFile() {
        Path xlsFolder = Paths.get("xls");
        if (!Files.exists(xlsFolder) && Files.isDirectory(xlsFolder) ) {
            consoleHelper.print(MessageText.FOLDER_NOT_EXISTS_ERROR);
            return;
        }
        try {
            Files.list(xlsFolder).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        System.exit(0);
    }


}
