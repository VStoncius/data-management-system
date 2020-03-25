package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GroupListPage extends MainPage {

	public GroupListPage(WebDriver driver) {
		super(driver);
	}

	/* FIELDS */

	@FindBy(xpath = "//input")
	private WebElement searchField;

	/* CLICK BUTTONS */

	public void clickEditSpecificGroupButton(String group) {
		driver.findElement(By.xpath("//td[contains(text(),'" + group + "')]/..//td[3]//button")).click();
	}

	/* SEND KEYS */

	public void sendKeysSearchForUser(String userInformation) {
		searchField.sendKeys(userInformation);
	}

	public void sendKeysSearchForGroup(String groupName) {
		searchField.sendKeys(groupName);
	}

	/* CLEAR FIELDS */

	public void clearSearchField() {
		this.searchField.clear();
	}
}
