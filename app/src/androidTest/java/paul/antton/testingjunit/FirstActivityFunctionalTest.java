package paul.antton.testingjunit;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Paul's on 07-Jan-15.
 */
public class FirstActivityFunctionalTest extends ActivityInstrumentationTestCase2<FirstActivity> {

    private FirstActivity activity;
    private EditText textField;
    private Button firstButton;
    private TextView destinationField;

    public FirstActivityFunctionalTest()
    {
        super (FirstActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);

        activity = getActivity();
        textField = (EditText)activity.findViewById(R.id.spinnerResult);
        firstButton = (Button)activity.findViewById(R.id.firstButton);
    }



    public void testStartSecondActivity() throws Exception
    {
        final String fieldValue = "Desdet Dexd";
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textField.setText(fieldValue);
            }
        });

        // Monitor the second activity
        Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(SecondActivity.class.getName(), null, false);

        //click button to launch second activity
        TouchUtils.clickView(this,firstButton);

        // wait for activity to start
        SecondActivity secondActivity = (SecondActivity) monitor.waitForActivityWithTimeout(2000);
        assertNotNull("second activity is null and shouldn't be", secondActivity);

        //find text view in second activity
        destinationField = (TextView) secondActivity.findViewById(R.id.destinationField);

        //verify that the textview is on the screen
        ViewAsserts.assertOnScreen(secondActivity.getWindow().getDecorView(), destinationField);

        //verify text
        assertEquals("Text is not the same as sent through intent", fieldValue, destinationField.getText().toString() );

        //press back to return to initial activity
        this.sendKeys(KeyEvent.KEYCODE_BACK);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
