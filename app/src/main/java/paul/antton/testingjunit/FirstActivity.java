package paul.antton.testingjunit;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class FirstActivity extends Activity {

    EditText mEditText;
    Button mButton;
    Spinner mSpinner ;

    protected int mPos;
    protected String mSelection;

    protected ArrayAdapter<CharSequence> mAdapter;

    public static final int DEFAULT_POSITION = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mSpinner = (Spinner)findViewById(R.id.Spinner01);
        mEditText = (EditText)findViewById(R.id.spinnerResult);
        mButton = (Button)findViewById(R.id.firstButton);

        mAdapter = ArrayAdapter.createFromResource(this, R.array.Planets,
                android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mAdapter);

        AdapterView.OnItemSelectedListener spinnerListener = new myOnItemSelectedListener(this,this.mAdapter);

        mSpinner.setOnItemSelectedListener(spinnerListener);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, mEditText.getText().toString());
                startActivity(intent);
            }
        });
    }

    public class myOnItemSelectedListener implements AdapterView.OnItemSelectedListener
    {
        ArrayAdapter<CharSequence> mLocalAdapter;
        Activity mLocalContext;

        public myOnItemSelectedListener(Activity c, ArrayAdapter<CharSequence> ad) {

            this.mLocalContext = c;
            this.mLocalAdapter = ad;

        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            FirstActivity.this.mPos = position;
            FirstActivity.this.mSelection = parent.getItemAtPosition(position).toString();

            mEditText.setText(FirstActivity.this.mSelection);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Spinner restoreSpinner = (Spinner)findViewById(R.id.Spinner01);
        restoreSpinner.setSelection(getSpinnerPosition());
    }


    public int getSpinnerPosition() {
        return this.mPos;
    }

    public void setSpinnerPosition(int pos) {
        this.mPos = pos;
    }

    public String getSpinnerSelection() {
        return this.mSelection;
    }

    public void setSpinnerSelection(String selection) {
        this.mSelection = selection;
    }
}
