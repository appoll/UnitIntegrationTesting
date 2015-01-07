package paul.antton.testingjunit;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/**
 * Created by Paul's on 07-Jan-15.
 */

// step by step of https://developer.android.com/tools/testing/activity_test.html
public class SpinnerTest extends ActivityInstrumentationTestCase2 <FirstActivity>{

    private FirstActivity mActivity;
    private Spinner mSpinner;
    private SpinnerAdapter mPlanetData;

    private EditText spinnerResult;

    public static final int ADAPTER_COUNT = 9;
    public static final int INITIAL_POSITION = 0;

    private int mPos ;
    private String mSelection;

    public SpinnerTest()
    {
        super(FirstActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(false);

        mActivity = getActivity();
        mSpinner = (Spinner)mActivity.findViewById(R.id.Spinner01);
        spinnerResult = (EditText) mActivity.findViewById(R.id.spinnerResult);
        mPlanetData = mSpinner.getAdapter();
    }


    // initial condition tests

    public void testPreConditions()
    {
        assertTrue(mSpinner.getOnItemSelectedListener() != null);
        assertTrue( mPlanetData != null);
        assertEquals(mPlanetData.getCount(),ADAPTER_COUNT);
        assertNotNull(spinnerResult);
    }

    public void testSpinnerUI()
    {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSpinner.requestFocus();
                mSpinner.setSelection(INITIAL_POSITION);
            }
        });

        this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);

        for (int i =1; i<=5; i++)
        {
            this.sendKeys(KeyEvent.KEYCODE_DPAD_DOWN);
        }
        this.sendKeys(KeyEvent.KEYCODE_DPAD_CENTER);

        mPos = mSpinner.getSelectedItemPosition();
        mSelection = mSpinner.getItemAtPosition(mPos).toString();

        String resultText = spinnerResult.getText().toString();

        assertEquals("spinner selection is wrong", resultText, mSelection);

    }

}
