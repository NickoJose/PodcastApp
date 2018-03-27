package comp3350.podcast.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import com.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;
import comp3350.podcast.R;
import comp3350.podcast.presentation.MainActivity;
import comp3350.podcast.presentation.ViewChannelActivity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class MainTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public MainTest()
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

	public void testMain()
	{
		solo.waitForActivity("MainActivity");

		solo.clickOnButton("Subscribed \nChannels");
		solo.clickOnButton("All \nEpisodes");
		Assert.assertTrue(solo.searchText("#890 - Fight Breakdown"));

		solo.clickOnButton("All \nChannels");
		Assert.assertTrue(solo.searchText("NBA Hang Time"));

		solo.clickInRecyclerView(0,0);
		solo.assertCurrentActivity("Expected ViewChannelActivity","ViewChannelActivity");
		Assert.assertTrue(solo.searchText("NBA Hang Time"));

		LinearLayout ll = (LinearLayout) solo.getView(R.id.episode_list);
		View view = ll.getChildAt(0);
		solo.clickOnView(view);
		solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");

		solo.clickOnButton("Play");
		solo.assertCurrentActivity("Expected PlayContentActivity","PlayContentActivity");
		solo.sleep(3000);
		solo.clickOnButton("Pause");

		solo.goBackToActivity("ViewEpisodeActivity");
        solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");

        solo.goBackToActivity("MainActivity");
        solo.assertCurrentActivity("Expected MainActivity","MainActivity");

        solo.clickOnButton("All \nEpisodes");
        solo.clickInRecyclerView(0,0);

        solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");

        solo.clickOnButton("Play");
        solo.assertCurrentActivity("Expected PlayContentActivity","PlayContentActivity");
        solo.sleep(3000);
        solo.clickOnButton("Pause");



	}

	public void testRecommended()
	{
		solo.waitForActivity("MainActivity");

		solo.hideSoftKeyboard();

		//make sure keyboard isn't hiding the scrolling action
		solo.sleep(1500);

		//not necessary for Robotium to find an episode but showcases scrolling
		View sv = (View)solo.getView(R.id.horizontalScrollView);
		solo.scrollViewToSide(sv,solo.RIGHT);

		LinearLayout reclist = (LinearLayout)solo.getView(R.id.recLayout);
		View ep = (View)reclist.getChildAt(2);
		solo.clickOnView(ep);
		solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");

		solo.clickOnButton("Play");
		solo.assertCurrentActivity("Expected PlayContentActivity","PlayContentActivity");

		solo.sleep(3000);
		solo.clickOnButton("Pause");
	}
}
