package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    public String myDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
        Configuration.headless = true;
    }
    @Test
    public void shouldSendRequest() {

        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id='date'] input").setValue(myDate);
        $("[data-test-id='name'] input").val("Иван Петров");
        $("[data-test-id='phone'] input").val("+79000000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']").shouldHave(text("Встреча успешно забронирована на " + myDate), Duration.ofSeconds(15));

    }
}
