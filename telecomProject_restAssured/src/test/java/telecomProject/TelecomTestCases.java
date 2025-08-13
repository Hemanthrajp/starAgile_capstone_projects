package telecomProject;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TelecomTestCases {
	
	String token;
	String NewEmail;
	String contactID;
	
	@Test(priority = 1)
	public void addNewUser() {
		
		Response res = given().header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"firstName\": \"Akul\",\r\n"
				+ "\"lastName\": \"sharma\",\r\n"
				+ "\"email\": \"akul"+ System.currentTimeMillis() +"@fake.com\",\r\n"
				+ "\"password\": \"akul123\"\r\n"
				+ "}")
		.when().post("https://thinking-tester-contact-list.herokuapp.com/users");
		System.out.println("--addNewUser--");
		res.then().log().body();
		
		token = res.jsonPath().getString("token");
		
		Assert.assertEquals(res.statusCode(), 201);
		Assert.assertEquals(res.statusLine(), "HTTP/1.1 201 Created");
	}
	
	@Test(priority = 2, dependsOnMethods = "addNewUser")
	public void getUserProfile() {
		Response res = given().header("Content-Type", "application/json")
		.header("Authorization", "Bearer " + token)
		.when().get("https://thinking-tester-contact-list.herokuapp.com/users/me");
		System.out.println("--getUserProfile--");
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(priority = 3)
	public void updateUser() {
		Response res = given().header("Content-Type", "application/json")
							  .header("Authorization", "Bearer " + token)
							  .body("{\r\n"
							  		+ "\"firstN ame\": \"Akula\",\r\n"
							  		+ "\"lastName\": \"kumari\",\r\n"
							  		+ "\"email\": \"akula"+ System.currentTimeMillis() +"@fake.com\",\r\n"
							  		+ "\"password\": \"akula123\"\r\n"
							  		+ "}")
					   .when().patch("https://thinking-tester-contact-list.herokuapp.com/users/me");
		System.out.println("--updateUser--");
				res.then().log().body();
				NewEmail = res.jsonPath().getString("email");
				
				Assert.assertEquals(res.getStatusCode(), 200);
				Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(priority = 4)
	public void logInUser() {
		Response res = given().header("Content-Type", "application/json")
						.body("{\r\n"
								+ "\"email\": \""+ NewEmail +"\",\r\n"
								+ "\"password\": \"akula123\"\r\n"
								+ "}")
						.when().post("https://thinking-tester-contact-list.herokuapp.com/users/login");
		System.out.println("--LoginUser--");
			res.then().log().body();
			
			token = res.jsonPath().getString("token");
			
			Assert.assertEquals(res.getStatusCode(), 200);
			Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(priority = 5, dependsOnMethods = "logInUser")
	public void addContact() {
		Response res = given().header("Content-Type", "application/json")
							  .header("Authorization", "Bearer " + token)
					   .body("{\r\n"
					   		+ "\"firstName\": \"John\",\r\n"
					   		+ "\"lastName\": \"Doe\",\r\n"
					   		+ "\"birthdate\": \"1970-01-01\",\r\n"
					   		+ "\"email\": \"jdoe@fake.com\",\r\n"
					   		+ "\"phone\": \"8005555555\",\r\n"
					   		+ "\"street1\": \"1 Main St.\",\r\n"
					   		+ "\"street2\": \"Apartment A\",\r\n"
					   		+ "\"city\": \"Anytown\",\r\n"
					   		+ "\"stateProvince\": \"KS\",\r\n"
					   		+ "\"postalCode\": \"12345\",\r\n"
					   		+ "\"country\": \"USA\"\r\n"
					   		+ "}")
					   .when().post("https://thinking-tester-contact-list.herokuapp.com/contacts");
			System.out.println("--addContact--");
			res.then().log().body();
			
			Assert.assertEquals(res.statusCode(), 201);
			Assert.assertEquals(res.statusLine(), "HTTP/1.1 201 Created");
	}
	
	@Test(priority = 6)
	public void getContactList() {
		Response res = given().header("Content-Type", "application/json")
							  .header("Authorization", "Bearer " + token)
					   .when().get("https://thinking-tester-contact-list.herokuapp.com/contacts");
		System.out.println("--getContactList--");
		res.then().log().body();
		
		contactID = res.jsonPath().getString("[0]._id");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(priority = 7, dependsOnMethods = "getContactList")
	public void getContact() {
		Response res = given().header("Content-Type", "application/json")
							  .header("Authorization", "Bearer " + token)
					   .when().get("https://thinking-tester-contact-list.herokuapp.com/contacts/" + contactID);
		System.out.println("--getContact--");
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(priority = 8)
	public void updateContact() {
		Response res = given().header("Content-Type", "application/json")
							  .header("Authorization", "Bearer " + token)
					   .body("{\r\n"
					   		+ "\"firstName\": \"Amy\",\r\n"
					   		+ "\"lastName\": \"Miller\",\r\n"
					   		+ "\"birthdate\": \"1992-02-02\",\r\n"
					   		+ "\"email\": \"amiller@fake.com\",\r\n"
					   		+ "\"phone\": \"8005554242\",\r\n"
					   		+ "\"street1\": \"13 School St.\",\r\n"
					   		+ "\"street2\": \"Apt. 5\",\r\n"
					   		+ "\"city\": \"Washington\",\r\n"
					   		+ "\"stateProvince\": \"QC\",\r\n"
					   		+ "\"postalCode\": \"A1A1A1\",\r\n"
					   		+ "\"country\": \"Canada\"\r\n"
					   		+ "}")
					   .when().put("https://thinking-tester-contact-list.herokuapp.com/contacts/" + contactID);
		System.out.println("--updateContact--");
		res.then().log().body();
		
		String email = res.jsonPath().getString("email");
		Assert.assertEquals(email, "amiller@fake.com");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(priority = 9)
	public void updateContact1() {
		Response res = given().header("Content-Type", "application/json")
							  .header("Authorization", "Bearer " + token)
					   .body("{\r\n"
					   		+ "\"firstName\": \"Anna\"\r\n"
					   		+ "}\r\n"
					   		+ "")
					   .when().patch("https://thinking-tester-contact-list.herokuapp.com/contacts/" + contactID);
		System.out.println("--updateContact1--");
		res.then().log().body();
		
		String firstName = res.jsonPath().getString("firstName");
		Assert.assertEquals(firstName, "Anna");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
	}
	
	@Test(priority = 10)
	public void logoutUser() {
		Response res = given().header("Content-Type", "application/json")
							  .header("Authorization", "Bearer " + token)
					   .when().post("https://thinking-tester-contact-list.herokuapp.com/users/logout");
		System.out.println("--logoutUser--");
		
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
	}

}
