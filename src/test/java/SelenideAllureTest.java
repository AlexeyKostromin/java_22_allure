import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideAllureTest {


    @Test
    void openSelenideRepoTest() {
        open("https://github.com");

        $(".header-search-button").click();
        $("#query-builder-test").sendKeys("eroshenkoam/allure-example");
        $("#query-builder-test").submit();

        $(By.linkText("eroshenkoam/allure-example")).click();
        $("#issues-tab").click();
        $(Selectors.withText("#80")).should(Condition.visible);
    }
}
