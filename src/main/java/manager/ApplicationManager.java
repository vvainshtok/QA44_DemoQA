package manager;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.List;

public class ApplicationManager {

    private WebDriver driver;
    private ChromeOptions chromeOptions = new ChromeOptions();

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp() {
        chromeOptions.addArguments("load-extension=C://Tools/5.10.1_0");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.navigate().to("https://demoqa.com/");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String s :tabs)
            System.out.println("tab-->"+s);

     //   driver.switchTo().window(tabs.get(1)).close();
        driver.switchTo().window(tabs.get(0));

    }

    public void tearDown() {
    //    if (driver != null)
    //        driver.quit();
    }
}
