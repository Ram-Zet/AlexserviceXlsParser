package ru.alexservice34.xlsparser.forsqlscript;

import com.google.common.io.Resources;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MakeSql {
    private static final String INSERT_PATTERN = "INSERT INTO dictionaries.streets (city, street) VALUES ";
    private static final String VALUE_PATTERN = "('Волгоград','%s')";
    private static final String DELIMITER = ",/n";
    private static final String END_OF_STRING = ";";

    @Test
    @Disabled
    public void makeSqlStreets() throws Exception {
        List<String> streets = Resources.readLines(Resources.getResource("streets.txt"), StandardCharsets.UTF_8);

        StringBuilder sb = new StringBuilder(INSERT_PATTERN);
        for (int i = 0; i < streets.size(); i++) {
            sb.append(String.format(VALUE_PATTERN, streets.get(i)));
            if (i < streets.size() - 1) sb.append(DELIMITER);
        }
        sb.append(END_OF_STRING);

        Files.write(Paths.get("streets.sql"), sb.toString().getBytes());
    }
}
