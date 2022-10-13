package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.adapter.RecycleViewAdapter;
import com.erickjsm.padoca.adapter.RecycleViewCompra;
import com.erickjsm.padoca.obj.Produto;

import java.util.ArrayList;
import java.util.List;

public class BuscaProduroCompraRecyclerVwActivity extends AppCompatActivity {

    Button btn_comparaProdutos;
    RecyclerView recyclerVw;
    Context context;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManeger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_produro_compra_recycler_vw);
        context = getApplicationContext();

        recyclerVw = findViewById(R.id.recyclerVwCompraProd);
        btn_comparaProdutos = findViewById(R.id.btn_comparProdutos);
        buscaBanco();

        btn_comparaProdutos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }

    private void buscaBanco(){
        DAO dao2 = new DAO(context);

        List<Produto> produtos = dao2.buscaProduto();
        List<String> valor = new ArrayList<String>();
        List<String> nomes = new ArrayList<>();

        String[] dados_nomes = new String[] {};
        String[] dados_valor = new String[] {};

        for(Produto produtoBuscado: produtos){
            nomes.add(produtoBuscado.getNome());
            //valor.add(produtoBuscado.getValor());
        }

        dados_nomes = nomes.toArray(new String[0]);
        //dados_valor = valor.toArray(new String[0]);

        recyclerViewLayoutManeger = new LinearLayoutManager(context);
        recyclerVw.setLayoutManager(recyclerViewLayoutManeger);
        recyclerViewAdapter = new RecycleViewCompra(context, dados_nomes);

        recyclerVw.setAdapter(recyclerViewAdapter);
        Intent intent = new Intent(context, RecycleViewCompra.class);
    }
}