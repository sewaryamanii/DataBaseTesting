package databasetesting.databasetesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
	Connection con;
	Statement stmt;
	ResultSet rs;
	WebDriver driver = new ChromeDriver();
	String customerfirstName ;
	String customerlastName ;
	String password ;
	
@BeforeTest
public void maysetup() throws SQLException {
	
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","1234");
	driver.get("https://smartbuy-me.com/ar/account/register");
}
	@Test (priority = 1, enabled = false)
	public void insetrintodatabase() throws SQLException {
		String query ="INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00)";
		stmt =con.createStatement();
		int roweffected = stmt.executeUpdate(query);
		System.out.println(roweffected);
		
}
	@Test(priority = 2)
	public void updatedatabase () throws SQLException {
		
			String query ="UPDATE customers SET creditLimit = 75000 WHERE customerNumber = 999;";
			stmt =con.createStatement();
			int roweffected = stmt.executeUpdate(query);
			System.out.println(roweffected);	
	}
	
	@Test(priority = 3)
	public void readdata() throws SQLException {
		
	
		String query ="SELECT * FROM customers WHERE customerNumber = 103;";
		stmt =con.createStatement();
		rs=stmt.executeQuery(query);
		while (rs.next()) {
			 customerfirstName = rs.getString("contactFirstName");
			System.out.println(customerfirstName);
		}
		rs=stmt.executeQuery(query);
		while (rs.next()) {
			customerlastName = rs.getString("contactLastName");
			System.out.println(customerlastName);}
		rs=stmt.executeQuery(query);
		while (rs.next()) {
			password = rs.getString("addressLine1");
			System.out.println(password);}
		
	driver.findElement(By.id("customer[first_name]")).sendKeys(customerfirstName);
	driver.findElement(By.id("customer[last_name]")).sendKeys(customerlastName);
	driver.findElement(By.id("customer[email]")).sendKeys(customerfirstName+customerlastName+"@gmail.com" );
	driver.findElement(By.id("customer[password]")).sendKeys(password);
	
	}
	
	
 }
