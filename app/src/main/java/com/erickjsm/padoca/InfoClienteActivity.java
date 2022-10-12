package com.erickjsm.padoca;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Cliente;
import com.erickjsm.padoca.obj.Pedido;

public class InfoClienteActivity extends AppCompatActivity {

    Button atualizarCliente;
    Button excluirCliente;
    Button mudarDividaCliente;
    Button addPedido;
    ImageView img_infoCliente;

    TextView textPNome;
    TextView textMinimal;
    TextView textUNome;
    TextView textCpf;
    TextView textDataNasc;
    TextView textTel;
    TextView textEndereco;
    TextView textSexo;
    TextView textDivida;
    TextView textUValorPago;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_cliente);

        img_infoCliente = findViewById(R.id.img_infoCliente);
        textPNome = findViewById(R.id.textPNome2);
        textMinimal = findViewById(R.id.textMinimal2);
        textUNome = findViewById(R.id.textUNome2);
        textCpf = findViewById(R.id.textCpf2);
        textDataNasc = findViewById(R.id.textDataNasc2);
        textTel = findViewById(R.id.textTel2);
        textEndereco = findViewById(R.id.textEndereco2);
        textSexo = findViewById(R.id.textSexo2);
        textDivida = findViewById(R.id.textDivida2);
        textUValorPago = findViewById(R.id.textUValorPago2);

        excluirCliente = findViewById(R.id.btn_excluirCliente);
        atualizarCliente = findViewById(R.id.btn_atualizarCliente);
        mudarDividaCliente = findViewById(R.id.btn_mudarDividaCliente);
        addPedido = findViewById(R.id.btn_addPedido);

        Intent intent = getIntent();
        Cliente cliente = new Cliente();
        cliente = recebeCliente(intent.getStringExtra("cpf"));

        if (cliente.getSexo().equals("F")) {
            img_infoCliente.setImageResource(R.drawable.client_women_foreground);
        } else {
            img_infoCliente.setImageResource(R.drawable.client_men_foreground);
        }

        textPNome.setText(cliente.getpNome());
        textMinimal.setText(cliente.getMinimal());
        textUNome.setText(cliente.getuNome());
        textCpf.setText(cliente.getCPF());
        textDataNasc.setText(cliente.getDataNasc());
        textTel.setText(cliente.getTelefone());
        textEndereco.setText(cliente.getEndereco());
        textSexo.setText(cliente.getSexo());
        textDivida.setText(cliente.getDivida());
        textUValorPago.setText(cliente.getValorPago());

        Cliente finalCliente = cliente;
        excluirCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaPessoa(finalCliente.getCPF());
                Intent reload = new Intent(InfoClienteActivity.this, MenuActivity.class);
                finish();
                startActivity(reload);
            }
        });
        atualizarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaPessoa(finalCliente.getCPF());
                Intent intent = new Intent(InfoClienteActivity.this, RegisterActivity_Cliente.class);
                startActivity(intent);

            }
        });
        mudarDividaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlteraDividaActivity.class);
                intent.putExtra("cpf", finalCliente.getCPF());
                v.getContext().startActivity(intent);
            }
        });
        addPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // crai uma activity com uma lista de produtos.
            }
        });
    }

    public Cliente recebeCliente(String intent){

        System.out.println(intent);
        DAO dao = new DAO(context);
        Cliente cliente = new Cliente();
        cliente = dao.buscaUmCliente(intent);
        System.out.println(cliente);

        return cliente;
    }
}