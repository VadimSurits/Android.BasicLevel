package geekbrains.AndroidBasicLevel.PreviousRequests;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import geekbrains.AndroidBasicLevel.R;

public class PreviousRequestsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerDataAdapter_for_PRActivity recyclerDataAdapter_for_prActivity;
    private ArrayList<PreviousRequest> previousRequests = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_requests);

        previousRequests.add(new PreviousRequest("Moscow",250.5f, 1000,10));
        previousRequests.add(new PreviousRequest("Saint-Petersburg",260.6f, 1050,5));
        previousRequests.add(new PreviousRequest("Ekaterinburg",270.7f, 1010, 2));
        previousRequests.add(new PreviousRequest("Novosibirsk",280.8f, 1020,7));
        previousRequests.add(new PreviousRequest("Khabarovsk",290.9f, 1030,4));

        recyclerView = findViewById(R.id.recycler_view_for_HoR);
        recyclerDataAdapter_for_prActivity = new RecyclerDataAdapter_for_PRActivity(previousRequests);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(recyclerDataAdapter_for_prActivity);
    }

}