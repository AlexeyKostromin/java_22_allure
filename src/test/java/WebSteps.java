import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {
    @Step("Open github main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Search for repository {repoName}")
    public void searchForRepository(String repoName) {
        $(".header-search-button").click();
        $("#query-builder-test").sendKeys(repoName);
        $("#query-builder-test").submit();
    }

    @Step("Open repository {repoName}")
    public void clickOnRepositoryLink(String repoName) {
        $(By.linkText(repoName)).click();
    }

    @Step("Click on Issues tab")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Verify Issure with number displayed {repoNumber}")
    public void shouldSeeIssueWIthNumber(int repoNumber) {
        $(Selectors.withText("#" + repoNumber)).should(Condition.visible);
    }

    @Attachment(value = "Screenshot", type = "iamge/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
