package com.erickjsm.padoca;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Produto;

public class RegisterActivity_Produto extends AppCompatActivity {

    Button btn_criaProduto;
    EditText etxt_Cad_ProdutoValor;
    EditText etxt_Cad_ProdutoQtd;
    EditText etxt_Cad_ProdutoValidadeI;
    EditText etxt_Cad_ProdutoValidadeF;
    EditText etxt_Cad_ProdutoCod;
    EditText etxt_Cad_ProdutoNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_produto);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        btn_criaProduto = findViewById(R.id.btn_criarProduto);
        etxt_Cad_ProdutoCod = findViewById(R.id.etxt_Cad_ProdutoCod);
        etxt_Cad_ProdutoNome = findViewById(R.id.etxt_Cad_ProdutoNome);
        etxt_Cad_ProdutoQtd = findViewById(R.id.etxt_Cad_ProdutoQtd);
        etxt_Cad_ProdutoValidadeF = findViewById(R.id.etxt_Cad_ProdutoValidadeF);
        etxt_Cad_ProdutoValidadeI = findViewById(R.id.etxt_Cad_ProdutoValidadeI);
        etxt_Cad_ProdutoValor = findViewById(R.id.etxt_Cad_ProdutoValor);

        btn_criaProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etxt_Cad_ProdutoCod.getText().toString().equals("") || etxt_Cad_ProdutoNome.getText().toString().equals("") /*|| etxt_Cad_ProdutoValidadeF.getText().toString().equals("") || etxt_Cad_ProdutoValidadeI.getText().toString().equals("")*/)) {
                    DAO dao = new DAO(getApplicationContext());
                    Produto produto = new Produto();
                    produto.setNome(String.valueOf(etxt_Cad_ProdutoNome.getText().toString()));
                    produto.setCodProduto(String.valueOf(etxt_Cad_ProdutoCod.getText().toString()));
                    produto.setFabricacao(String.valueOf(etxt_Cad_ProdutoValidadeI.getText().toString()));
                    produto.setTermino(String.valueOf(etxt_Cad_ProdutoValidadeF.getText().toString()));
                    produto.setValor(String.valueOf(etxt_Cad_ProdutoValor.getText().toString()));
                    produto.setQtdEstoque(String.valueOf(etxt_Cad_ProdutoQtd.getText().toString()));

                    dao.insertProduto(produto);

                    etxt_Cad_ProdutoCod.setText("");
                    etxt_Cad_ProdutoQtd.requestFocus();
                    etxt_Cad_ProdutoNome.setText("");
                    etxt_Cad_ProdutoQtd.setText("");
                    etxt_Cad_ProdutoValidadeF.setText("");
                    etxt_Cad_ProdutoValidadeI.setText("");
                    etxt_Cad_ProdutoValor.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Os campos Codigo do Produto, Nome e as Validades são obrigatorios!", Toast.LENGTH_SHORT).show();
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