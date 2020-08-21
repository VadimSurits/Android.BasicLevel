package geekbrains.AndroidBasicLevel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView appTitle = findViewById(R.id.appTitle);
        final TextView cityName = findViewById(R.id.cityName);
        final TextView temperature = findViewById(R.id.temperature);
        final ImageView mainImage = findViewById(R.id.mainImage);

        String instanceState;
        if(savedInstanceState == null){
            instanceState = "Первый запуск";
        } else{
            instanceState = "Повторный запуск";
        }

        cityName.setText(R.string.cityName);
        temperature.setText(R.string.temperature);

        Button buttonChangeCity = findViewById(R.id.buttonChangeCity);
        Button buttonSettings = findViewById(R.id.buttonSettings);

        buttonChangeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CityChoiceActivity.class));
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        Toast.makeText(getApplicationContext(), instanceState + " MainActivity - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext()," MainActivity - onStart()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveIS){
        super.onRestoreInstanceState(saveIS);
        Toast.makeText(getApplicationContext(), "Повторный запуск MainActivity - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " Повторный запуск MainActivity - onRestoreInstanceState())");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(), " MainActivity - onResume()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(getApplicationContext(), " MainActivity - onPause()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveIS){
        super.onSaveInstanceState(saveIS);
        Toast.makeText(getApplicationContext(), " MainActivity - onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onSaveInstanceState()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(getApplicationContext(), " MainActivity - onStop()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(getApplicationContext(), " MainActivity - onRestart()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), " MainActivity - onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", " onDestroy()");
    }
}
