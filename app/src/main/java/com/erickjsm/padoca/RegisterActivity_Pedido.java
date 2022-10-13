package com.erickjsm.padoca;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Cliente;
import com.erickjsm.padoca.obj.Pedido;

import java.util.Random;

public class RegisterActivity_Pedido extends AppCompatActivity {

    Button btn_criarPedido;
    EditText etxt_Cad_PedidoCpfFuncionario;
    EditText etxt_Cad_PedidoQtd;
    EditText etxt_Cad_PedidoData;
    EditText etxt_Cad_PedidoCod;
    EditText etxt_Cad_ProduroCode;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pedido);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        context = getApplicationContext();

        btn_criarPedido = findViewById(R.id.btn_criarPedido);
        etxt_Cad_PedidoCod = findViewById(R.id.etxt_Cad_ProdutoCod);
        etxt_Cad_PedidoData = findViewById(R.id.etxt_Cad_ProdutoValidadeI);
        etxt_Cad_PedidoQtd = findViewById(R.id.etxt_Cad_ProdutoValidadeF);
        etxt_Cad_PedidoCpfFuncionario = findViewById(R.id.etxt_Cad_ProdutoValor);
        etxt_Cad_ProduroCode = findViewById(R.id.etxt_Cad_ProduroCode);

        btn_criarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etxt_Cad_PedidoCod.getText().toString().equals("") || etxt_Cad_PedidoQtd.getText().toString().equals("") || etxt_Cad_PedidoCpfFuncionario.getText().toString().equals(""))) {
                    DAO dao = new DAO(getApplicationContext());
                    Pedido pedido = new Pedido();
                    pedido.setCodPedido(String.valueOf(etxt_Cad_PedidoCod.getText().toString()));
                    pedido.setQtdPedida(String.valueOf(etxt_Cad_PedidoQtd.getText().toString()));
                    pedido.setData(String.valueOf(etxt_Cad_PedidoData.getText().toString()));
                    pedido.setCpfFuncionario(String.valueOf(etxt_Cad_PedidoCpfFuncionario.getText().toString()));
                    pedido.setCodProduto(etxt_Cad_ProduroCode.getText().toString());

                    Intent intent = getIntent();
                    Cliente cliente = new Cliente();
                    cliente = recebeCliente(intent.getStringExtra("cpf"));

                    Random rand = new Random();
                    String cpf;
                    if(rand.nextBoolean()){
                        cpf = "22233344402";
                    }
                    else{
                        cpf = "22233344403";
                    }
                    dao.insertPedido(pedido);
                    dao.insertFaz_pedido(cliente.getCPF(), pedido.getCodPedido());
                    dao.inserTem_produtos(pedido.getCodPedido(), pedido.getCodProduto());
                }
                else{
                    Toast.makeText(getApplicationContext(), "Os campos Codigo do pedido, Quantidade e CPF do Funcionario são obrigatorios!", Toast.LENGTH_SHORT).show();
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

    private Cliente recebeCliente(String cpf) {
        DAO dao = new DAO(context);
        Cliente cliente1 = new Cliente();
        cliente1 = dao.buscaUmCliente(cpf);
        System.out.println(cliente1);

        return cliente1;
    }
}