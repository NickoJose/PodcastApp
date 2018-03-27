package comp3350.podcast.acceptance;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.robotium.solo.Solo;

import junit.framework.Assert;

import comp3350.podcast.R;
import comp3350.podcast.presentation.MainActivity;

public class SearchTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public SearchTest()
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



	public void testSearch()
	{
		solo.waitForActivity("MainActivity");

		solo.clearEditText(0);
		solo.enterText(0,"Jamie Foxx");
		solo.clickOnButton("Search");
		solo.assertCurrentActivity("Expected Search activity","SearchableActivity");

		LinearLayout ll = (LinearLayout) solo.getView(R.id.resultLayout);
		Assert.assertTrue(ll.getChildCount() > 0);
		View view = ll.getChildAt(0);
		solo.clickOnView(view);
		solo.assertCurrentActivity("Expected ViewEpisodeActivity","ViewEpisodeActivity");

		solo.clickOnButton("Play");
		solo.assertCurrentActivity("Expected PlayContentActivity","PlayContentActivity");

		solo.sleep(3000);
		solo.clickOnButton("Pause");
	}

	public void testBadSearch()
	{

		solo.waitForActivity("MainActivity");

		solo.clearEditText(0);
		solo.enterText(0,"skdjn237kjsdn733");
		solo.clickOnButton("Search");
		solo.assertCurrentActivity("Expected Search activity","SearchableActivity");

		LinearLayout ll = (LinearLayout) solo.getView(R.id.resultLayout);
		Assert.assertEquals(0,ll.getChildCount());
	}
}
