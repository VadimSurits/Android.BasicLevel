package geekbrains.AndroidBasicLevel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CityChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choice);

        String instanceState;
        if(savedInstanceState == null){
            instanceState = "Первый запуск";
        } else{
            instanceState = "Повторный запуск";
        }
        Toast.makeText(getApplicationContext(), instanceState + " CityChoiceActivity - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext()," CityChoiceActivity - onStart()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск CityChoiceActivity - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " Повторный запуск CityChoiceActivity - onRestoreInstanceState()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onResume()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onPause()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " onSaveInstanceState()");
    }


    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onStop()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onRestart()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d("CityChoiceActivity", " onDestroy()");
    }
}