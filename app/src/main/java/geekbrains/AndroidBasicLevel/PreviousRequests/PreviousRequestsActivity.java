package geekbrains.AndroidBasicLevel.PreviousRequests;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import geekbrains.AndroidBasicLevel.CityChoiceActivity;
import geekbrains.AndroidBasicLevel.MainActivity;
import geekbrains.AndroidBasicLevel.PreviousRequests.Dao.RequestDao;
import geekbrains.AndroidBasicLevel.R;
import geekbrains.AndroidBasicLevel.SettingsActivity;

public class PreviousRequestsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerDataAdapter_for_PRActivity recyclerDataAdapter_for_prActivity;
    public static PreviousRequestsSource previousRequestsSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_requests);
        iniRecyclerView();

        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
    }

    private void iniRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view_for_HoR);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        RequestDao requestDao = App
                .getInstance()
                .getRequestDao();
        previousRequestsSource = new PreviousRequestsSource(requestDao);

        recyclerDataAdapter_for_prActivity = new RecyclerDataAdapter_for_PRActivity(previousRequestsSource, this);
        recyclerView.setAdapter(recyclerDataAdapter_for_prActivity);
    }

    private Toolbar initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }

    private void initDrawer(Toolbar toolbar){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.nav_mainActivity:
                startActivity(new Intent(PreviousRequestsActivity.this, MainActivity.class));
                return true;
            case R.id.nav_cityChoiceActivity:
                startActivity(new Intent(PreviousRequestsActivity.this, CityChoiceActivity.class));
                return true;
            case R.id.nav_settingsActivity:
                startActivity(new Intent(PreviousRequestsActivity.this, SettingsActivity.class));
                return true;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else{
            super.onBackPressed();
        }
    }
}