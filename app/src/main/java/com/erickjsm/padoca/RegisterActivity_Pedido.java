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
import com.erickjsm.padoca.obj.Pedido;

public class RegisterActivity_Pedido extends AppCompatActivity {

    Button btn_criarPedido;
    EditText etxt_Cad_PedidoCpfFuncionario;
    EditText etxt_Cad_PedidoQtd;
    EditText etxt_Cad_PedidoData;
    EditText etxt_Cad_PedidoCod;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pedido);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        btn_criarPedido = findViewById(R.id.btn_criarPedido);
        etxt_Cad_PedidoCod = findViewById(R.id.etxt_Cad_ProdutoCod);
        etxt_Cad_PedidoData = findViewById(R.id.etxt_Cad_ProdutoValidadeI);
        etxt_Cad_PedidoQtd = findViewById(R.id.etxt_Cad_ProdutoValidadeF);
        etxt_Cad_PedidoCpfFuncionario = findViewById(R.id.etxt_Cad_ProdutoValor);

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

                    dao.insertPedido(pedido);
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
    public Pedido atuPedido(Pedido pedido){
        return pedido;
    }
}