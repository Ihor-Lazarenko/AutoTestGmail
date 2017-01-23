import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testingemail {
    private WebDriver driver;
    private String baseUrl;
    private String Login;
    private String Pass;
    private String Topic;

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://mail.google.com/";
        Login="testingemail.lazarenko";
        Pass="vfccfhfri";
        long epoch = System.currentTimeMillis()/1000;
        Topic="Letter" +epoch;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testNew1() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("#Email")).sendKeys(Login);
        driver.findElement(By.cssSelector("#next")).click();
        driver.findElement(By.cssSelector("#Passwd")).sendKeys(Pass);
        driver.findElement(By.cssSelector("#signIn")).click();

        driver.get(baseUrl+"mail/#inbox?compose=new");
        driver.findElement(By.cssSelector(".vO")).sendKeys("ig.laz2013@gmail.com");
        driver.findElement(By.cssSelector(".aoT")).sendKeys(Topic);
        driver.findElement(By.cssSelector(".Am")).sendKeys("Hello!");
        driver.findElement(By.xpath(".//div[text() = 'Отправить']")).click();

        driver.get(baseUrl+"mail/#sent");
        int sizeSent=driver.findElements(By.xpath(".//span[contains(text(),'"+Topic+"')]")).size();
        assertTrue("Sorry, your letter wasn`t sent",sizeSent>0);

        driver.get("http://s.pikabu.ru/post_img/2013/02/05/6/1360052532_1736136065.jpg");
    }

    @After
    public void tearDown() throws Exception {
        //driver.quit();
    }
}




