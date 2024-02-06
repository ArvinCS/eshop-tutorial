package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class EditProductFunctionalTest {
    /*
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /*
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest(){
        baseUrl=String.format("%s:%d",testBaseUrl, serverPort);
    }

    @AfterEach
    void cleanup(ChromeDriver driver) {
        driver.get(baseUrl + "/product/list");

        List<WebElement> deleteButtons = driver.findElements(By.id("deleteButton"));
        for (WebElement deleteButton : deleteButtons) {
            deleteButton.click();
        }

        driver.quit();
    }

    @Test
    void editProduct_canEdit(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        assertEquals("Create New Product", driver.getTitle());

        driver.findElement(By.id("nameInput")).sendKeys("Arabica Beans");
        driver.findElement(By.id("quantityInput")).sendKeys("60");
        driver.findElement(By.tagName("form")).submit();

        assertEquals("Product List", driver.getTitle());

        WebElement selectedRow = driver.findElement(By.xpath("//table//tr[./td[text() = 'Arabica Beans']]"));
        assertNotNull(selectedRow);

        selectedRow.findElement(By.id("editButton")).click();
        assertEquals("Edit Product", driver.getTitle());

        driver.findElement(By.id("nameInput")).clear();
        driver.findElement(By.id("nameInput")).sendKeys("Robusta Beans");
        driver.findElement(By.tagName("form")).submit();

        assertEquals("Product List", driver.getTitle());

        selectedRow = driver.findElement(By.xpath("//table//tr[./td[text() = 'Robusta Beans']]"));
        assertNotNull(selectedRow);
    }

    @Test
    void editProduct_canNotEditNonExisting(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        assertEquals("Create New Product", driver.getTitle());

        driver.findElement(By.id("nameInput")).sendKeys("Arabica Beans");
        driver.findElement(By.id("quantityInput")).sendKeys("60");
        driver.findElement(By.tagName("form")).submit();

        assertEquals("Product List", driver.getTitle());

        driver.get(baseUrl + "/product/60/edit");

        assertEquals("Product List", driver.getTitle());
    }
}
