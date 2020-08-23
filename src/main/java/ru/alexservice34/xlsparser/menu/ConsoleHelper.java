package ru.alexservice34.xlsparser.menu;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static ru.alexservice34.xlsparser.menu.MessageText.*;

@Service
public class ConsoleHelper {
    private BufferedReader reader;

    @PostConstruct
    public void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @PreDestroy
    public void destroy() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print(Object o) {
        System.out.println(o);
    }

    public void printMenu(List<MenuItem> menuItems) {
        for (int i = 1; i <= menuItems.size(); i++) {
            print(String.format("%d. %s", i, menuItems.get(i-1).toString()));
        }
    }

    public MenuItem chooseMenuItem(List<MenuItem> items) {
        while (true) {
            print(INPUT_MENU_NUMBER);
            int menuNum = readInt();
            if (items.size() < menuNum) {
                print(MENU_NUMBER_ERROR);
                continue;
            }
            menuNum--;
            return items.get(menuNum);
        }
    }

    public int readInt() {
        while (true) {
            try {
                return Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                print(INPUT_ERROR);
            }
        }
    }

    public String readString() {
        while (true) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
