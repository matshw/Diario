package br.mateus.diario.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.mateus.diario.R;

public class CadastroActivity extends AppCompatActivity {
    TextView email;
    TextView senha;
    Button cadastrar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        email = findViewById(R.id.idEmailC);
        senha = findViewById(R.id.idSenhaC);
        cadastrar = findViewById(R.id.idCadastrarC);
        mAuth = FirebaseAuth.getInstance();
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }
    private void cadastrar(){
        String emailC = email.getText().toString().trim();
        String senhaC = senha.getText().toString().trim();
        if(emailC.isEmpty() || senhaC.isEmpty()){
            Toast.makeText(this, "Preencha os dados corretamente!", Toast.LENGTH_SHORT).show();
        }else{
            criarFirebase(emailC,senhaC);
        }
    }
    private void criarFirebase(String email, String senha){
        mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    finish();
                    Toast.makeText(CadastroActivity.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }else{
                    Toast.makeText(CadastroActivity.this, "Ocorreu um erro, tente novamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}