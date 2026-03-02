package ui.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

public class ExcelUtils {

    private ExcelUtils() {}

    public static List<Map<String, String>> readSheetAsMaps(String resourcePath, String sheetName) {
        try (InputStream is = ExcelUtils.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Excel not found on classpath: " + resourcePath);
            }

            try (Workbook workbook = new XSSFWorkbook(is)) {
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new IllegalArgumentException("Sheet not found: " + sheetName);
                }

                DataFormatter formatter = new DataFormatter();
                Iterator<Row> rowIterator = sheet.rowIterator();
                if (!rowIterator.hasNext()) return Collections.emptyList();

                // Header row
                Row headerRow = rowIterator.next();
                List<String> headers = new ArrayList<>();
                int lastCellNum = headerRow.getLastCellNum();

                for (int i = 0; i < lastCellNum; i++) {
                    Cell cell = headerRow.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    String header = cell == null ? "" : formatter.formatCellValue(cell).trim();
                    headers.add(header);
                }

                // Data rows
                List<Map<String, String>> rows = new ArrayList<>();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Map<String, String> rowMap = new LinkedHashMap<>();

                    for (int i = 0; i < headers.size(); i++) {
                        String header = headers.get(i);
                        if (header == null || header.isBlank()) continue; // skip blank header columns

                        Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        String value = (cell == null) ? "" : formatter.formatCellValue(cell).trim();
                        rowMap.put(header, value);
                    }

                    boolean allEmpty = rowMap.values().stream().allMatch(String::isEmpty);
                    if (!allEmpty) rows.add(rowMap);
                }

                return rows;
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed reading excel. file=" + resourcePath + ", sheet=" + sheetName, e);
        }
    }

    public static ExcelRow findByTestCaseId(String resourcePath, String sheetName, String testCaseId) {
        List<Map<String, String>> rows = readSheetAsMaps(resourcePath, sheetName);

        Map<String, String> match = rows.stream()
                .filter(r -> testCaseId.equalsIgnoreCase(r.getOrDefault("testCaseId", "").trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No row found for testCaseId=" + testCaseId
                        + " in sheet=" + sheetName));

        return new ExcelRow(match);
    }
}