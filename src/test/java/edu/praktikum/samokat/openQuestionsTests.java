package edu.praktikum.samokat;

import edu.praktikum.samokat.example.HomePageSamokat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
import static edu.praktikum.samokat.example.URL.URL;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class openQuestionsTests {
    private WebDriver driver;
    private final int index;
    private final String text;

    public void orderScooterTestDriver(WebDriver driver){
        this.driver = driver;
    }
    public openQuestionsTests(int index, String text){
        this.index = index;
        this.text = text;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestions(){
        return new Object[][]{
                {1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто " +
                        "сделать несколько заказов — один за другим."},
                {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт" +
                        " времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат " +
                        "8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете " +
                        "кататься без передышек и во сне. Зарядка не понадобится."},
                {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setUp(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void checkQuestionsTest(){
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        objHomePage.scrollToQuestions(index);
        objHomePage.clickOnAccordion(index);
        objHomePage.getTextToAccordion(index);

        Assert.assertEquals("Текст из выпадащего списка должен совпадать с элементом", objHomePage.getTextToAccordion(index-1), containsString(text));
    }
    @After
    public void teardown() {
        driver.quit();
    }

}