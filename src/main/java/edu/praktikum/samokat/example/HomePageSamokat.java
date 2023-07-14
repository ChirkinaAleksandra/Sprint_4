package edu.praktikum.samokat.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePageSamokat {
    private WebDriver driver;
    private static final By orderUpButton = By.className("Button_Button__ra12g"); //локатор для верхней кнопки "Заказать"
    private static final By orderDownButton = By.className("Button_Button__ra12g Button_Middle__1CSJM"); //локатор для нижней кнопки "Заказать"
    private final By accordion = By.xpath(".//div[@data-accordion-component = 'Accordion']"); //локатор для аккордиона с вопросами
    private final By buttonOnAccordion = By.className("accordion__button"); //локатор для кнопки клика по аккордиону
    private final By textByAccordion = By.cssSelector("accordion__panel p"); //локатор с текстом аккордиона

    public HomePageSamokat(WebDriver driver){
        this.driver = driver;
    }

    //метод скролла до аккордиона
    public void scrollToQuestions(int index){
        WebElement element = driver.findElement(accordion);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(); ", driver.findElements(accordion).get(index));
    }
    //метод клика по аккордеону
    public void clickOnAccordion(int index){
        driver.findElements(buttonOnAccordion).get(index).click();
    }
    //метод получения текста аккордеона
    public String getTextToAccordion(int index){
        return driver.findElements(textByAccordion).get(index).getText();
    }

    public static By getOrderUpButton(){
        return orderUpButton;
    }

    public static By getOrderDownButton(){
        return orderDownButton;
    }

    //метод для общего клика по кнопке
    public void clickButtonOrder(By button){
        WebElement buttonElement = driver.findElement(button);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", buttonElement);
        buttonElement.click();
    }

}