package paul.antton.testingjunit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

/**
 * Created by Paul's on 07-Jan-15.
 */
public class SecondActivity extends Activity {

    TextView mTextView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextView = (TextView)findViewById(R.id.destinationField);


        String passedString = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        mTextView.setText(passedString);
    }
}
