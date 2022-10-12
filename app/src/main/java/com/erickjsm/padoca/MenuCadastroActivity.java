package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuCadastroActivity extends AppCompatActivity {

    Button btn_menuCriarFuncionario;
    Button btn_menuCriarCliente;
    Button btn_menuCriarProduto;
    Button btn_menuCriarPedido;
    Button btn_menuCriarEmpresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cadastro);

        btn_menuCriarFuncionario = findViewById(R.id.btn_menuCriarFuncionario);
        btn_menuCriarCliente = findViewById(R.id.btn_menuCriarCliente);
        btn_menuCriarProduto = findViewById(R.id.btn_menuCriarProduto);
        btn_menuCriarEmpresa = findViewById(R.id.btn_menuCriarEmpresa);
        btn_menuCriarPedido = findViewById(R.id.btn_menuCriarPedido);


        btn_menuCriarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela_menuCriarCliente = new Intent(MenuCadastroActivity.this, RegisterActivity_Cliente.class);
                finish();
                startActivity(tela_menuCriarCliente);
            }
        });

        btn_menuCriarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela_menuFuncionario = new Intent(MenuCadastroActivity.this, RegisterActivity_Funcionarios.class);
                finish();
                startActivity(tela_menuFuncionario);
            }
        });

        btn_menuCriarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela_menuCriarProduto = new Intent(MenuCadastroActivity.this, RegisterActivity_Produto.class);
                finish();
                startActivity(tela_menuCriarProduto);
            }
        });

        btn_menuCriarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela_menuCriarEmpresa = new Intent(MenuCadastroActivity.this, RegisterActivity_Fornecedor.class);
                finish();
                startActivity(tela_menuCriarEmpresa);
            }
        });

        btn_menuCriarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela_menuCriarPedido= new Intent(MenuCadastroActivity.this, RegisterActivity_Pedido.class);
                finish();
                startActivity(tela_menuCriarPedido);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent tela_menu = new Intent(this, MenuActivity.class);
        startActivity(tela_menu);
        super.onBackPressed();
    }
}