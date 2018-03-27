package comp3350.podcast.acceptance;

import android.test.ActivityInstrumentationTestCase2;
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

		//If there was some interruption that prevented deletion last time
		if(solo.searchText("Favourites"))
		{
			solo.clickOnButton("Favourites");
			solo.clickOnButton("Delete Playlist");
		}

		solo.clickOnButton("Create New Playlist");
		solo.waitForDialogToOpen();
		solo.clearEditText(0);
		solo.enterText(0, "Favourites");
		solo.clickOnButton("Create");
		solo.assertCurrentActivity("Expected PlaylistActivity","PlaylistActivity");
		solo.goBackToActivity("MainActivity");

		solo.assertCurrentActivity("Expected MainActivity","MainActivity");
		Assert.assertTrue(solo.searchText("Favourites"));

		solo.clickOnButton("All \nEpisodes");
		solo.clickInRecyclerView(0,0);
		solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");
		solo.clickOnButton("Add to playlist");
		solo.assertCurrentActivity("Expected AddToPlaylistActivity","AddToPlaylistActivity");
		solo.clickOnButton("Favourites");

		solo.goBackToActivity("ViewEpisodeActivity");
		solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");

		solo.sleep(500);

		solo.goBackToActivity("MainActivity");
		solo.assertCurrentActivity("Expected MainActivity","MainActivity");

		solo.clickOnButton("All \nEpisodes");
		solo.clickInRecyclerView(1,0);
		solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");
		solo.clickOnButton("Add to playlist");
		solo.assertCurrentActivity("Expected AddToPlaylistActivity","AddToPlaylistActivity");
		solo.clickOnButton("Favourites");

		solo.goBackToActivity("ViewEpisodeActivity");
		solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");

		solo.sleep(500);

		solo.goBackToActivity("MainActivity");
		solo.assertCurrentActivity("Expected MainActivity","MainActivity");

		solo.sleep(500);

		solo.clickOnButton("Favourites");

		LinearLayout pl = (LinearLayout) solo.getView(R.id.resultLayout);
		Assert.assertTrue(pl.getChildCount() >= 2);

		solo.clickOnButton("Delete Playlist");
	}

	public void testNamelessPlaylist()
	{

		solo.clickOnButton("Create New Playlist");
		solo.waitForDialogToOpen();
		solo.clearEditText(0);
		solo.enterText(0, "");
		solo.clickOnButton("Create");

		solo.waitForDialogToOpen();
		Assert.assertTrue( solo.searchText("Enter playlist name") );

	}
}
