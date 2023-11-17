package edu.hw6.task5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class HackerNewsTest {

    @Test
    void HackerNewsStories() {
        HackerNews hackerNews = new HackerNews();
        long[] news = hackerNews.hackerNewsTopStories();

        assertThat(news).isNotEmpty();
        assertThat(news).isNotNull();
    }

    @Test
    void News() {
        HackerNews hackerNews = new HackerNews();
        String response = hackerNews.news(37570037);
        assertThat(response).isNotEmpty();
        assertThat(response).isEqualTo("JDK 21 Release Notes");
    }
}
