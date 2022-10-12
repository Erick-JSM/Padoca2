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
import com.erickjsm.padoca.obj.Cliente;
import com.erickjsm.padoca.obj.Pedido;
import com.erickjsm.padoca.obj.Produto;

public class InfoProdutoActivity extends AppCompatActivity {

    Button atualizarProduto;
    Button excluirProduto;
    ImageView img_infoProduto;

    TextView textNome;
    TextView textCodigo;
    TextView textVencimentoI;
    TextView textVencimentoF;
    TextView textPreco;
    TextView textQtd;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_produto);

        img_infoProduto = findViewById(R.id.img_infoProduto);
        textNome = findViewById(R.id.textNome2);
        textCodigo = findViewById(R.id.textCodigo2);
        textVencimentoI = findViewById(R.id.textVencimentoI2);
        textVencimentoF = findViewById(R.id.textVencimentoF2);
        textPreco = findViewById(R.id.textPreco2);
        textQtd = findViewById(R.id.textQtdEstoque2);

        excluirProduto = findViewById(R.id.btn_excluirProduto2);
        atualizarProduto = findViewById(R.id.btn_atualizarProduto2);

        Intent intent = getIntent();
        Produto produto = new Produto();
        produto = recebeProduto(intent.getStringExtra("codProduto"));

        img_infoProduto.setImageResource(R.drawable.baguette_foreground);
        textNome.setText(produto.getNome());
        textCodigo.setText(produto.getCodProduto());
        textVencimentoI.setText(produto.getFabricacao());
        textVencimentoF.setText(produto.getTermino());
        textPreco.setText(produto.getValor());
        textQtd.setText(produto.getQtdEstoque());

        Produto finalProduto = produto;
        excluirProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaProduto(finalProduto.getCodProduto());
                Intent reload = new Intent(InfoProdutoActivity.this, MenuActivity.class);
                finish();
                startActivity(reload);
            }
        });
        atualizarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaPedido(finalProduto.getCodProduto());
                Intent intent = new Intent(InfoProdutoActivity.this, RegisterActivity_Produto.class);
                startActivity(intent);
            }
        });
    }

    public Produto recebeProduto(String intent){
        System.out.println(intent);
        DAO dao = new DAO(context);
        Produto produto = new Produto();
        produto = dao.buscaUmProduto(intent);

        return produto;
    }
}