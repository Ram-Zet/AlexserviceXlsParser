package ru.alexservice34.xlsparser.menu;

public enum MenuItem {
    READ_XLS_FILE("Read xls file"),
    EXIT("exit");

    private String name;

    MenuItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
