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
import com.erickjsm.padoca.obj.Pedido;

public class InfoPedidoActivity extends AppCompatActivity {

    Button excluirPedido;
    Button atualizarPedido;
    ImageView img_infoPedido;

    TextView textCodPedido;
    TextView textNomeFunc;
    TextView textNomeCliente;
    TextView textCodProduto;
    TextView textQtdProduto;
    TextView textDataPedido;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pedido);

        img_infoPedido = findViewById(R.id.img_infoPedido);
        textCodPedido = findViewById(R.id.textCodPedido2);
        textDataPedido = findViewById(R.id.textData);
        textNomeCliente = findViewById(R.id.textNomeCliente2);
        textNomeFunc = findViewById(R.id.textNomeFunc2);
        textCodProduto = findViewById(R.id.textCodProd2);
        textQtdProduto = findViewById(R.id.textQtdProd2);

        excluirPedido = findViewById(R.id.btn_excluirPedido);
        atualizarPedido = findViewById(R.id.btn_atualizarPedido);

        Intent intent = getIntent();
        Pedido pedido = new Pedido();
        pedido = recebePedido(intent.getStringExtra("codPedido"));

        img_infoPedido.setImageResource(R.drawable.payment_foreground);
        textCodPedido.setText(pedido.getCodPedido());
        textNomeFunc.setText(pedido.getCpfFuncionario());
        textNomeCliente.setText(pedido.getCpfCliente());
        textCodProduto.setText(pedido.getCodProduto());
        textDataPedido.setText((pedido.getData()));

        Pedido finalPedido = pedido;
        excluirPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaPedido(finalPedido.getCodPedido());
                Intent reload = new Intent(InfoPedidoActivity.this, MenuActivity.class);
                finish();
                startActivity(reload);
            }
        });
        atualizarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaPedido(finalPedido.getCodPedido());
                Intent intent = new Intent(InfoPedidoActivity.this, RegisterActivity_Pedido.class);
                startActivity(intent);

            }
        });
    }

    public Pedido recebePedido(String intent){
        System.out.println(intent);
        DAO dao = new DAO(context);
        Pedido pedido = new Pedido();
        pedido = dao.buscaUmPedido(intent);
        System.out.println(pedido);

        return pedido;
    }
}