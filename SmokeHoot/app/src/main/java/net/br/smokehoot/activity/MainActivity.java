package net.br.smokehoot.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import net.br.smokehoot.ActivityHome;
import net.br.smokehoot.R;
import net.br.smokehoot.config.ConfiguracaoFirebase;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificaUsuarioLogado();
    }

    public void eventoEntrar(View view){
        Intent entrar = new Intent(this, LoginActivity.class );
        startActivity(entrar);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
    }

    public void eventoCadastrar(View view){
        Intent cadastrar = new Intent(this, CadastroActivity.class );
        startActivity(cadastrar);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
    }

    public void verificaUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if( autenticacao.getCurrentUser() != null ){
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, ActivityHome.class ));
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_out_left);
    }

}
