package net.michelison.tempconverter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements EditText.OnEditorActionListener {

    private EditText inputFahrenheit;
    private TextView displayCelsius;

    private String temperature;
    private SharedPreferences savedValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputFahrenheit = (EditText) findViewById(R.id.inputFarenheit);
        displayCelsius = (TextView) findViewById(R.id.displayCelsius);

        inputFahrenheit.setOnEditorActionListener(this);
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause(){
        // save values for both Farhenheit and Celsius
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("temperature", temperature);
        editor.commit();

        super.onPause();

    }

    @Override
    public void onResume(){
        super.onResume();
        // restore the values
        temperature = savedValues.getString("temperature", "");
        inputFahrenheit.setText(temperature);
        calculateAndDisplay();
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


    }
}
