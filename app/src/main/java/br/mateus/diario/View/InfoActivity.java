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

public class InfoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    Button info1;
    Button info2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        info1 = findViewById(R.id.idInfo1);
        info2 = findViewById(R.id.idInfo2);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.fecha,R.string.abre);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoFragment2 infoFragment2 = new InfoFragment2();

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, infoFragment2,null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });

        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                InfoFragment infoFragment = new InfoFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, infoFragment,null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.novoPensamento){
            Intent intent = new Intent(InfoActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.Pensamentos){
            Intent intent = new Intent(InfoActivity.this, RecycleActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Notificacao) {
            Intent intent = new Intent(InfoActivity.this,NotificationActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.Informacoes){
            Intent intent = new Intent(InfoActivity.this,InfoActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

}