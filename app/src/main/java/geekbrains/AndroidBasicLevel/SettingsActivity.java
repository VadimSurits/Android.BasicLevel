package geekbrains.AndroidBasicLevel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        String instanceState;
        if(savedInstanceState == null){
            instanceState = "Первый запуск";
        } else{
            instanceState = "Повторный запуск";
        }
        Toast.makeText(getApplicationContext(), instanceState + " SettingsActivity - onCreate()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext()," SettingsActivity - onStart()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск SettingsActivity - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " Повторный запуск SettingsActivity - onRestoreInstanceState()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(), " SettingsActivity - onResume()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(getApplicationContext(), " SettingsActivity - onPause()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), " SettingsActivity - onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " onSaveInstanceState()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(getApplicationContext(), " SettingsActivity - onStop()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(getApplicationContext(), " SettingsActivity - onRestart()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), " SettingsActivity - onDestroy()", Toast.LENGTH_SHORT).show();
        Log.d("SettingsActivity", " onDestroy()");
    }
}