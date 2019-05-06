package net.br.smokehoot.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import net.br.smokehoot.R;
import net.br.smokehoot.config.ConfiguracaoFirebase;
import net.br.smokehoot.model.Usuario;



public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha;
    private Button botaoCadastrar;
    private FirebaseAuth autenticacao;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // INSTANCIANDO OS ELEMENTOS
        campoNome = findViewById(R.id.editNome);
        campoEmail = findViewById(R.id.editEmail);
        campoSenha = findViewById(R.id.editSenha);
        botaoCadastrar = findViewById(R.id.btnCadastrar);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = campoNome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoSenha = campoSenha.getText().toString();

                // VALIDANDO SE TODOS OS CAMPOS FORAM PREENCHIDOS
                if (!textoNome.isEmpty()) {
                    if (!textoEmail.isEmpty()) {
                        if (!textoSenha.isEmpty()) {


                            usuario = new Usuario();
                            usuario.setNome( textoNome );
                            usuario.setEmail( textoEmail );
                            usuario.setSenha( textoSenha );
                            cadastrarUsuario();


                            // SE TODOS OS CAMPOS SÃO DIFERENTES DE VAZIO, O MÉTODO "CADASTRARUSUARIO" SERÁ CHAMADO.
                            cadastrarUsuario();

                        } else {
                            Toast.makeText(CadastroActivity.this, "Preencha a senha", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroActivity.this, "Preencha o e-mail", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this, "Preencha o nome", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void cadastrarUsuario(){

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if ( task.isSuccessful() ){

                    finish();

                } else {

                    String excecao = "";

                    try{
                        throw task.getException();
                    } catch ( FirebaseAuthWeakPasswordException e ){
                        excecao = "Digite uma senha mais forte!";
                    }catch ( FirebaseAuthInvalidCredentialsException e ){
                        excecao = "Por favor, digite um e-mail válido!";
                    } catch ( FirebaseAuthUserCollisionException e ){
                        excecao = "E-mail já cadastrado em nosso sistema";
                    } catch ( Exception e ) {
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }


                    Toast.makeText(CadastroActivity.this, excecao, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}