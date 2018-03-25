package comp3350.podcast.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import comp3350.podcast.R;
import comp3350.podcast.presentation.MainActivity;

public class PlaylistTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public PlaylistTest()
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



	public void testMakePlaylist() {
		solo.waitForActivity("MainActivity");


		solo.clickOnButton("Create New Playlist");
		solo.waitForDialogToOpen();
		solo.clearEditText(0);
		solo.enterText(0, "Sports");
		solo.clickOnButton("Create");
		Assert.assertTrue(solo.searchText("Sports"));

		solo.clickOnButton("Create New Playlist");
		solo.waitForDialogToOpen();
		solo.clearEditText(0);
		solo.enterText(0, "Comedy");
		solo.clickOnButton("Create");
		Assert.assertTrue(solo.searchText("Comedy"));

		solo.clickOnButton("Create New Playlist");
		solo.waitForDialogToOpen();
		solo.clearEditText(0);
		solo.enterText(0, "Inspiration");
		solo.clickOnButton("Create");
		Assert.assertTrue(solo.searchText("Inspiration"));

		LinearLayout ll = (LinearLayout) solo.getView(R.id.playlistsLayout);
		Assert.assertEquals(3,ll.getChildCount());
		//TODO test that the playlist buttons go to the right place when implemented
		//View view = ll.getChildAt(0);

	}
}
