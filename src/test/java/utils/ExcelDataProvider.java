package utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getExcelData() {
        String filePath = "src/test/resources/TestData.xlsx"; // Excel file path
        return ExcelUtils.readExcelData(filePath, "LoginData"); // Sheet name
    }
}
