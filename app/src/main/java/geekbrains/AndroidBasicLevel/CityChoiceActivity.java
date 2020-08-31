package geekbrains.AndroidBasicLevel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CityChoiceActivity extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_city_choice);

        final EditText editCityName = findViewById(R.id.editCityName);
        final CheckBox checkBoxWindSpeed = findViewById(R.id.checkBoxWindSpeed);
        final CheckBox checkBoxPressure = findViewById(R.id.checkBoxPressure);

        Button buttonMoscow = findViewById(R.id.buttonMoscow);
        Button buttonSpb = findViewById(R.id.buttonSpb);
        Button buttonEkaterinburg = findViewById(R.id.buttonEkaterinburg);
        Button buttonNovosibirsk = findViewById(R.id.buttonNovosibirsk);
        Button buttonKhabarovsk = findViewById(R.id.buttonKhabarovsk);

//        String instanceState;
//        if(saveInstanceState == null){
//            instanceState = "Первый запуск";
//        } else{
//            instanceState = "Повторный запуск";
//        }
//        Toast.makeText(getApplicationContext(), instanceState + " CityChoiceActivity - onCreate()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " onCreate()");

        buttonMoscow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCityName.setText(R.string.buttonMoscow);
                Intent intentResult = new Intent();
                intentResult.putExtra("CITY",editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra("URI", "https://ru.wikipedia.org/wiki/" +
                        "%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0");
                setResult(RESULT_OK, intentResult);
                if(checkBoxWindSpeed.isChecked()){
                    intentResult.putExtra("WIND_SPEED", checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if(checkBoxPressure.isChecked()){
                    intentResult.putExtra("PRESSURE", checkBoxPressure.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                finish();
            }
        });

        buttonSpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCityName.setText(R.string.buttonSpb);
                Intent intentResult = new Intent();
                intentResult.putExtra("CITY",editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra("URI",
                        "https://ru.wikipedia.org/wiki/%D0%A1%D0%B0%D0%BD%D0%BA%D1%82-" +
                                "%D0%9F%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3");
                setResult(RESULT_OK, intentResult);
                if(checkBoxWindSpeed.isChecked()){
                    intentResult.putExtra("WIND_SPEED", checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if(checkBoxPressure.isChecked()){
                    intentResult.putExtra("PRESSURE", checkBoxPressure.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                finish();
            }
        });

        buttonEkaterinburg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCityName.setText(R.string.buttonEkaterinburg);
                Intent intentResult = new Intent();
                intentResult.putExtra("CITY",editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra("URI", "https://ru.wikipedia.org/wiki/" +
                        "%D0%95%D0%BA%D0%B0%D1%82%D0%B5%D1%80%D0%B8%D0%BD%D0%B1%D1%83%D1%80%D0%B3");
                setResult(RESULT_OK, intentResult);
                if(checkBoxWindSpeed.isChecked()){
                    intentResult.putExtra("WIND_SPEED", checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if(checkBoxPressure.isChecked()){
                    intentResult.putExtra("PRESSURE", checkBoxPressure.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                finish();
            }
        });

        buttonNovosibirsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCityName.setText(R.string.buttonNovosibirsk);
                Intent intentResult = new Intent();
                intentResult.putExtra("CITY",editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra("URI", "https://ru.wikipedia.org/wiki/" +
                        "%D0%9D%D0%BE%D0%B2%D0%BE%D1%81%D0%B8%D0%B1%D0%B8%D1%80%D1%81%D0%BA");
                setResult(RESULT_OK, intentResult);
                if(checkBoxWindSpeed.isChecked()){
                    intentResult.putExtra("WIND_SPEED", checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if(checkBoxPressure.isChecked()){
                    intentResult.putExtra("PRESSURE", checkBoxPressure.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                finish();
            }
        });

        buttonKhabarovsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCityName.setText(R.string.buttonKhabarovsk);
                Intent intentResult = new Intent();
                intentResult.putExtra("CITY",editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra("URI", "https://ru.wikipedia.org/wiki/" +
                        "%D0%A5%D0%B0%D0%B1%D0%B0%D1%80%D0%BE%D0%B2%D1%81%D0%BA");
                setResult(RESULT_OK, intentResult);
                if(checkBoxWindSpeed.isChecked()){
                    intentResult.putExtra("WIND_SPEED", checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if(checkBoxPressure.isChecked()){
                    intentResult.putExtra("PRESSURE", checkBoxPressure.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(getApplicationContext()," CityChoiceActivity - onStart()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " onStart()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
//        Toast.makeText(getApplicationContext(), "Повторный запуск CityChoiceActivity - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " Повторный запуск CityChoiceActivity - onRestoreInstanceState()");
    }

    @Override
    protected void onResume(){
        super.onResume();
//        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onResume()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
//        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onPause()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " onPause()");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
//        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onSaveInstanceState()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " onSaveInstanceState()");
    }


    @Override
    protected void onStop(){
        super.onStop();
//        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onStop()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " onStop()");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
//        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onRestart()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " onRestart()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
//        Toast.makeText(getApplicationContext(), " CityChoiceActivity - onDestroy()", Toast.LENGTH_SHORT).show();
//        Log.d("CityChoiceActivity", " onDestroy()");
    }
}