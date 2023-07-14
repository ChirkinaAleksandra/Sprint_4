package edu.praktikum.samokat.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPageSamokat {
    private final WebDriver driver;
    private final By orderPageHeader = By.xpath(".//div[contains(@class, 'Order_Header')]");
    public final By inputName = By.xpath(".//input[@placeholder = '* Имя']"); //локатор для поля Имя
    public final By inputSurname = By.xpath(".//input[@placeholder = '* Фамилия']"); //локатор для поля Фамилия
    public final By inputAdress = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']"); //локатор для поля Адрес
    public final By inputStation = By.xpath(".//input[@placeholder = '* Станция метро']"); //локатор для поля Станция метро
    public final By inputPhoneNumber = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']"); //локатор для поля Телефон
    public final By nextButton = By.xpath(".//div[contains(@class, 'Order_NextButton')]/button[contains(@class, 'Button_Button')]"); //локатор для кнопки Далее
    public final By dateToBringScooter = By.xpath(".//input[@placeholder = '* Когда привезти самокат']"); //локатор для поля Когда привезти самокат
    public final By dropdownCalendar = By.xpath(".//div[contains(@class, 'react-datepicker__day--today')]"); //локатор для выпадающего календаря
    public final By rentalPeriod = By.className("Dropdown-placeholder"); //локатор для поля Срок аренды
    public final By rentalDay = By.xpath(".//div[@class='Dropdown-menu']/div[text()='сутки']"); //локатор для выпадающего списка с днями
    public final By checkboxes = By.xpath(".//div[contains(@class, 'Order_Checkboxes')]//input"); //локатор для выбора цвета
    public final By commentForCourier = By.xpath(".//input[@placeholder = 'Комментарий для курьера']"); //локатор для поля Комментарий
    public final By buttonOrder = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[contains(text(), 'Заказать')]"); //локатор для кнопки Заказать
    public final By buttonYes = By.xpath("//button[contains(text(), 'Да')]"); //локатор для кнопки Да
    public final By successText = By.xpath("//div[(text()= 'Заказ оформлен')]");

    public OrderPageSamokat(WebDriver driver){
        this.driver = driver;
    }

    //геттеры для строчек 9-13; 15-18; 20
    public WebElement getInputName(){
        return driver.findElement(inputName);
    }
    public WebElement getInputSurname(){
        return driver.findElement(inputSurname);
    }
    public WebElement getInputAdress(){
        return driver.findElement(inputAdress);
    }
    public WebElement getInputStation(){
        return driver.findElement(inputStation);
    }
    public WebElement getInputPhoneNumber(){
        return driver.findElement(inputPhoneNumber);
    }
    public WebElement getDateToBringScooter() {
        return driver.findElement(dateToBringScooter);
    }

    public WebElement getDropdownCalendar() {
        return driver.findElement(dropdownCalendar);
    }
    public WebElement getRentalPeriod(){
        return driver.findElement(rentalPeriod);
    }
    public WebElement getRentalDay(){
        return driver.findElement(rentalDay);
    }
    public WebElement getCommentForCourier(){
        return driver.findElement(commentForCourier);
    }

    //методы для клику по кнопкам Далее, Выбор цвета, Заказать, Да
    public void clickOnNextButton(){
        driver.findElement(nextButton).click();
    }
    public void clickCheckboxes() {
        driver.findElements(checkboxes).forEach(checkbox -> checkbox.click());
    }
    public WebElement getTitleOrder() {
        return driver.findElement(orderPageHeader);
    }
    public void clickOnButtonOrder(){
        driver.findElement(buttonOrder).click();
    }
    public void clickOnButtonYes(){
        driver.findElement(buttonYes).click();
    }

    //ШАГ для заполнения формы 1
    public void pageOne(String name, String surname, String adress, String station, String telefon){
        getInputName().sendKeys(name);
        getInputSurname().sendKeys(surname);
        getInputAdress().sendKeys(adress);
        new Actions(driver).moveToElement(getInputStation()).click().sendKeys(station)
                .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        getInputPhoneNumber().sendKeys(telefon);
    }

    //ШАГ для заполнения формы 2
    public void pageTwo(String comment){
        getDateToBringScooter().click();
        getDropdownCalendar().click();
        getRentalPeriod().click();
        getRentalDay().click();
        clickCheckboxes();
        getCommentForCourier().sendKeys(comment);
    }
    public void waitForLoadOrderPage(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPageHeader));
    }
    public void waitForLoadSuccessText(){
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(successText));
    }
    public boolean isDisplayedSuccessText() {
        System.out.println(driver.findElement(successText).getText());
        return driver.findElement(successText).isDisplayed();
    }
}