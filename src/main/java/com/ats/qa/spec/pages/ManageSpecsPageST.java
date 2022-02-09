package com.ats.qa.spec.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ats.qa.util.CommonMethods;
import com.ats.qa.util.ExcelReadWrite;

public class ManageSpecsPageST extends AbstractPageST {
	WebDriverWait wait;
	WebDriverWait waitSpecPerformance = new WebDriverWait(driver, 660);
	Actions action = new Actions(driver);
	StopWatch stopWatchSpecPerformance = new StopWatch();
	ExcelReadWrite reader = new ExcelReadWrite(
			System.getProperty("user.dir") + "/src/main/resources/com/ats/qa/testdata/SeriesAvailableInCategory.xlsx");
	ExcelReadWrite reader1 = new ExcelReadWrite(
			System.getProperty("user.dir") + "/src/main/resources/com/ats/qa/testdata/ListLoadingTimeSpecTool.xlsx");
	ExcelReadWrite reader2 = new ExcelReadWrite(
			System.getProperty("user.dir") + "/src/main/resources/com/ats/qa/testdata/SpecPerformance.xlsx");
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
	String timeStamp = formatter.format(calendar.getTime());
	DashBoardPageST dashBoardPageST = new DashBoardPageST(driver);
	ManageMastersPageST manageMastersPageST = new ManageMastersPageST(driver);

	@FindBy(xpath = "//button[@title=\"New Spec\"]")
	WebElement createSpec;

	@FindBy(xpath = "//button[@title=\"New Project\"]")
	WebElement createProject;

	@FindBy(xpath = "//button[@aria-label='SpeedDial example']")
	WebElement createSpec_Project;

	@FindBy(xpath = "//label[text()='Specification Title *']/ancestor::div[1]/div/input")
	WebElement specTitle;

	@FindBy(xpath = "//span[text()='Finish']/ancestor::button[1]")
	WebElement specFinish;

	@FindBy(xpath = "//span[text()='Specifications']/ancestor::button")
	WebElement specificationList;

	@FindBy(xpath = "//span[text()='New Drawing']/ancestor::button[1]")
	WebElement newDrawingButtonSpecDetailPage;

	@FindBy(xpath = "//li[text()='Category']")
	WebElement categoryAsOption;

	@FindBy(xpath = "//span[text()='ACTION']/ancestor::button")
	WebElement actionSpecList;

	@FindBy(xpath = "//p[text()='Edit']/ancestor::li")
	WebElement editAction;

	@FindBy(xpath = "//p[text()='Delete']/ancestor::li")
	WebElement deleteAction;

	@FindBy(xpath = "//span[text()='More']/ancestor::button")
	WebElement moreSpecDetail;

	@FindBy(xpath = "//div[text()='Delete Specification']/ancestor::li")
	WebElement deleteSpec_SpecDetail;

	@FindBy(xpath = "//div[text()='Edit Specification Info']/ancestor::li")
	WebElement editSpec_SpecDetail;

	@FindBy(xpath = "//span[text()='Cancel']/ancestor::div[1]/button[2]")
	WebElement deleteConfirm;

	@FindBy(xpath = "//div[text()='Manufacturer']/ancestor::div[2]/div[2]/div/div/div/div/div/label")
	WebElement manufactureList;

	@FindBy(xpath = "//span[text()='Create Spec']/ancestor::button")
	WebElement createSpecButtonSpecList;

	@FindBy(xpath = "//p[contains(text(),'Country:')]/ancestor::div[1]/div/div/p")
	WebElement countryTextUnavailability;

	@FindBy(xpath = "(//p[contains(text(),'Country:')]/ancestor::div[1]/div/div/p)[2]")
	WebElement countrySecondTextUnavailability;

	@FindBy(xpath = "//p[text()='Rows per page:']/ancestor::div[1]/p[2]")
	WebElement numberOfSpecifications;

	@FindBy(xpath = "(//label[text()='Sequence']/ancestor::div[1]/div/input)[3]")
	WebElement drawingSequence;

	@FindBy(xpath = "(//p[contains(@title,'2742.068C.020')])[3]")
	WebElement drawingProduct;

