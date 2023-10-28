import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;

public class StepsAllureTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;

    @Test
    @Feature("Issue in repository")
    @Story("Create Issue")
    @Owner("Alexey K")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Testing", url = "https://testing.github.com")
    @DisplayName("Can get issue with unauthorized user @step")
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
        step("Verify Issure with number displayed " + ISSUE, () -> {
            $(Selectors.withText("#" + ISSUE)).should(Condition.visible);
        });

    }

    @Test
    void webStepsRepoTest() {
        Allure.getLifecycle().updateTestCase(x -> x.setName("Can get issue with unauthorized user @webStep"));
        Allure.feature("Issue in repository web");
        Allure.story("Create Issue web");
        Allure.label("owner", "Alexey K");
        Allure.label("severity", SeverityLevel.NORMAL.value());
        Allure.link("testing", "https://testing.github.com");

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        var steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWIthNumber(ISSUE);
    }

    @Test
    @Disabled
    void lambdaAttachmentTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        step("Open github main page", () -> {
            open("https://github.com");
            attachment("Source", webdriver().driver().source());
        });
    }

    @Test
    @Disabled
    void annotatedttachmentTest() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        var steps = new WebSteps();

        steps.openMainPage();
        steps.takeScreenshot();
    }
}
