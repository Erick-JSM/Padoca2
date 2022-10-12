package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Fornecedor;

public class RegisterActivity_Fornecedor extends AppCompatActivity {

    Button btn_criarFornecedor;
    EditText etxt_Cad_nomeFornecedor;
    EditText etxt_Cad_fornecedorEmail;
    EditText etxt_Cad_cnpjFornecedor;
    EditText etxt_Cad_endFornecedor;
    EditText etxt_Cad_telFornecedor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_fornecedor);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        btn_criarFornecedor = findViewById(R.id.btn_criarFornecedor);
        etxt_Cad_nomeFornecedor = findViewById(R.id.etxt_Cad_ProdutoCod);
        etxt_Cad_fornecedorEmail = findViewById(R.id.etxt_Cad_fornecedorEmail);
        etxt_Cad_cnpjFornecedor = findViewById(R.id.etxt_Cad_ProdutoValidadeI);
        etxt_Cad_endFornecedor = findViewById(R.id.etxt_Cad_ProdutoValidadeF);
        etxt_Cad_telFornecedor = findViewById(R.id.etxt_Cad_ProdutoValor);

        btn_criarFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!(etxt_Cad_cnpjFornecedor.getText().toString().equals("") || etxt_Cad_nomeFornecedor.getText().toString().equals("") || etxt_Cad_telFornecedor.getText().toString().equals(""))) {
                    DAO dao = new DAO(getApplicationContext());
                    Fornecedor fornecedor = new Fornecedor();
                    fornecedor.setNome(String.valueOf(etxt_Cad_nomeFornecedor.getText().toString()));
                    fornecedor.setCnpj(String.valueOf(etxt_Cad_cnpjFornecedor.getText().toString()));
                    fornecedor.setEmail(String.valueOf(etxt_Cad_fornecedorEmail.getText().toString()));
                    fornecedor.setEndereco(String.valueOf(etxt_Cad_endFornecedor.getText().toString()));
                    fornecedor.setTelefone(String.valueOf(etxt_Cad_telFornecedor.getText().toString()));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Os campos Nome, CNPJ e Telefone são obrigatorios!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent tela_menuCadrastro = new Intent(this, MenuCadastroActivity.class);
        startActivity(tela_menuCadrastro);
        super.onBackPressed();
    }
}