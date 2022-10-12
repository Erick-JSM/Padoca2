package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.adapter.RecycleViewAdapter;
import com.erickjsm.padoca.obj.Cliente;
import com.erickjsm.padoca.obj.Fornecedor;
import com.erickjsm.padoca.obj.Funcionario;
import com.erickjsm.padoca.obj.Pedido;
import com.erickjsm.padoca.obj.Produto;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    Button btn_vendas;
    Button btn_produtos;
    Button btn_clientes;
    Button btn_empresas;
    Button btn_funcionarios;
    Button btn_criar;


    RecyclerView recyclerView_pesquisa;
    Context context;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManeger;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        context = getApplicationContext();

        btn_vendas = findViewById(R.id.btn_vendas);
        btn_produtos = findViewById(R.id.btn_Produtos);
        btn_clientes = findViewById(R.id.btn_clientes);
        btn_empresas = findViewById(R.id.btn_empresas);
        btn_funcionarios = findViewById(R.id.btn_funcionarios);
        btn_criar = findViewById(R.id.btn_criar);
        recyclerView_pesquisa = findViewById(R.id.recycleVw_pesquisa);

        btn_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    BuscaNoBanco(1);
            }
        });

        btn_vendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    BuscaNoBanco(5);
            }
        });

        btn_empresas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    BuscaNoBanco(3);
            }
        });

        btn_funcionarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    BuscaNoBanco(2);
            }
        });

        btn_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuscaNoBanco(4);
            }
        });

        btn_criar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tela_menuCriar = new Intent(MenuActivity.this, MenuCadastroActivity.class);
                finish();
                startActivity(tela_menuCriar);
            }
        });


    }
