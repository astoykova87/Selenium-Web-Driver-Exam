package teleriktests;

import org.junit.jupiter.api.Test;

public class LikeTopicTest extends BaseTelerikTest {

    @Test
    public void likeTopicTest() throws InterruptedException {
        navigateToTopic();
        scrollUpInSteps();
        likePost();
    }
}