	@FindBy(xpath = "//p[text()='Bathtub']")
	WebElement checkCategoryName;

	@FindBy(xpath = "//h6[text()='Options']/ancestor::div[1]/div[2]/div/div[2]//input")
	WebElement seriesFirstOptionSelection;

	@FindBy(xpath = "//th[text()='Spec Title']")
	WebElement titleSpecName;

	@FindBy(xpath = "//p[text()='Rows per page:']/ancestor::div[1]/p[2]")
	WebElement countSpecListPagination;

	@FindBy(xpath = "//input[@name='createdDate.gte']")
	WebElement filterCreatedStartDateSpec;

	@FindBy(xpath = "//input[@name='createdDate.lte']")
	WebElement filterCreatedEndDateSpec;

	@FindBy(xpath = "(//button[@title='Open'])[1]")
	WebElement filterCreatedByUser;

	@FindBy(xpath = "//span[text()='Created By User']/ancestor::div[1]/div[3]//input")
	WebElement filterCreatedByUserOptionEntry;

	@FindBy(xpath = "(//button[@title='Open'])[2]")
	WebElement filterCreatedByOffice;

	@FindBy(xpath = "//span[text()='Created By Office']/ancestor::div[1]/div[4]//input")
	WebElement filterCreatedByOfficeOptionEntry;

	@FindBy(xpath = "//span[text()='Continue']/ancestor::button")
	WebElement continueSpec;

	@FindBy(xpath = "//span[text()='Back']/ancestor::button")
	WebElement backSpec;

	@FindBy(xpath = "//span[text()='Product Brochure']/ancestor::button")
	WebElement productBrochure;

	@FindBy(xpath = "//span[text()='Schedule']/ancestor::button")
	WebElement schedule;

	@FindBy(xpath = "//span[text()='Long Spec']/ancestor::button")
	WebElement longSpec;

	@FindBy(xpath = "//span[text()='Short Spec']/ancestor::button")
	WebElement shortSpec;

	@FindBy(xpath = "//span[text()='Manufacturer Spec Sheet']/ancestor::button")
	WebElement manufactureSpecSheet;

	@FindBy(xpath = "//span[text()='Revit']/ancestor::button")
	WebElement revit;

	@FindBy(xpath = "//span[text()='CSI 3 PART SPEC']/ancestor::button")
	WebElement csi;

	// Initializing the Page Objects:
	public ManageSpecsPageST(WebDriver webDriver) {
		super(webDriver);
		PageFactory.initElements(driver, this);
		wait = webDriverWait();
	}

