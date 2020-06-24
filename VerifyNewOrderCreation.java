import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utility.Utility;


public class VerifyNewOrderCreation {

	public static void main(String[] args) throws InterruptedException {


		System.setProperty("webdriver.chrome.driver",
				"/Users/anarganbarov/Documents/SeleniumFiles/BrowserDriver/chromedriver3");
		//1. Open the chrome browser
		WebDriver driver = new ChromeDriver();

		//2. Go to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
		Thread.sleep(2000);
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

		//3. Login using username Tester and password test

		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(Keys.chord("Tester"));
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(Keys.chord("test"));
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();

		//4.Click on Order link
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[@href='Process.aspx']")).click();

		//5. Enter a random quantity between 1 and 100
		Thread.sleep(2000);

		int random =(int) ((int)1+( Math.random()*100));
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.DELETE);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.chord(""+random));

		//6. Enter Customer Name:
		Thread.sleep(2000);

		String alphabet= "abcdefghijklmnopqrstuvwxyz";
		String s = "";
		Random rand = new Random();
		for (int i = 0; i <5; i++) {
			char c = alphabet.charAt(rand.nextInt(26));
			s+=c;
		}
		s = s.substring(0, 1).toUpperCase() + s.substring(1);

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(Keys.chord("John "+ s+ " Doe"));

		//7. Enter street: 8607 Westwood Center Dr
		Thread.sleep(2000);

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(Keys.chord("8607 Westwood Center Dr"));

		//8. Enter City: Vienna
		Thread.sleep(2000);

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(Keys.chord("Vienna"));

		//9. Enter State: Virginia
		Thread.sleep(2000);

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(Keys.chord("Virginia"));

		//10. Enter a random 5 digit number to the zip code field
		Thread.sleep(2000);

		String numbers = "0123456789";
		String zipCode = "";
		for (int i = 0; i <5; i++) {
			char zip = numbers.charAt(rand.nextInt(10));
			zipCode+=zip;
		}
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(Keys.chord(zipCode));

		//11. Select any card type. Every time your code should select a different type.
		Thread.sleep(2000);

		WebElement visa = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0"));
		WebElement masterCard = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1"));
		WebElement americanExpress = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2"));

		String randomize = "abc";
		char c = randomize.charAt(rand.nextInt(3));
		if (c == 'a') 
			visa.click();	
		else if(c == 'b')
			masterCard.click();
		else if(c == 'c')
			americanExpress.click();





		//12. Enter any card number: 
		Thread.sleep(2000);

		//If you selected Visa, card number should start with 4. If you selected Master, card number should start with 5. If you selected American Express, card number should start with 3. New card number should be auto generated every time you run the test. Card numbers should be 16 digits for Visa and Master, 15 for American Express.
		if(visa.isSelected()) {
			long visaNumber = 4000000000000000L + (long) (Math.random() * 1000000000000000L);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(Keys.chord(""+visaNumber));
		}
		else if(masterCard.isSelected()) {
			long mastercardNumber = 5000000000000000L + (long) (Math.random() * 1000000000000000L);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(Keys.chord(""+mastercardNumber));
		}
		else if(americanExpress.isSelected()) {
			long americanExpressNumber = 300000000000000L + (long) (Math.random() * 100000000000000L);
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(Keys.chord(""+americanExpressNumber));

		}


		//13. Enter a valid expiration date 
		Thread.sleep(2000);

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(Keys.chord("11/25"));

		//14. Click on Process
		Thread.sleep(2000);

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

		//15. Verify that the page contains text “New order has been successfully added”.
		Thread.sleep(2000);

		String expected = driver.findElement(By.xpath("//strong")).getText();
		String actual = "New order has been successfully added.";
		Utility.assertEquals(expected, actual);

		driver.quit();






	}
}

