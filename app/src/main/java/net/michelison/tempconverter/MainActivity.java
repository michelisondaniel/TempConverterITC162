package net.michelison.tempconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements EditText.OnEditorActionListener {

    private EditText inputFahrenheit;
    private TextView displayCelsius;

    private String temperature;
    private SharedPreferences savedValues;

    private static final String TAG = "TempConverter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputFahrenheit = (EditText) findViewById(R.id.inputFarenheit);
        displayCelsius = (TextView) findViewById(R.id.displayCelsius);

        inputFahrenheit.setOnEditorActionListener(this);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        Log.d(TAG, "onCreate method");
    }

    @Override
    public void onPause(){
        // save values for both Farhenheit and Celsius
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("temperature", temperature);
        editor.commit();

        super.onPause();

        Log.d(TAG, "onPause method");
    }

    @Override
    public void onResume(){
        super.onResume();
        // restore the values
        temperature = savedValues.getString("temperature", "0");
        inputFahrenheit.setText(temperature);
        calculateAndDisplay();

        Log.d(TAG, "onResume method");
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();
        return false;
    }

    private void calculateAndDisplay(){
        // create method for fahrenheit to celsius converter

        temperature = inputFahrenheit.getText().toString();
        double temp;
        temp = Double.parseDouble(temperature);


        // do math

        double celsiusTemp = (temp - 32.0) * (5.0/9.0);

        // gotta figure out how to format to display numbers

        NumberFormat celsius = NumberFormat.getNumberInstance();

        displayCelsius.setText(celsius.format(celsiusTemp));

        Log.d(TAG, "calculateAndDisplay method");

    }
}
