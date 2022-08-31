package com.example.lecture_spring_2_crudproject.service.textTransfer;

import com.example.lecture_spring_2_crudproject.entity.data.SeleniumDtoExample;
import com.example.lecture_spring_2_crudproject.repository.data.SeleniumDtoExampleRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

//https://chromedriver.chromium.org/downloads

@Service
public class Selenium {

    @Autowired
    SeleniumDtoExampleRepository seleniumDtoExampleRepository;

    private WebDriver driver;
    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
//    public static final String WEB_DRIVER_PATH = "/Users/js/Cleancode/lecture_spring_2_crudProject/src/main/resources/static/tool/chromedriver";
    public static final String WEB_DRIVER_PATH = "C:/Cleancode/lecture_spring_2_crudProject/src/main/resources/static/tool/chromedriver_win.exe";

    private String base_url;

    //Jsoup
    // http request사용하여 정적 데이터 수집
    //selenium
    //jsoup에 속도는 느리나 드라이버를 사용해 동적데이터 수집 가능

    public void scraping() {

        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

//        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
//        base_url = "https://www.fragrantica.com/perfume/Maison-Francis-Kurkdjian/Baccarat-Rouge-540-33519.html";
        base_url = "https://giraf.sktelecom.com/web/kostat/";
        driver.get(base_url);

        try{
            Thread.sleep(5000);
            System.out.println(driver.getPageSource());

            WebElement element = driver.findElement(By.tagName("div"));


            List<WebElement> elements = element.findElements(By.tagName("p"));
            int checkNum = 0;
            for (WebElement e : elements) {
                System.out.println(checkNum);
                System.out.println(e.getText());
                checkNum++;

                SeleniumDtoExample seleniumDtoExample = new SeleniumDtoExample();
                seleniumDtoExample.setData_name("pTagData");
                seleniumDtoExample.setData_content(e.getText());
                insertSeleniumDtoExample(seleniumDtoExample);
            }

            WebElement element_click = driver.findElement(By.tagName("a"));
            List<WebElement> elements_click = element_click.findElements(By.tagName("i"));
            System.out.println("----------check------------"+element_click.getSize());
            for (WebElement e : elements_click) {
                System.out.println("----------check in------------");
                System.out.println(e.getText());
            }

//            WebElement textBox = driver.findElement(By.name("my-text"));
//            WebElement submitButton = driver.findElement(By.cssSelector("button"));
//            driver.get("https://www.fragrantica.com/perfume/Maison-Francis-Kurkdjian/Baccarat-Rouge-540-33519.html");
//            WebElement element = driver.findElement(By.tagName("div"));
//
//            List<WebElement> elements = element.findElements(By.tagName("p"));
//            for (WebElement e : elements) {
//                System.out.println(e.getText());
//            }
        }catch(Exception e) {
            e.printStackTrace();

        }finally {
            driver.quit();
        }
    }

    public void insertSeleniumDtoExample(SeleniumDtoExample seleniumDtoExample) {
        seleniumDtoExampleRepository.save(seleniumDtoExample);
    }
}
