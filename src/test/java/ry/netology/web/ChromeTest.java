package ry.netology.web;

import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;

public class ChromeTest {
    @Test
    void shouldBookCardDelivery() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");

    }
}
