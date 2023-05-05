package ry.netology.web;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;


import java.time.Duration;
import java.time.LocalDate;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ChromeTest {
    @Test
    void shouldBookCardDeliveryHappyPath() {

        Configuration.holdBrowserOpen = true;
        int today = LocalDate.now().getDayOfMonth();
//        int today =31;
        int selectDay = LocalDate.now().plusWeeks(1).getDayOfMonth();
//        int selectDay = 6;

        open("http://localhost:9999/");

        $("[data-test-id=city] input").sendKeys("Че");
        $(byText("Челябинск")).click();
        $(".calendar-input input").click();


        if (selectDay > today){
            $(byText(String.valueOf(selectDay))).click();
        } else {
            $(".calendar__arrow_direction_right").click();
            $(byText(String.valueOf(selectDay))).click();
        }
        $("[data-test-id=name] input").setValue("Василий Пупкин");
        $("[data-test-id=phone] input").setValue("+79062421277");

        $(withText("соглашаюсь")).click();
        $(withText("Забронировать")).click();

        $(withText("Встреча успешно забронирована")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
