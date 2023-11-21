package br.mateus.diario.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.mateus.diario.Model.Diario;
import br.mateus.diario.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    TextView titulo;
    TextView descricao;
    TextView humor;
    DatePicker calendario;
    ImageButton salvar;
    ImageButton sair;
    ImageButton notificacao;
    DatabaseReference databaseReference;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    NotificationFragment notFragmento;
    private Button datePickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        titulo = findViewById(R.id.idTitulo);
        notFragmento = new NotificationFragment();
        getSupportFragmentManager().beginTransaction().add(notFragmento,"not").commit();
        calendario = findViewById(R.id.datePicker);
        descricao = findViewById(R.id.idDescricao);
        salvar = findViewById(R.id.idSalvar);
        sair = findViewById(R.id.idSair);
        notificacao = findViewById(R.id.idNotificacao);
        humor = findViewById(R.id.idHumor);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.fecha,R.string.abre);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        datePickerButton = findViewById(R.id.datePickerButton);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
        notificacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirNotificacao();
            }
        });
        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar(view);
            }
        });
    }
    public void salvar(View view){
        Diario d = new Diario();
        String tituloD = titulo.getText().toString().trim();
        String descricaoD = descricao.getText().toString().trim();
        String humorD = humor.getText().toString().trim();
        String dataD = calendario.getDayOfMonth() + "/" + (calendario.getMonth() + 1) + "/" + calendario.getYear();//
        DatabaseReference diario = databaseReference.child("pensamentos");

        if(tituloD.isEmpty() || descricaoD.isEmpty() || humorD.isEmpty()){
            Toast.makeText(this, "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show();
        }else{
            d.setTitulo(tituloD);
            d.setDescricao(descricaoD);
            d.setHumor(humorD);
            d.setData(dataD);
            diario.push().setValue(d);
            Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show();
            limparCampos();
        }
    }
    public void limparCampos(){
        titulo.setText(" ");
        descricao.setText(" ");
        humor.setText(" ");
        calendario.updateDate(2023,0,1);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    private void showDatePicker() {
        // Pega a data atual para pré-configurar o DatePicker
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Cria um DatePickerDialog com a data atual
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Aqui você pode lidar com a data selecionada pelo usuário
                        // Por exemplo, exibir em um TextView ou salvar em uma variável
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        datePickerButton.setText(selectedDate);
                    }
                }, year, month, dayOfMonth);

        // Exibe o DatePickerDialog como um popup
        datePickerDialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.novoPensamento){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.Pensamentos){
            Intent intent = new Intent(MainActivity.this, RecycleActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
    private void abrirNotificacao() {
        // Inicia o fragmento de adicionar notificação
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.idFragmentN, new NotificationFragment())
                .addToBackStack(null)
                .commit();
    }
}