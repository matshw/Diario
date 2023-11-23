package br.mateus.diario.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

import br.mateus.diario.R;

public class NotificationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.fecha,R.string.abre);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.novoPensamento){
            Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.Pensamentos){
            Intent intent = new Intent(NotificationActivity.this, RecycleActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Notificacao) {
            Intent intent = new Intent(NotificationActivity.this,NotificationActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Informacoes){
            Intent intent = new Intent(NotificationActivity.this,InfoActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}