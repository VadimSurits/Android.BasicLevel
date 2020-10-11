package geekbrains.AndroidBasicLevel;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class CityChoiceActivity extends AppCompatActivity implements Constants {

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_city_choice);

        final TextInputEditText editCityName = findViewById(R.id.editCityName);
        final CheckBox checkBoxWindSpeed = findViewById(R.id.checkBoxWindSpeed);
        final CheckBox checkBoxPressure = findViewById(R.id.checkBoxPressure);

        Button buttonMoscow = findViewById(R.id.buttonMoscow);
        Button buttonSpb = findViewById(R.id.buttonSpb);
        Button buttonEkaterinburg = findViewById(R.id.buttonEkaterinburg);
        Button buttonNovosibirsk = findViewById(R.id.buttonNovosibirsk);
        Button buttonKhabarovsk = findViewById(R.id.buttonKhabarovsk);
        Button buttonDefineLocation = findViewById(R.id.buttonDefineLocation);

        editCityName.setOnKeyListener(new View.OnKeyListener(){
               public boolean onKey(View v, int keyCode, KeyEvent event) {
                   if (event.getAction() == KeyEvent.ACTION_DOWN &&
                       (keyCode == KeyEvent.KEYCODE_ENTER)) {
                       String str = editCityName.getText().toString();
                       if (str.equals(getString(R.string.buttonMoscow))) {
                           Intent intentResult = new Intent();
                           intentResult.putExtra(CITY, str);
                           setResult(RESULT_OK, intentResult);
                           intentResult.putExtra(URI, getString(R.string.UriMoscow));
                           setResult(RESULT_OK, intentResult);
                           if (checkBoxWindSpeed.isChecked()) {
                               intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           if (checkBoxPressure.isChecked()) {
                               intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           finish();
                       } else if (str.equals(getString(R.string.buttonSpb))) {
                           Intent intentResult = new Intent();
                           intentResult.putExtra(CITY, str);
                           setResult(RESULT_OK, intentResult);
                           intentResult.putExtra(URI, getString(R.string.UriSpb));
                           setResult(RESULT_OK, intentResult);
                           if (checkBoxWindSpeed.isChecked()) {
                               intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           if (checkBoxPressure.isChecked()) {
                               intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           finish();
                       } else if (str.equals(getString(R.string.buttonEkaterinburg))) {
                           Intent intentResult = new Intent();
                           intentResult.putExtra(CITY, str);
                           setResult(RESULT_OK, intentResult);
                           intentResult.putExtra(URI, getString(R.string.UriEkaterinburg));
                           setResult(RESULT_OK, intentResult);
                           if (checkBoxWindSpeed.isChecked()) {
                               intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           if (checkBoxPressure.isChecked()) {
                               intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           finish();
                       } else if (str.equals(getString(R.string.buttonNovosibirsk))) {
                           Intent intentResult = new Intent();
                           intentResult.putExtra(CITY, str);
                           setResult(RESULT_OK, intentResult);
                           intentResult.putExtra(URI, getString(R.string.UriNovosibirsk));
                           setResult(RESULT_OK, intentResult);
                           if (checkBoxWindSpeed.isChecked()) {
                               intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           if (checkBoxPressure.isChecked()) {
                               intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           finish();
                       } else if (str.equals(getString(R.string.buttonKhabarovsk))) {
                           Intent intentResult = new Intent();
                           intentResult.putExtra(CITY, str);
                           setResult(RESULT_OK, intentResult);
                           intentResult.putExtra(URI, getString(R.string.UriKhabarovsk));
                           setResult(RESULT_OK, intentResult);
                           if (checkBoxWindSpeed.isChecked()) {
                               intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           if (checkBoxPressure.isChecked()) {
                               intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
                               setResult(RESULT_OK, intentResult);
                           }
                           finish();
                       } else {
                           editCityName.setError("Вам нужно ввести название города из текущего списка" +
                                   " с заглавной буквы!");
                           Snackbar.make(v, "Вы ввели неверные данные! Можете вернуться на главный экран ----->",
                                   Snackbar.LENGTH_INDEFINITE).setAction("КНОПКА", new View.OnClickListener () {
                                       @Override
                                       public void onClick (View v) {
                                           startActivity(new Intent(CityChoiceActivity.this, MainActivity.class));
                                       }
                                   }).show();
                       }
                   }
                   return false;
               }
        });

        buttonMoscow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCityName.setText(R.string.buttonMoscow);
                Intent intentResult = new Intent();
                intentResult.putExtra(CITY,editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra(URI, getString(R.string.UriMoscow));
                setResult(RESULT_OK, intentResult);
                if (checkBoxWindSpeed.isChecked()) {
                    intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if (checkBoxPressure.isChecked()) {
                    intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
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
                intentResult.putExtra(CITY,editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra(URI, getString(R.string.UriSpb));
                setResult(RESULT_OK, intentResult);
                if (checkBoxWindSpeed.isChecked()) {
                    intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if (checkBoxPressure.isChecked()) {
                    intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
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
                intentResult.putExtra(CITY,editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra(URI, getString(R.string.UriEkaterinburg));
                setResult(RESULT_OK, intentResult);
                if (checkBoxWindSpeed.isChecked()) {
                    intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if (checkBoxPressure.isChecked()) {
                    intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
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
                intentResult.putExtra(CITY,editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra(URI, getString(R.string.UriNovosibirsk));
                setResult(RESULT_OK, intentResult);
                if (checkBoxWindSpeed.isChecked()) {
                    intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if (checkBoxPressure.isChecked()) {
                    intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
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
                intentResult.putExtra(CITY,editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra(URI, getString(R.string.UriKhabarovsk));
                setResult(RESULT_OK, intentResult);
                if (checkBoxWindSpeed.isChecked()) {
                    intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if (checkBoxPressure.isChecked()) {
                    intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                finish();
            }
        });

        buttonDefineLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editCityName.setText(R.string.searchCity);
                Intent intentResult = new Intent();
                intentResult.putExtra(CITY,editCityName.getText().toString());
                setResult(RESULT_OK, intentResult);
                intentResult.putExtra(URI, "https://geekbrains.ru");
                setResult(RESULT_OK, intentResult);
                if (checkBoxWindSpeed.isChecked()) {
                    intentResult.putExtra(WIND_SPEED, checkBoxWindSpeed.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                if (checkBoxPressure.isChecked()) {
                    intentResult.putExtra(PRESSURE, checkBoxPressure.getText().toString());
                    setResult(RESULT_OK, intentResult);
                }
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
