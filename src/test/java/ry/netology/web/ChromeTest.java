package ry.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


        String dateToSet = LocalDate.now().plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        block.$(".calendar-input input").setValue(dateToSet).sendKeys(Keys.ESCAPE);
        block.$("[data-test-id=name] input").setValue("Василий Пупкин");
        block.$("[data-test-id=phone] input").setValue("+79062421277");

        block.$(withText("соглашаюсь")).click();
        block.$(withText("Забронировать")).click();

        $(".notification__content")
                .shouldHave(Condition.text(
                                "Встреча успешно забронирована на " + dateToSet),
                        Duration.ofSeconds(15)
                )
                .shouldBe(Condition.visible);
    }

}
