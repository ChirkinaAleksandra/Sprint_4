package edu.praktikum.samokat;

import edu.praktikum.samokat.example.HomePageSamokat;
import edu.praktikum.samokat.example.OrderPageSamokat;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import static edu.praktikum.samokat.example.URL.URL;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class orderScooterTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String adress;
    private final String station;
    private final String  telefon;
    private final By button;
    private final String comment;

    public void orderScooterTestDriver(WebDriver driver){
        this.driver = driver;
    }
    public orderScooterTest(String name, String surname, String adress, String station, String telefon, By button, String comment){
        this.name = name;
        this.surname = surname;
        this.adress = adress;
        this.station = station;
        this.telefon = telefon;
        this.button = button;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][]{
                {"Александр", "Иванов", "Алексеевская, 4а", "Сокольники", "89996565656", HomePageSamokat.getOrderUpButton(), "мой комментарий"},
                {"Евгения", "Иванова", "Тверская, 10-2", "Лубянка", "89996666544", HomePageSamokat.getOrderDownButton(), "для курьера"},
        };
    }

    @Before
    public void setUp(){
        WebDriver driver = new ChromeDriver();
        driver.get(URL);
    }

    @Test
    public void checkOrder(){
        HomePageSamokat homePage = new HomePageSamokat(driver);
        homePage.clickButtonOrder(button);

        OrderPageSamokat objOrderPage = new OrderPageSamokat(driver);
        objOrderPage.waitForLoadOrderPage();
        objOrderPage.pageOne(name, surname, adress, station, telefon);
        objOrderPage.clickOnNextButton();

        objOrderPage.pageTwo(comment);
        objOrderPage.clickOnButtonOrder();
        objOrderPage.clickOnButtonYes();
        objOrderPage.waitForLoadSuccessText();

        assertTrue("Сообщение об успешном заказе отсутствует", objOrderPage.isDisplayedSuccessText());
    }

}

    @After
    public void tearDown(){
        driver.quit();
    }

}