package Rest.Rest.API.Automation.Utility;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.lang.reflect.Field;
import Rest.Rest.API.Automation.pojo.UserData;

public class ExcelLib {

    private Workbook workbook;
    private Sheet worksheet;
    private List<String> columnHeaders;
    private List<UserData> testData;

    public ExcelLib(String filePath, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        worksheet = workbook.getSheet(sheetName);
        columnHeaders = extractColumnHeaders(worksheet);
        testData = extractTestData(worksheet);
    }

    private List<String> extractColumnHeaders(Sheet sheet) {
        List<String> headers = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }
        return headers;
    }

    private List<UserData> extractTestData(Sheet sheet) {
        List<UserData> data = new ArrayList<>();

        for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            UserData userData = new UserData();

            for (int colIndex = 0; colIndex < columnHeaders.size(); colIndex++) {
                Cell cell = row.getCell(colIndex);
                String cellValue = "";

                if (cell != null) {
                    cellValue = cell.toString();
                }

                String fieldName = columnHeaders.get(colIndex);
                setUserDataField(userData, fieldName, cellValue);
            }

            data.add(userData);
        }

        return data;
    }

    private void setUserDataField(UserData userData, String fieldName, String fieldValue) {
        try {
            Field field = UserData.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            // Determine the field type and set the value accordingly
            if (field.getType().equals(String.class)) {
                field.set(userData, fieldValue);
            } else if (field.getType().equals(int.class)) {
                field.set(userData, Integer.parseInt(fieldValue));
            } // Add more type conversions as needed
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public List<String> getColumnHeaders() {
        return columnHeaders;
    }

    public List<List<String>> getTestData() {
        List<List<String>> testDataList = new ArrayList<>();

        for (UserData userData : testData) {
            List<String> userDataValues = new ArrayList<>();
            for (String fieldName : columnHeaders) {
                try {
                    Field field = UserData.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Object fieldValue = field.get(userData);
                    userDataValues.add(fieldValue.toString());
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            testDataList.add(userDataValues);
        }

        return testDataList;
    }
}