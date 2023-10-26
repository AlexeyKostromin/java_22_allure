import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class StepsAllureTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    void stepsRepoTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        step("Open github main page", () -> {
            open("https://github.com");
        });
        step("Search for repository " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });
        step("Open repository " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Click on Issues tab", () -> {
            $("#issues-tab").click();
        });
        step("Verify Issure with number displayed" + ISSUE, () -> {
            $(Selectors.withText("#" + ISSUE)).should(Condition.visible);
        });

    }

    @Test
    void webStepsRepoTest() {
        var steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWIthNumber(ISSUE);
    }
}
