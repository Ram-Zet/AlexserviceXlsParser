package ru.alexservice34.xlsparser.menu;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;
import ru.alexservice34.xlsparser.service.XlsFileParser;

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
import static ru.alexservice34.xlsparser.menu.MessageText.TOO_MANY_XLS_FILES;
import static ru.alexservice34.xlsparser.menu.MessageText.XLS_FILE_NOT_FOUND;

@Service
public class MainMenu {
    private Map<MenuItem, Runnable> menuMethods;
    private List<MenuItem> menues;

    private final ConsoleHelper consoleHelper;
    private final XlsFileParser xlsFileParser;

    public MainMenu(ConsoleHelper consoleHelper, XlsFileParser xlsFileParser) {
        this.consoleHelper = consoleHelper;
        this.xlsFileParser = xlsFileParser;
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
            List<Path> xlsFiles = Files.list(xlsFolder)
                    .filter(filePath ->{
                        System.out.println(filePath.toUri());
                        return filePath.toUri().toString().endsWith(".xls") || filePath.toString().endsWith("xlsx");})
                    .collect(Collectors.toList());
            if (xlsFiles.isEmpty()) {
                consoleHelper.print(XLS_FILE_NOT_FOUND);
                return;
            }
            if (xlsFiles.size()>1) {
                consoleHelper.print(TOO_MANY_XLS_FILES);
                return;
            }
            xlsFileParser.parse(xlsFiles.get(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit() {
        System.exit(0);
    }


}
