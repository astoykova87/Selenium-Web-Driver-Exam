package teleriktests;

import com.telerik.pages.LoginPage;
import com.telerik.pages.LoginHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testframework.core.BaseWebTest;

import java.time.Duration;

public abstract class BaseTelerikTest extends BaseWebTest {

    protected LoginPage loginPage;
    protected LoginHelper loginHelper;
    protected static final String TOPIC_URL = "https://stage-forum.telerikacademy.com/t/alpha-61-qa-we-are-awesome-and-great/11547/2";
    protected static final By topicLinkLocator = By.cssSelector("a.raw-link.raw-topic-link[data-topic-id='11547']");
    protected static final By likeButtonLocator = By.xpath("//button[@data-post-id='34574']");

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        loginHelper = new LoginHelper(loginPage);
        loginHelper.loginWithValidCredentials("armine.stoykova.a61@learn.telerikacademy.com", "Katrin2018Dayana@");
    }

    protected WebElement waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver(), Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void navigateToTopic() throws InterruptedException {
        driver().navigate().to(TOPIC_URL);
        Thread.sleep(3000);
    }

    protected void scrollUpInSteps() throws InterruptedException {
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver().getWebDriver();
        long currentScrollPosition = (long) js.executeScript("return window.scrollY;");

        while (currentScrollPosition > 0) {
            js.executeScript("window.scrollBy(0, -500);");
            Thread.sleep(2000);
            currentScrollPosition = (long) js.executeScript("return window.scrollY;");
        }
    }

    protected void likePost() throws InterruptedException {
        WebElement likeButton = driver().findElement(likeButtonLocator);
        String titleValue = likeButton.getAttribute("title");

        if (!"you've liked this post".equals(titleValue)) {
            likeButton.click();
            Thread.sleep(1000);
            Assertions.assertEquals("you've liked this post", likeButton.getAttribute("title"), "The thread was not liked successfully.");
            System.out.println("The thread was liked successfully.");
        } else {
            System.out.println("The thread is already liked.");
        }
    }

    protected void dislikePost() throws InterruptedException {
        WebElement likeButton = driver().findElement(likeButtonLocator);
        String titleValue = likeButton.getAttribute("title");

        if ("you've liked this post".equals(titleValue)) {
            likeButton.click();
            Thread.sleep(1000);
            Assertions.assertEquals("like this post", likeButton.getAttribute("title"), "The thread was not disliked successfully.");
            System.out.println("The thread was disliked successfully.");
        } else {
            System.out.println("The thread is already disliked.");
        }
    }
}