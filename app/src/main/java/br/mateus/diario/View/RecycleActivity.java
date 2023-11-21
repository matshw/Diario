package br.mateus.diario.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.mateus.diario.Model.Diario;
import br.mateus.diario.R;
import br.mateus.diario.ViewModel.AdapterR;

public class RecycleActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DatabaseReference databaseReference;
    List<Diario> listaDiario;
    RecyclerView recyclerView;
    AdapterR adapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.idRecycle);
        listaDiario = new ArrayList<>();
        adapter = new AdapterR(listaDiario);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.fecha,R.string.abre);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listar();
    }

    public void listar(){
        DatabaseReference diario = databaseReference.child("pensamentos");
        listaDiario.clear();
        diario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot item:snapshot.getChildren()){
                    Diario diario = item.getValue(Diario.class);
                    listaDiario.add(diario);
                }
                recyclerView.setAdapter(new AdapterR(listaDiario));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RecycleActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.novoPensamento){
            Intent intent = new Intent(RecycleActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.Pensamentos){
            Intent intent = new Intent(RecycleActivity.this, RecycleActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}