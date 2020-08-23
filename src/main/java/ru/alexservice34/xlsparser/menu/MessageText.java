package ru.alexservice34.xlsparser.menu;

public enum MessageText {
    INPUT_MENU_NUMBER("Введите номер меню"),
    MENU_NUMBER_ERROR("Такого номера не существует"),
    INPUT_ERROR("Некорректный ввод, повторите"),
    FOLDER_NOT_EXISTS_ERROR("Каталога 'xls' не существует"),
    XLS_FILE_NOT_FOUND("В каталоге xls не найдены .xls/.xlsx файлы"),
    TOO_MANY_XLS_FILES("Слишком много .xls/.xlsx файлов")
    ;
    private String value;

    MessageText(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
