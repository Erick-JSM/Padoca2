package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.obj.Cliente;

public class AlteraDividaActivity extends AppCompatActivity {

    Button btn_somar;
    Button btn_subtrarir;
    TextView etxt_incrementoDivida;

    Double valor;

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_divida);

        btn_somar = findViewById(R.id.btn_somar);
        btn_subtrarir = findViewById(R.id.btn_subtrair);
        etxt_incrementoDivida = findViewById(R.id.etxt_incrementoDivida);

        Intent intent = getIntent();
        Cliente cliente = new Cliente();
        cliente = recebeCliente(intent.getStringExtra("cpf"));

        Cliente finalCliente = cliente;
        btn_somar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                valor = (Double.parseDouble(String.valueOf(etxt_incrementoDivida.getText())) + Double.parseDouble(finalCliente.getDivida()));
                dao.atualizaDivida(valor, finalCliente.getCPF(), 0.0);
            }
        });

        btn_subtrarir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                System.out.println(finalCliente.getDivida());
                System.out.println(Double.parseDouble(String.valueOf(etxt_incrementoDivida.getText())));
                valor = Double.parseDouble(finalCliente.getDivida())-(Double.parseDouble(String.valueOf(etxt_incrementoDivida.getText())));
                dao.atualizaDivida(valor, finalCliente.getCPF(), (Double.parseDouble(String.valueOf(etxt_incrementoDivida.getText()))));
            }
        });
    }

    private Cliente recebeCliente(String cpf) {
        DAO dao = new DAO(context);
        Cliente cliente1 = new Cliente();
        cliente1 = dao.buscaUmCliente(cpf);
        System.out.println(cliente1);

        return cliente1;
    }
}