// Lista de produtos ===============================================================================================================
    private void BuscaNoBanco(int ops) {

        DAO dao2 = new DAO(getApplicationContext());
        if(ops == 1) {
            List<Cliente> clientes = dao2.buscaCliente();
            List<String> keys = new ArrayList<String>();
            List<String> pNomes = new ArrayList<>();
            List<String> uNomes = new ArrayList<>();
            List<String> minimal = new ArrayList<>();
            List<String> sexo = new ArrayList<>();

            String[] dados_pNomes = new String[] {};
            String[] dados_uNomes = new String[] {};
            String[] dados_minimal = new String[] {};
            String[] dados_sexo = new String[] {};
            String[] dados_keys = new String[] {};


            for(Cliente clienteBuscado: clientes){
                pNomes.add(clienteBuscado.getpNome());
                uNomes.add(clienteBuscado.getuNome());
                minimal.add(clienteBuscado.getMinimal());
                keys.add(String.valueOf(clienteBuscado.getCPF()));
                sexo.add(clienteBuscado.getSexo());
            }

            dados_pNomes = pNomes.toArray(new String[0]);
            dados_uNomes = uNomes.toArray(new String[0]);
            dados_minimal = minimal.toArray(new String[0]);
            dados_keys = keys.toArray(new String[0]);
            dados_sexo = sexo.toArray(new String[0]);


            recyclerViewLayoutManeger = new LinearLayoutManager(context);
            recyclerView_pesquisa.setLayoutManager(recyclerViewLayoutManeger);
            recyclerViewAdapter = new RecycleViewAdapter(context, dados_pNomes, dados_keys, dados_uNomes, dados_minimal, dados_sexo, null,null,  1);

            recyclerView_pesquisa.setAdapter(recyclerViewAdapter);
        }

        //==================----\-\----======================
        else if(ops == 2){
            List<Funcionario> funcionarios = dao2.buscaFuncionario();
            List<String> keys = new ArrayList<String>();
            List<String> pNomes = new ArrayList<>();
            List<String> uNomes = new ArrayList<>();
            List<String> minimal = new ArrayList<>();
            List<String> sexo = new ArrayList<>();
            List<String> cargo = new ArrayList<>();

            String[] dados_pNomes = new String[] {};
            String[] dados_uNomes = new String[] {};
            String[] dados_minimal = new String[] {};
            String[] dados_sexo = new String[] {};
            String[] dados_cargo = new String[] {};
            String[] dados_keys = new String[] {};

            for(Funcionario funcionarioBuscado: funcionarios){
                pNomes.add(funcionarioBuscado.getpNome());
                uNomes.add(funcionarioBuscado.getuNome());
                minimal.add(funcionarioBuscado.getMinimal());
                keys.add(String.valueOf(funcionarioBuscado.getCPF()));
                sexo.add(funcionarioBuscado.getSexo());
                cargo.add(funcionarioBuscado.getCargo());
            }

            dados_pNomes = pNomes.toArray(new String[0]);
            dados_uNomes = uNomes.toArray(new String[0]);
            dados_minimal = minimal.toArray(new String[0]);
            dados_keys = keys.toArray(new String[0]);
            dados_cargo = cargo.toArray(new String[0]);
            dados_sexo = sexo.toArray(new String[0]);

            recyclerViewLayoutManeger = new LinearLayoutManager(context);
            recyclerView_pesquisa.setLayoutManager(recyclerViewLayoutManeger);
            recyclerViewAdapter = new RecycleViewAdapter(context, dados_pNomes, dados_keys, dados_uNomes, dados_minimal, dados_sexo, dados_cargo,null,  2);

            recyclerView_pesquisa.setAdapter(recyclerViewAdapter);
        }

        //==================----\-\----======================
        else if(ops == 3){
            List<Fornecedor> fornecedores = dao2.buscaFornecedor();
            List<String> keys = new ArrayList<String>();
            List<String> nomes = new ArrayList<>();

            String[] dados_nomes = new String[] {};
            String[] dados_keys = new String[] {};

            for(Fornecedor fornecedorBuscado: fornecedores){
                nomes.add(fornecedorBuscado.getNome());
                keys.add(String.valueOf(fornecedorBuscado.getCnpj()));
            }

            dados_nomes = nomes.toArray(new String[0]);
            dados_keys = keys.toArray(new String[0]);

            recyclerViewLayoutManeger = new LinearLayoutManager(context);
            recyclerView_pesquisa.setLayoutManager(recyclerViewLayoutManeger);
            recyclerViewAdapter = new RecycleViewAdapter(context, dados_nomes, dados_keys, null, null, null, null,null,  3);

            recyclerView_pesquisa.setAdapter(recyclerViewAdapter);
        }

        //==================----\-\----======================
        else if(ops == 4){
            List<Produto> produtos = dao2.buscaProduto();
            List<String> keys = new ArrayList<String>();
            List<String> nomes = new ArrayList<>();

            String[] dados_nomes = new String[] {};
            String[] dados_keys = new String[] {};

            for(Produto produtoBuscado: produtos){
                nomes.add(produtoBuscado.getNome());
                keys.add(String.valueOf(produtoBuscado.getCodProduto()));
            }

            dados_nomes = nomes.toArray(new String[0]);
            dados_keys = keys.toArray(new String[0]);

            recyclerViewLayoutManeger = new LinearLayoutManager(context);
            recyclerView_pesquisa.setLayoutManager(recyclerViewLayoutManeger);
            recyclerViewAdapter = new RecycleViewAdapter(context, dados_nomes, dados_keys, null, null, null, null,null,  4);

            recyclerView_pesquisa.setAdapter(recyclerViewAdapter);
        }

        //==================----\-\----======================
        else{
            List<Pedido> pedidos = dao2.buscaPedido();
            List<String> keys = new ArrayList<String>();
            List<String> data = new ArrayList<>();

            String[] dados_data = new String[] {};
            String[] dados_keys = new String[] {};

            for(Pedido pedidoBuscado: pedidos){
                data.add(pedidoBuscado.getData());
                keys.add(String.valueOf(pedidoBuscado.getCodPedido()));
                System.out.println(keys);
            }

            dados_data = data.toArray(new String[0]);
            dados_keys = keys.toArray(new String[0]);

            recyclerViewLayoutManeger = new LinearLayoutManager(context);
            recyclerView_pesquisa.setLayoutManager(recyclerViewLayoutManeger);
            recyclerViewAdapter = new RecycleViewAdapter(context, dados_keys,null, null, null, null, null, dados_data, 5);

            recyclerView_pesquisa.setAdapter(recyclerViewAdapter);
        }
    }
}