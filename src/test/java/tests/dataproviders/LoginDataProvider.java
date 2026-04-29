package tests.dataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utils.ExcelReader;

public class LoginDataProvider {
	
	@DataProvider(name="correctLoginData")
    public Object[][] correctLoginDataProvider() throws IOException {
        return ExcelReader.readloginCredentialsExcel("Correct Login Data");
    }

    @DataProvider(name="incorrectLoginData")
    public Object[][] incorrectLoginDataProvider() throws IOException {
        return ExcelReader.readloginCredentialsExcel("Incorrect Login Data");
    }

}
