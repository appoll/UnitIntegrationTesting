package paul.antton.testingjunit;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Paul's on 07-Jan-15.
 */
public class FirstActivityUnitTest extends ActivityUnitTestCase<FirstActivity> {

    private FirstActivity activity;
    private EditText textField;
    private Button firstButton;


    public FirstActivityUnitTest() {
        super(FirstActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Intent intent = new Intent (getInstrumentation().getTargetContext(), FirstActivity.class);
        startActivity(intent, null,null);
        activity = getActivity();


        firstButton = (Button)activity.findViewById(R.id.firstButton);
        textField = (EditText)activity.findViewById(R.id.textField);
    }

    public void testLayout()
    {
        // verify button and text field existence
        assertNotNull("button is null", activity.findViewById(R.id.firstButton));
       assertNotNull("text field is null", activity.findViewById(R.id.textField));

        //verify correct label of the button
        assertEquals("incorrect button label", activity.getString(R.string.first_button_label), firstButton.getText());
    }


    // verify intent fired on button click
    public void testIntentTriggerOnClick()
    {
        String fieldValue = "Desdet Dexd";


        textField.setText(fieldValue);
        firstButton.performClick();

        // verify intent triggered
        Intent triggeredIntent = getStartedActivityIntent();
        assertNotNull("intent supposed to be triggered on button click", triggeredIntent);

        // verify intent extra
        String passedValue = triggeredIntent.getStringExtra(Intent.EXTRA_TEXT);
        assertEquals("incorrect string value passed to the second activity", fieldValue, passedValue);

    }
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
