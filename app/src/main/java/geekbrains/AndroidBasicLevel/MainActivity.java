package geekbrains.AndroidBasicLevel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView mainTitle = findViewById(R.id.mainTitle);
        final TextView cityName = findViewById(R.id.cityName);
        final TextView temperature = findViewById(R.id.temperature);
        final ImageView mainImage = findViewById(R.id.mainImage);
        final ImageView imageTop = findViewById(R.id.imageTop);
        final ImageView imageBottom = findViewById(R.id.imageBottom);

        cityName.setText(R.string.cityName);
        temperature.setText(R.string.temperature);


    }
}