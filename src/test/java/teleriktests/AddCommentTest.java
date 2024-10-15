package teleriktests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddCommentTest extends BaseTelerikTest {

    private static final By commentTextAreaLocator = By.cssSelector(".d-editor-input");
    private static final By replyButtonOpenLocator = By.xpath("//span[contains(@class, 'd-button-label') and text()='Reply']");
    private static final By replyButtonSendLocator = By.cssSelector("div.save-or-cancel button.btn.btn-icon-text.btn-primary.create");

    @Test
    public void addCommentWithEmojiNames() throws InterruptedException {
        navigateToTopic();
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(replyButtonOpenLocator)).click();

        WebElement commentTextArea = driverWait().until(ExpectedConditions.elementToBeClickable(commentTextAreaLocator));
        String commentText = "Hi, I am Armine, and I did it 😄 🎉 🍻 🙈 🙉 🙊 ❤️";

        JavascriptExecutor js = (JavascriptExecutor) driver().getWebDriver();
        js.executeScript("arguments[0].value = arguments[1];", commentTextArea, commentText);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", commentTextArea);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", commentTextArea);

        driverWait().until(ExpectedConditions.visibilityOfElementLocated(replyButtonSendLocator)).click();
        Thread.sleep(10000);


    }
}
