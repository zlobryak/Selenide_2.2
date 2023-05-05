package ry.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ChromeTest {
    @Test
    void shouldBookCardDeliveryHappyPath() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        SelenideElement block = $("fieldset");
        block.$("[data-test-id=city] input").sendKeys("Челябинск");

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String date = dateFormat.format(
                new Date(String.valueOf(LocalDate.now().plusDays(3)))
    );
        block.$(".calendar-input input").setValue(date).sendKeys(Keys.ESCAPE);
        block.$("[data-test-id=name] input").setValue("Василий Пупкин");
        block.$("[data-test-id=phone] input").setValue("+79062421277");

        block.$(withText("соглашаюсь")).click();
        block.$(withText("Забронировать")).click();

        $(".notification__content")
                .shouldHave(Condition.text(
                                "Встреча успешно забронирована на " + date),
                        Duration.ofSeconds(15)
                )
                .shouldBe(Condition.visible);
    }
}
