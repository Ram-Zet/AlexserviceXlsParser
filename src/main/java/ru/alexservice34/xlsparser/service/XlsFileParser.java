package ru.alexservice34.xlsparser.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.Optional;

@Service
public class XlsFileParser {
    public void parse(Path xlsFile) {
        try (InputStream fis = Files.newInputStream(xlsFile)) {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheetAt(0);
            processMainSheet(sheet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processMainSheet(XSSFSheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        //Пропускаем строчку с шапкой
        rowIterator.next();
        while (rowIterator.hasNext()) {
            processRow(rowIterator.next());
        }
    }

    private void processRow(Row row) {
        //id
        int id = (int) row.getCell(0).getNumericCellValue();
        if (id == 413) {
            System.out.println();
        }

        //адрес
        String addressString = Optional.ofNullable(row.getCell(1))
                .map(Cell::getStringCellValue).orElse(null);

        //город
        String city = Optional.ofNullable(row.getCell(2))
                .map(Cell::getStringCellValue).orElse(null);

        //комментарий/имя
        String name = Optional.ofNullable(row.getCell(3))
                .map(Cell::getStringCellValue).orElse(null);

        //номер телефона
        String number = Optional.ofNullable(row.getCell(4))
                .map(Cell::getStringCellValue)
                .map(e -> e.replace("!", ""))
                .orElse(null);

        //Дата звонка
        LocalDate orderStartDate = Optional.ofNullable(row.getCell(5))
                .map(Cell::getDateCellValue)
                .map(dt -> dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .orElse(null);

        //Цель обращения
        String issue = Optional.ofNullable(row.getCell(6))
                .map(Cell::getStringCellValue).orElse(null);

        //CurrentStatus
        String currentStatus = Optional.ofNullable(row.getCell(7))
                .map(Cell::getStringCellValue).orElse(null);

        //Выполненные работы
        String works = Optional.ofNullable(row.getCell(8))
                .map(Cell::getStringCellValue).orElse(null);

        //Дата выполнения
        LocalDate dateComplete = Optional.ofNullable(row.getCell(9))
                .map(Cell::getDateCellValue)
                .map(dt -> dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .orElse(null);

        //стоимость закупки деталей
        Integer partsBuyCost = Optional.ofNullable(row.getCell(10))
                .map(cell -> (int) cell.getNumericCellValue())
                .orElse(null);

        //стоимость продажи деталей
        Integer partsSellCost = Optional.ofNullable(row.getCell(11))
                .map(cell -> (int) cell.getNumericCellValue())
                .orElse(null);

        //стоимость работ
        Integer worksCost = Optional.ofNullable(row.getCell(12))
                .map(cell -> (int) cell.getNumericCellValue())
                .orElse(null);

        //Комментарий
        String comment = Optional.ofNullable(row.getCell(13))
                .map(Cell::getStringCellValue).orElse(null);

        //Техник
         String employee = Optional.ofNullable(row.getCell(14))
                .map(Cell::getStringCellValue).orElse(null);

        System.out.println(id + " | " + addressString + " | " + city
                + " | " + name
                + " | " + number
                + " | " + issue
                + " | " + currentStatus
                + " | " + works
                + " | " + dateComplete
                + " | " + partsBuyCost
                + " | " + partsSellCost
                + " | " + worksCost
                + " | " + comment
                + " | " + employee
        );
    }
}