	public void seriesInCategories(String strSpecName) {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		CommonMethods.staticTimeWait(1000);
		ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		ManageSpecsPageST.searchSpecFieldSpecListPageST().sendKeys("Specification" + strSpecName);
		CommonMethods.staticTimeWait(2000);
		driver.findElement(By.xpath("//a[@title=" + "'Specification" + strSpecName + "'" + "]")).click();
		ManageSpecsPageST.buttonAddDrawingButtonSpecDetailPageST().click();
		CommonMethods.staticTimeWait(2000);
		String sheetName = "SpecTool";
		reader.addColumn(sheetName, timeStamp);
		reader.addColumn(sheetName, "Manufactures In Category");
		reader.addColumn(sheetName, "Manufactures Match Result");
		String result;
		int rowCount = 170; // Make a change in row count depends on excel file for which whitelabel is in
							// use
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
			String strCategory = reader.getCellData(sheetName, "categoryName", rowNum);
			try {
				driver.findElement(By.xpath("//p[text()='" + strCategory + "']/ancestor::button[1]")).click();
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.visibilityOfElementLocated(
						By.xpath("//p[text()='Applied filters:']/ancestor::div[3]/div[2]/p")));
				result = driver.findElement(By.xpath("//p[text()='Applied filters:']/ancestor::div[3]/div[2]/p"))
						.getText();
				List<WebElement> listOfElements = driver.findElements(
						By.xpath("//div[text()='Manufacturer']/ancestor::div[2]/div[2]/div/div/div/div/div/label"));
				int manufactureTotal = listOfElements.size();
				String manufactureName = "";
				String resultManufacture;
				String finalResultManufacture = "";
				String[] wattsManufactures = { "Watts", "Orion", "Powers", "BLÃœCHER", "Ames Fire & Waterworks",
						"Febco", "Dormont", "Watts ProLine", "Mueller Steam" };
				String[] bobrickManufactures = { "Koala Kare", "Gamco", "Bobrick", "Privada" };
				StringBuilder myString = new StringBuilder();
				StringBuilder myString1 = new StringBuilder();
				for (int manufactureNum = 1; manufactureNum <= manufactureTotal; manufactureNum++) {
					manufactureName = driver.findElement(
							By.xpath("//div[text()='Manufacturer']/ancestor::div[2]/div[2]/div/div/div/div/div/label["
									+ manufactureNum + "]/span[2]"))
							.getText();
					myString.append(manufactureName + "  ");

					boolean matchManufacture = Arrays.stream(bobrickManufactures).anyMatch(manufactureName::contains);
					// Make a change in array depends on which whitelabel is in use

					if (matchManufacture == true) {
						resultManufacture = "pass";
					} else {
						resultManufacture = "fail";
					}
					myString1.append(resultManufacture + "  ");
					finalResultManufacture = myString1.toString();
					String manufactureNames = myString.toString();

					reader.setCellData(sheetName, "Manufactures In Category", rowNum, manufactureNames);
				}
				// System.out.println(finalResultManufacture);
				if (finalResultManufacture.contains("fail")) {
					reader.setCellData(sheetName, "Manufactures Match Result", rowNum, "Fail");
				} else if (finalResultManufacture.contains("pass")) {
					reader.setCellData(sheetName, "Manufactures Match Result", rowNum, "Pass");
				}
			} catch (Exception e) {
				result = "Error- Not Found";
			}
			String url = driver.getCurrentUrl();// Only if error is on product selection page
			if (url.contains("productSelection")) {
				driver.findElement(By.xpath("//p[text()='Category']/ancestor::a")).click();
				CommonMethods.staticTimeWait(2000);
			}

