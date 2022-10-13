package com.erickjsm.padoca.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erickjsm.padoca.DAO.DAO;
import com.erickjsm.padoca.InfoClienteActivity;
import com.erickjsm.padoca.InfoFornecedorActivity;
import com.erickjsm.padoca.InfoFuncionarioActivity;
import com.erickjsm.padoca.InfoPedidoActivity;
import com.erickjsm.padoca.InfoProdutoActivity;
import com.erickjsm.padoca.R;
import com.erickjsm.padoca.obj.Cliente;

import java.text.BreakIterator;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    
    Context context;
    String[] pNomes;
    String[] uNomes;

    String[] nomes;
    String[] minimal;
    String[] keys;
    String[] cargo;
    String[] sexo;
    String[] data;

    int opcao;

    View viewOnCreate;
    ViewHolder viewHolderLocal;

// Adpter funcionario ===================================================================================================================================
    public RecycleViewAdapter(Context contextRecebido, String[] nomesRecebidos, String[] keysRecebidas, String[] uNomesRecebidos, String[] minimalRecebidos, String[] sexoRecebida, String[] cargoRecebido, String[] dataRecebida, int ops){
        context = contextRecebido;
        uNomes = uNomesRecebidos;
        nomes = nomesRecebidos;
        minimal = minimalRecebidos;
        keys = keysRecebidas;
        sexo = sexoRecebida;
        cargo = cargoRecebido;
        data = dataRecebida;
        opcao = ops;
    }
    
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView textMinimal;
        public TextView textuNome;
        public TextView textNome;
        public TextView textKey;
        public ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNomeAdapter);
            textuNome = itemView.findViewById(R.id.textUNomeAdapter);
            textMinimal = itemView.findViewById(R.id.textMinimalAdapter);
            textKey = itemView.findViewById(R.id.textKeyAdapter);
            icon = itemView.findViewById(R.id.iconAdapter);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }

        @Override
        public void onClick(View v) {

        }
    }
    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.activity_recycle_view, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        if(opcao == 2) {
            holder.textNome.setText(nomes[position]);
            holder.textMinimal.setText(minimal[position]);
            holder.textuNome.setText(uNomes[position]);
            holder.textKey.setText(keys[position]);

            if(cargo[position].equals("Caixa")) {
                if (sexo[position].equals("F")) {
                    holder.icon.setImageResource(R.drawable.clerk_women_foreground);
                } else {
                    holder.icon.setImageResource(R.drawable.clerk_men_foreground);
                }
            }
            else{
                if (sexo[position].equals("F")) {
                    holder.icon.setImageResource(R.drawable.chef_women_foreground);
                } else {
                    holder.icon.setImageResource(R.drawable.chef_men_foreground);
                }
            }
            viewOnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InfoFuncionarioActivity.class);
                    intent.putExtra("cpf", keys[position]);
                    v.getContext().startActivity(intent);
                }
            });
        }
        //==============================================================================
        if(opcao == 3) {
            holder.textNome.setText(nomes[position]);
            holder.textKey.setText(keys[position]);
            holder.icon.setImageResource(R.drawable.building_foreground);

            viewOnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InfoFornecedorActivity.class);
                    intent.putExtra("cnpj", keys[position]);
                    v.getContext().startActivity(intent);
                }
            });
        }
        //==============================================================================
        if(opcao == 4) {
            holder.textNome.setText(nomes[position]);
            holder.textKey.setText(keys[position]);
            holder.icon.setImageResource(R.drawable.baguette_foreground);

            viewOnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InfoProdutoActivity.class);
                    intent.putExtra("codProduto", keys[position]);
                    v.getContext().startActivity(intent);
                }
            });
        }
        //==============================================================================
        if(opcao == 1) {
            holder.textNome.setText(nomes[position]);
            holder.textMinimal.setText(minimal[position]);
            holder.textuNome.setText(uNomes[position]);
            holder.textKey.setText(keys[position]);

            if (sexo[position].equals("F")) {
                holder.icon.setImageResource(R.drawable.client_women_foreground);
            } else {
                holder.icon.setImageResource(R.drawable.client_men_foreground);
            }
            viewOnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InfoClienteActivity.class);
                    intent.putExtra("cpf", keys[position]);
                    v.getContext().startActivity(intent);
                }
            });
        }
        //==============================================================================
        if(opcao == 5) {
            holder.textNome.setText(nomes[position]);
            holder.textKey.setText(data[position]);
            holder.icon.setImageResource(R.drawable.payment_foreground);

            viewOnCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, InfoPedidoActivity.class);
                    intent.putExtra("codPedido", nomes[position]);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return nomes.length;
    }
}
