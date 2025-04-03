package testScript;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseLibrary.BaseClass;
import pageHelper.GmailUtility;
import pageHelper.mmtSearchFlight;

public class GmailUtilityTest extends BaseClass {

	GmailUtility ob;

	@Test(priority = 1, enabled = true)
	@Parameters(value = { "browser", "url" })
	public void gmailLogin() {
		try {
		ob = new GmailUtility();
		ob.gmailLogin();
		ob.ComposeMail();
		ob.openDraftMailandSend();
		ob.moveToInbox();
		Assert.assertTrue(ob.verifyMailReceived(), "Mail received");
		ob.openMain();
		Assert.assertTrue(ob.verifySubject(), "subject verified");
		Assert.assertTrue(ob.verifyMailBody(), "mail body verified");
		Assert.assertTrue(ob.verifyAttachmentDownloaded(), "attachment downloaded successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw e;
		}
	}

}
