package testScript;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseLibrary.BaseClass;
import pageHelper.mmtSearchFlight;

public class mmtSearchFlight_test extends BaseClass {
	mmtSearchFlight ob;

	@Test(priority = 1)
	@Parameters(value = {"browser","url"})
	public void searcghFlight() {
		ob = new mmtSearchFlight();
		ob.SearchFlight();
		ob.sortByDetarture();
		ob.getPrice();
	}

}
