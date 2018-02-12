package test;

import org.testng.annotations.*;

//we want to be able to setup and cleanup a basic test
public interface BasicTest {
	@BeforeTest
	public void SetUp();
	@AfterTest
	public void CleanUp();
}
