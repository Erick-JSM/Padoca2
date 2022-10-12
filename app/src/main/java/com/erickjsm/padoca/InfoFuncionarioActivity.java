package com.erickjsm.padoca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.R;
import com.erickjsm.padoca.obj.Cliente;
import com.erickjsm.padoca.obj.Fornecedor;
import com.erickjsm.padoca.obj.Funcionario;

public class InfoFuncionarioActivity extends AppCompatActivity {

    Button atualizarFuncionario;
    Button excluirFuncionario;
    Button preparaProduto;
    ImageView img_infoFuncionario;

    TextView textPNome;
    TextView textMinimal;
    TextView textUNome;
    TextView textCpf;
    TextView textDataNasc;
    TextView textTel;
    TextView textEndereco;
    TextView textSexo;
    TextView textCargo;
    TextView textSalario;

    Switch swith_Cad_FuncSexo;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_funcionario);

        img_infoFuncionario = findViewById(R.id.img_infoFuncionario);
        textPNome = findViewById(R.id.textPNome2);
        textMinimal = findViewById(R.id.textMinimal2);
        textUNome = findViewById(R.id.textUNome2);
        textCpf = findViewById(R.id.textCpf2);
        textDataNasc = findViewById(R.id.textDataNasc2);
        textTel = findViewById(R.id.textTel2);
        textEndereco = findViewById(R.id.textEndereco2);
        textSexo = findViewById(R.id.textSexo2);
        textCargo = findViewById(R.id.textCargo2);
        textSalario = findViewById(R.id.textSalario2);
        swith_Cad_FuncSexo = findViewById(R.id.swith_Cad_FuncSexo);

        atualizarFuncionario = findViewById(R.id.btn_atualizarFuncionario);
        excluirFuncionario = findViewById(R.id.btn_excluirFuncionario);
        preparaProduto = findViewById(R.id.btn_prepararFuncionario);

        Intent intent = getIntent();
        Funcionario funcionario = new Funcionario();
        funcionario = recebeFuncionario(intent.getStringExtra("cpf"));

        if (funcionario.getCargo().equals("Caixa")) {
            if (funcionario.getSexo().equals("F")) {
                img_infoFuncionario.setImageResource(R.drawable.clerk_women_foreground);
            } else {
                img_infoFuncionario.setImageResource(R.drawable.clerk_men_foreground);
            }
        }
        else {
            if (funcionario.getSexo().equals("F")) {
                img_infoFuncionario.setImageResource(R.drawable.chef_women_foreground);
            } else {
                img_infoFuncionario.setImageResource(R.drawable.chef_women_foreground);
            }
        }
        textPNome.setText(funcionario.getpNome());
        textMinimal.setText(funcionario.getMinimal());
        textUNome.setText(funcionario.getuNome());
        textCpf.setText(funcionario.getCPF());
        textDataNasc.setText(funcionario.getDataNasc());
        textTel.setText(funcionario.getTelefone());
        textEndereco.setText(funcionario.getEndereco());
        textSexo.setText(funcionario.getSexo());
        textSalario.setText(funcionario.getSalario());
        textCargo.setText(funcionario.getCargo());

        Funcionario finalFuncionario = funcionario;
        excluirFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaFuncionario(finalFuncionario.getCPF());
                Intent reload = new Intent(InfoFuncionarioActivity.this, MenuActivity.class);
                finish();
                startActivity(reload);
            }
        });
        atualizarFuncionario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO dao = new DAO(context);
                dao.apagaFuncionario(finalFuncionario.getCPF());
                Intent intent = new Intent(InfoFuncionarioActivity.this, RegisterActivity_Funcionarios.class);
                finish();
                startActivity(intent);

            }
        });
        preparaProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(InfoFornecedorActivity.this, RegisterActivity_Fornecedor.class);
                //startActivity(intent);
                // cria um pop up para implementcao dos dados e adcionar na tabela;
            }
        });
    }

    public Funcionario recebeFuncionario(String intent){

        System.out.println(intent);
        DAO dao = new DAO(context);
        Funcionario funcionario = new Funcionario();
        funcionario = dao.buscaUmFuncionario(intent);

        return funcionario;
    }
}