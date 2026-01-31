package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.EmployeePage;

public class EmployeeTest extends BaseTest {

    @Test
    public void successAddEmployee() {
        EmployeePage employeePage =
                new EmployeePage(driver);
        String name = "Irfan" + System.currentTimeMillis();
        String empId = "EMP" + System.currentTimeMillis();
        String email = name + "@test.com";
        System.out.println("=== NEW EMPLOYEE ===");
        System.out.println("Name : " + name);
        System.out.println("ID   : " + empId);
        System.out.println("Mail : " + email);
        System.out.println("===================");
        employeePage.addNewEmployee(
                name,
                empId,
                email,
                "8123456789",
                "Mentor"
        );
        Assert.assertTrue(
                employeePage.isSuccessToastDisplayed()
        );
        employeePage.searchEmployee(name);
        employeePage.verifyEmployeeExist(name);
    }
}
