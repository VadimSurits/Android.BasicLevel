package geekbrains.AndroidBasicLevel;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import geekbrains.AndroidBasicLevel.Bonus.BonusActivity1;

public class SettingsActivity extends AppCompatActivity {

    private View v;
    //Добавляем BottomBar
    private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:
                    mTextMessage.setText(R.string.title_main);
                    startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_aboutDevelopers:
                    mTextMessage.setText(R.string.aboutDeveloper);
                    Snackbar.make(v, "Разработчик еще учится", Snackbar.LENGTH_INDEFINITE)
                            .setAction("Закрыть окно", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            }).show();
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText(R.string.title_settings);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message );
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        v = findViewById(R.id.settingsActivity); //создал view для Snackbar

//        String instanceState;
//        if(savedInstanceState == null){
//            instanceState = "Первый запуск";
//        } else{
//            instanceState = "Повторный запуск";
//        }
//        Toast.makeText(getApplicationContext(), instanceState + " SettingsActivity - onCreate()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " onCreate()");


        Button buttonBonus = findViewById(R.id.buttonBonus);

        buttonBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, BonusActivity1.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(getApplicationContext()," SettingsActivity - onStart()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
//        Toast.makeText(getApplicationContext(), "Повторный запуск SettingsActivity - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " Повторный запуск SettingsActivity - onRestoreInstanceState()");
    }

    @Override
    protected void onResume(){
        super.onResume();
//        Toast.makeText(getApplicationContext(), " SettingsActivity - onResume()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
//        Toast.makeText(getApplicationContext(), " SettingsActivity - onPause()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
//        Toast.makeText(getApplicationContext(), " SettingsActivity - onSaveInstanceState()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " onSaveInstanceState()");
    }

    @Override
    protected void onStop(){
        super.onStop();
//        Toast.makeText(getApplicationContext(), " SettingsActivity - onStop()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
//        Toast.makeText(getApplicationContext(), " SettingsActivity - onRestart()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
//        Toast.makeText(getApplicationContext(), " SettingsActivity - onDestroy()", Toast.LENGTH_SHORT).show();
//        Log.d("SettingsActivity", " onDestroy()");
    }
}