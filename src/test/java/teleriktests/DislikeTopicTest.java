package teleriktests;

import org.junit.jupiter.api.Test;

public class DislikeTopicTest extends BaseTelerikTest {

    @Test
    public void dislikeTopicTest() throws InterruptedException {
        navigateToTopic();
        scrollUpInSteps();
        dislikePost();
    }
}
