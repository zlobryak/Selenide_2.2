package ry.netology.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
//import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
public class ChromeTest {
    @Test
    void shouldBookCardDeliveryHappyPath() {

//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        SelenideElement block = $("fieldset");
        block.$("[data-test-id=city] input").sendKeys("Челябинск");
        block.$(".calendar-input input")
                .setValue(
                        String.valueOf(LocalDate.now()
                                .plusDays(3)
                        )
                ).sendKeys(Keys.ESCAPE);
        block.$("[data-test-id=name] input").setValue("Василий Пупкин");
        block.$("[data-test-id=phone] input").setValue("+79062421277");
//        $x("/html/body/div[1]/div/form/fieldset/div[5]/label/span[2]").click();
        block.$(withText("соглашаюсь")).click();
        block.$(withText("Забронировать")).click();

        $(withText("Встреча успешно забронирована")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
