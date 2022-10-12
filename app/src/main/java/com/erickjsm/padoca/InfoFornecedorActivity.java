package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Fornecedor;
import com.erickjsm.padoca.obj.Funcionario;
import com.erickjsm.padoca.obj.Pedido;

public class InfoFornecedorActivity extends AppCompatActivity {

    Button atualizaFornecedor;
    Button excluiFornecedor;
    Button adicionarPedidofornecedor;
    ImageView img_infoFornecedor;

    TextView textNome;
    TextView textCnpj;
    TextView textTel;
    TextView textEndereco;
    TextView textEmail;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fornecedor);

        img_infoFornecedor = findViewById(R.id.img_infoFornecedor);
        textNome = findViewById(R.id.textNome2);
        textCnpj = findViewById(R.id.textCnpj2);
        textTel = findViewById(R.id.textTel2);
        textEndereco = findViewById(R.id.textEnd2);
        textEmail = findViewById(R.id.textEmail2);

        Intent intent = getIntent();
        Fornecedor fornecedor = new Fornecedor();
        fornecedor = recebeForncedor(intent.getStringExtra("cnpj"));

        atualizaFornecedor = findViewById(R.id.btn_atualizarFornecedor2);
        excluiFornecedor = findViewById(R.id.btn_excluirFornecedor2);
        adicionarPedidofornecedor = findViewById(R.id.btn_adicionarPedidoFornecedor);

        img_infoFornecedor.setImageResource(R.drawable.building_foreground);
        textNome.setText(fornecedor.getNome());
        textCnpj.setText(fornecedor.getCnpj());
        textTel.setText(fornecedor.getTelefone());
        textEndereco.setText(fornecedor.getEndereco());
        textEmail.setText(fornecedor.getEmail());

        Fornecedor finalFornecedor = fornecedor;
        excluiFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaFornecedor(finalFornecedor.getCnpj());
                Intent reload = new Intent(InfoFornecedorActivity.this, MenuActivity.class);
                finish();
                startActivity(reload);
            }
        });
        atualizaFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaFornecedor(finalFornecedor.getCnpj());
                Intent intent = new Intent(InfoFornecedorActivity.this, RegisterActivity_Fornecedor.class);
                finish();
                startActivity(intent);

            }
        });
        adicionarPedidofornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(InfoFornecedorActivity.this, RegisterActivity_Fornecedor.class);
                //startActivity(intent);
                // cria um novo pedio de um fornedor e atualiza os dados do produto
            }
        });
    }

    public Fornecedor recebeForncedor(String intent){

        System.out.println(intent);
        DAO dao = new DAO(context);
        Fornecedor fornecedor = new Fornecedor();
        fornecedor = dao.buscaUmFornecedor(intent);

        return fornecedor;
    }
}