package ro.pub.cs.systems.eim.colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private EditText directions;
    private int number_clicks = 0;
    private Button north_button, west_button, east_button, south_button;
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            String s = directions.getText().toString();

            Log.d("clicks:", String.valueOf(number_clicks));

            switch(view.getId()) {
                case R.id.north_button:
                    if (s.length() == 0) {
                        s += "North";
                        directions.setText(s);
                    }
                    else {
                        s += ",North";
                        directions.setText(s);
                    }
                    number_clicks++;
                    break;
                case R.id.west_button:
                    if (s.length() == 0) {
                        s += "West";
                        directions.setText(s);
                    }
                    else {
                        s += ",West";
                        directions.setText(s);
                    }
                    number_clicks++;
                    break;
                case R.id.east_button:
                    if (s.length() == 0) {
                        s += "East";
                        directions.setText(s);
                    }
                    else {
                        s += ",East";
                        directions.setText(s);
                    }
                    number_clicks++;
                    break;
                case R.id.south_button:
                    if (s.length() == 0) {
                        s += "South";
                        directions.setText(s);
                    }
                    else {
                        s += ",South";
                        directions.setText(s);
                    }
                    number_clicks++;
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviu1_13_main);

        directions = (EditText)findViewById(R.id.directions);

        north_button = (Button)findViewById(R.id.north_button);
        west_button = (Button)findViewById(R.id.west_button);
        east_button = (Button)findViewById(R.id.east_button);
        south_button = (Button)findViewById(R.id.south_button);

        north_button.setOnClickListener(buttonClickListener);
        west_button.setOnClickListener(buttonClickListener);
        east_button.setOnClickListener(buttonClickListener);
        south_button.setOnClickListener(buttonClickListener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("number clicks")) {
                number_clicks = Integer.valueOf(savedInstanceState.getString("number clicks"));
            } else {
                number_clicks = 0;
            }
        }
        else {
            number_clicks = 0;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("number clicks", String.valueOf(number_clicks));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("number clicks")) {
            number_clicks = Integer.valueOf(savedInstanceState.getString("number clicks"));
        } else {
            number_clicks = 0;
        }
    }

}
