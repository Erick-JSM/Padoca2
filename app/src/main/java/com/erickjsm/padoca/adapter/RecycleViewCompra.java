package com.erickjsm.padoca.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickjsm.padoca.InfoFuncionarioActivity;
import com.erickjsm.padoca.InfoProdutoActivity;
import com.erickjsm.padoca.R;

import java.text.BreakIterator;

public class RecycleViewCompra extends RecyclerView.Adapter<RecycleViewCompra.ViewHolder> {

    String[] codProd;

    View viewOnCreate;
    ViewHolder viewHolderLocal;
    Context context;

    public RecycleViewCompra(Context contextRecebido, String[] codProdRecebido){
        context = contextRecebido;
        codProd = codProdRecebido;
        //cpfCliente = cpfClienteRecebido;

    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public EditText etxt_numProd;
        public TextView nomeProd;
        public CheckBox checkBoxS_N;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etxt_numProd = itemView.findViewById(R.id.etxt_numProd);
            nomeProd = itemView.findViewById(R.id.txt_nomeProd);
            checkBoxS_N = itemView.findViewById(R.id.checkBox_compraS_N);
            img = itemView.findViewById(R.id.img);

            if(checkBoxS_N.isChecked()) {
                etxt_numProd.getText().toString();
            }
        }
        @Override
        public void onClick(View v) {
            
        }
    }
    
    @NonNull
    @Override
    public RecycleViewCompra.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewOnCreate = LayoutInflater.from(context).inflate(R.layout.activity_recycle_view_compra, parent, false);
        viewHolderLocal = new ViewHolder(viewOnCreate);
        return viewHolderLocal;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewCompra.ViewHolder holder, @SuppressLint("RecyclerView")int position) {
        System.out.println();
        holder.nomeProd.setText(codProd[position]);
        holder.img.setImageResource(R.drawable.baguette_foreground);
    }

    @Override
    public int getItemCount() {
        return codProd.length;
    }
}