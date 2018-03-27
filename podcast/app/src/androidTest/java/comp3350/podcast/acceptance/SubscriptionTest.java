package comp3350.podcast.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.LinearLayout;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import comp3350.podcast.R;
import comp3350.podcast.presentation.MainActivity;

public class SubscriptionTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public SubscriptionTest()
	{
		super(MainActivity.class);
	}

	public void setUp() throws Exception
	{
		solo = new Solo(getInstrumentation(), getActivity());
		
		// Disable this for full acceptance test
		// System.out.println("Injecting stub database.");
		// Services.createDataAccess(new DataAccessStub(Main.dbName));
	}
	
	@Override
	public void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}


	public void testSubscriptionFromChannelPage()
	{
		solo.waitForActivity("MainActivity");

		solo.clickOnButton("Subscribed \nChannels");
		Assert.assertTrue(solo.searchText("NBA Hang Time"));

		solo.clickOnButton("All \nChannels");
		Assert.assertTrue(solo.searchText("The Joe Rogan Experience"));

		solo.clickInRecyclerView(1,0);
		solo.assertCurrentActivity("Expected ViewChannelActivity","ViewChannelActivity");
		Assert.assertTrue(solo.searchText("The Joe Rogan Experience"));

		solo.clickOnButton("Subscribe");

		solo.goBackToActivity("MainActivity");
		solo.assertCurrentActivity("Expected MainActivity","MainActivity");

		solo.clickOnButton("Subscribed \nChannels");
		Assert.assertTrue(solo.searchText("The Joe Rogan Experience"));

		solo.clickInRecyclerView(1,0);
		solo.assertCurrentActivity("Expected ViewChannelActivity","ViewChannelActivity");
		Assert.assertTrue(solo.searchText("The Joe Rogan Experience"));
		solo.clickOnButton("Unsubscribe");

		solo.goBackToActivity("MainActivity");
		solo.assertCurrentActivity("Expected MainActivity","MainActivity");

		solo.clickOnButton("Subscribed \nChannels");
		Assert.assertTrue( !(solo.searchText("The Joe Rogan Experience")) );
	}

	public void testSubscriptionFromMain()
	{
		solo.waitForActivity("MainActivity");

		solo.clickOnButton("Subscribed \nChannels");
		Assert.assertTrue(solo.searchText("NBA Hang Time"));

		solo.clickOnButton("All \nChannels");
		Assert.assertTrue(solo.searchText("The Joe Rogan Experience"));

		solo.clickLongInRecycleView(1,0);
		solo.searchText("Subscribe");
		solo.clickOnText("Subscribe");

		solo.clickOnButton("Subscribed \nChannels");
		Assert.assertTrue(solo.searchText("The Joe Rogan Experience"));

		solo.clickLongInRecycleView(1,0);
		solo.searchText("Unsubscribe");
		solo.clickOnText("Unsubscribe");

		//solo.clickOnButton("Subscribed \nChannels");
		Assert.assertTrue( !(solo.searchText("The Joe Rogan Experience")) );
	}
}
