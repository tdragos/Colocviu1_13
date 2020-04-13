package ro.pub.cs.systems.eim.colocviu1_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Colocviu1_13MainActivity extends AppCompatActivity {

    private EditText directions;
    private int number_clicks = 0;
    private Button north_button, west_button, east_button, south_button;
    private Button navigateToSecondaryActivityButton;
    private IntentFilter intentFilter = new IntentFilter();
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
                case R.id.navigate_to_secondary_activity_button:
                Intent intent = new Intent(getApplicationContext(), Colocviu1_13SecondaryActivity.class);
                intent.putExtra("directions", s);
                s = "";
                directions.setText(s);
                number_clicks = 0;
                startActivityForResult(intent, 1);
                break;
            }

            if (number_clicks == 4)
            {
                Intent intent = new Intent(getApplicationContext(), Colocviu1_13Service.class);
                intent.putExtra("directions", s);
                getApplicationContext().startService(intent);
            }

        }
    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("bcast", intent.getStringExtra("service"));
        }
    }


    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, Colocviu1_13Service.class);
        stopService(intent);
        super.onDestroy();
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

        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);
        navigateToSecondaryActivityButton.setOnClickListener(buttonClickListener);

        north_button.setOnClickListener(buttonClickListener);
        west_button.setOnClickListener(buttonClickListener);
        east_button.setOnClickListener(buttonClickListener);
        south_button.setOnClickListener(buttonClickListener);

        intentFilter.addAction("10");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == -1)
            Toast.makeText(this, "The activity returned with register ", Toast.LENGTH_LONG).show();
        else if (resultCode == 0)
            Toast.makeText(this, "The activity returned with cancel ", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }


}
