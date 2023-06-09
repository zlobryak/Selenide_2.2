package ry.netology.web;

import com.codeborne.selenide.Condition;
//import com.codeborne.selenide.Configuration;
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
    public String generateDateToSet(long addDays, String pattern) {
        return LocalDate.now()
                .plusDays(addDays)
                .format(DateTimeFormatter
                        .ofPattern(pattern));
    }

    @Test
    void shouldBookCardDeliveryHappyPath() {
//        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        SelenideElement block = $("fieldset");
        block.$("[data-test-id=city] input").sendKeys("Челябинск");


        String dateToSet = generateDateToSet(3, "dd.MM.yyyy");

        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),
                        Keys.BACK_SPACE);

        block.$(".calendar-input input").setValue(dateToSet);
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

    @Test
    void shouldBookCardDeliveryWrongDate() {
        open("http://localhost:9999/");
        SelenideElement block = $("fieldset");
        block.$("[data-test-id=city] input").sendKeys("Челябинск");


        String dateToSet = generateDateToSet(1, "dd.MM.yyyy");

        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),
                        Keys.BACK_SPACE);

        block.$(".calendar-input input").setValue(dateToSet);
        block.$("[data-test-id=name] input").setValue("Василий Пупкин");
        block.$("[data-test-id=phone] input").setValue("+79062421277");

        block.$(withText("соглашаюсь")).click();
        block.$(withText("Забронировать")).click();

        $("[data-test-id='date']")
                .shouldHave(Condition.text(
                                "Заказ на выбранную дату невозможен")
                )
                .shouldBe(Condition.visible);
    }
    @Test
    void shouldBookCardDeliveryWrongPhone() {
        open("http://localhost:9999/");
        SelenideElement block = $("fieldset");
        block.$("[data-test-id=city] input").sendKeys("Челябинск");


        String dateToSet = generateDateToSet(3, "dd.MM.yyyy");

        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),
                        Keys.BACK_SPACE);

        block.$(".calendar-input input").setValue(dateToSet);
        block.$("[data-test-id=name] input").setValue("Василий Пупкин");
        block.$("[data-test-id=phone] input").setValue("543");

        block.$(withText("соглашаюсь")).click();
        block.$(withText("Забронировать")).click();

        $("[data-test-id='phone'] ,input-sub")
                .shouldHave(Condition.text(
                                "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")
                )
                .shouldBe(Condition.visible);
    }
    @Test
    void shouldBookCardDeliveryWrongName() {
        open("http://localhost:9999/");
        SelenideElement block = $("fieldset");
        block.$("[data-test-id=city] input").sendKeys("Челябинск");


        String dateToSet = generateDateToSet(3, "dd.MM.yyyy");

        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),
                        Keys.BACK_SPACE);

        block.$(".calendar-input input").setValue(dateToSet);
        block.$("[data-test-id=name] input").setValue("1");
        block.$("[data-test-id=phone] input").setValue("+79012345678");

        block.$(withText("соглашаюсь")).click();
        block.$(withText("Забронировать")).click();

        $("[data-test-id='name'] ,input-sub")
                .shouldHave(Condition.text(
                        "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")
                )
                .shouldBe(Condition.visible);
    }@Test
    void shouldBookCardDeliveryWrongCity() {
        open("http://localhost:9999/");
        SelenideElement block = $("fieldset");
        block.$("[data-test-id=city] input").sendKeys("Мга");


        String dateToSet = generateDateToSet(3, "dd.MM.yyyy");

        $("[data-test-id='date'] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),
                        Keys.BACK_SPACE);

        block.$(".calendar-input input").setValue(dateToSet);
        block.$("[data-test-id=name] input").setValue("Илья");
        block.$("[data-test-id=phone] input").setValue("+79012345678");

        block.$(withText("соглашаюсь")).click();
        block.$(withText("Забронировать")).click();

        $("[data-test-id='city'] ,input-sub")
                .shouldHave(Condition.text(
                        "Доставка в выбранный город недоступна")
                )
                .shouldBe(Condition.visible);
    }

}