			reader.setCellData(sheetName, timeStamp, rowNum, result);

		}

	}


	public void recordTimeSpecificationList() {
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		String sheetName = "SpecList";
		reader1.addColumn(sheetName, timeStamp);
		reader1.addColumn(sheetName, "Number of Specs");
		ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		String numberOfSpecs = "";
		String pagination = "";
		int rowCountPeter = 6;
		for (int rowNum = 2; rowNum <= rowCountPeter; rowNum++) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			CommonMethods.staticTimeWait(1000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String result = "";
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='Spec Title']")));
				pagination = numberOfSpecifications.getText();
				result = "pass";
			} catch (Exception e) {
				result = "fail";
			}
			stopWatch.stop();
			String strTime = stopWatch.toString();
			if (result.equals("fail")) {
				reader1.setCellData(sheetName, timeStamp, rowNum, "Fail");
				reader1.setCellData(sheetName, "Number of Specs", rowNum, "Unable To Load");
			} else if (result.equals("pass")) {
				String[] parts = pagination.split("of");
				numberOfSpecs = parts[1];
				reader1.setCellData(sheetName, timeStamp, rowNum, strTime);
				reader1.setCellData(sheetName, "Number of Specs", rowNum, numberOfSpecs);
			}
			CommonMethods.staticTimeWait(2000);
			DashBoardPageST.dashBoardTab().click();
			CommonMethods.staticTimeWait(1000);
			ManageProjectsPageST.manageProjectsTabST().click();
			ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		}
		CommonMethods.staticTimeWait(1000);
		DashBoardPageST.dashBoardTab().click();
		dashBoardPageST.loginAS("Jim Huesman");
		ManageProjectsPageST.manageProjectsTabST().click();
		ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		int rowCountJim = 11;
		for (int rowNum = 7; rowNum <= rowCountJim; rowNum++) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			CommonMethods.staticTimeWait(1000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String result = "";
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='Spec Title']")));
				pagination = numberOfSpecifications.getText();
				result = "pass";
			} catch (Exception e) {
				result = "fail";
			}
			stopWatch.stop();
			String strTime = stopWatch.toString();
			if (result.equals("fail")) {
				reader1.setCellData(sheetName, timeStamp, rowNum, "Fail");
				reader1.setCellData(sheetName, "Number of Specs", rowNum, "Unable To Load");
			} else if (result.equals("pass")) {
				String[] parts = pagination.split("of");
				numberOfSpecs = parts[1];
				reader1.setCellData(sheetName, timeStamp, rowNum, strTime);
				reader1.setCellData(sheetName, "Number of Specs", rowNum, numberOfSpecs);
			}
			CommonMethods.staticTimeWait(2000);
			DashBoardPageST.dashBoardTab().click();
			CommonMethods.staticTimeWait(1000);
			ManageProjectsPageST.manageProjectsTabST().click();
			ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		}
		CommonMethods.staticTimeWait(1000);
		DashBoardPageST.dashBoardTab().click();
		DashBoardPageST.profileIcon().click();
		DashBoardPageST.logOut().click();
		CommonMethods.staticTimeWait(1000);
		driver.navigate().refresh();
		dashBoardPageST.loginAS("Debbie Shortreed");
		ManageProjectsPageST.manageProjectsTabST().click();
		ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		int rowCountDebbi = 16;
		for (int rowNum = 12; rowNum <= rowCountDebbi; rowNum++) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			CommonMethods.staticTimeWait(1000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String result = "";
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='Spec Title']")));
				pagination = numberOfSpecifications.getText();
				result = "pass";
			} catch (Exception e) {
				result = "fail";
			}
			stopWatch.stop();
			String strTime = stopWatch.toString();
			if (result.equals("fail")) {
				reader1.setCellData(sheetName, timeStamp, rowNum, "Fail");
				reader1.setCellData(sheetName, "Number of Specs", rowNum, "Unable To Load");
			} else if (result.equals("pass")) {

				String[] parts = pagination.split("of");
				numberOfSpecs = parts[1];
				reader1.setCellData(sheetName, timeStamp, rowNum, strTime);
				reader1.setCellData(sheetName, "Number of Specs", rowNum, numberOfSpecs);
			}
			CommonMethods.staticTimeWait(2000);
			DashBoardPageST.dashBoardTab().click();
			CommonMethods.staticTimeWait(1000);
			ManageProjectsPageST.manageProjectsTabST().click();
			ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		}
		CommonMethods.staticTimeWait(1000);
		DashBoardPageST.dashBoardTab().click();
		DashBoardPageST.profileIcon().click();
		DashBoardPageST.logOut().click();
		CommonMethods.staticTimeWait(2000);
		dashBoardPageST.loginAS("Gene Stevens");
		ManageProjectsPageST.manageProjectsTabST().click();
		ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		int rowCountGene = 21;
		for (int rowNum = 17; rowNum <= rowCountGene; rowNum++) {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			CommonMethods.staticTimeWait(1000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			String result = "";
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//th[text()='Spec Title']")));
				pagination = numberOfSpecifications.getText();
				result = "pass";
			} catch (Exception e) {
				result = "fail";
			}
			stopWatch.stop();
			String strTime = stopWatch.toString();
			if (result.equals("fail")) {
				reader1.setCellData(sheetName, timeStamp, rowNum, "Fail");
				reader1.setCellData(sheetName, "Number of Specs", rowNum, "Unable To Load");
			} else if (result.equals("pass")) {

				String[] parts = pagination.split("of");
				numberOfSpecs = parts[1];
				reader1.setCellData(sheetName, timeStamp, rowNum, strTime);
				reader1.setCellData(sheetName, "Number of Specs", rowNum, numberOfSpecs);
			}
			CommonMethods.staticTimeWait(2000);
			DashBoardPageST.dashBoardTab().click();
			CommonMethods.staticTimeWait(1000);
			ManageProjectsPageST.manageProjectsTabST().click();
			ManageSpecsPageST.manageProjectsSpecificationTabST().click();
		}
	}

	


}