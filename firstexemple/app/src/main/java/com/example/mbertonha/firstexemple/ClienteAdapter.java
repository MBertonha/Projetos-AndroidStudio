package com.example.mbertonha.firstexemple;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mbertonha.firstexemple.dominio.entidades.Cliente;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by MBertonha on 24/10/2017.
 */

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {

    private List<Cliente> dados;                 //Objeto responsavel por armazenar os dados do projeto

    public ClienteAdapter(List<Cliente> dados){       //contrutor que recebe a fonte de dados
        this.dados = dados;
    }

    //classe que recupera as referencias do layout
    public class ViewHolderCliente extends RecyclerView.ViewHolder{
        public TextView txtNome;
        public TextView txtTelefone;
        public TextView txtEndereco;
        public TextView txtEmail;
        public ViewHolderCliente(View itemView) {
            super(itemView);
            txtNome     = (TextView) itemView.findViewById(R.id.txtNome);
            txtTelefone = (TextView) itemView.findViewById(R.id.txtTelefone);
            txtEndereco = (TextView) itemView.findViewById(R.id.txtEndereco);
            txtEmail    = (TextView) itemView.findViewById(R.id.txtEmail);
        }
    }

    @Override
    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); //referencia da classe layoutInflater
        View view = layoutInflater.inflate(R.layout.linha_clientes, parent, false); //referencia da view , layout de cada linha
        ViewHolderCliente holderCliente = new ViewHolderCliente(view);  //objeto do tipo viewHolderCliente
        return holderCliente;
    }

    @Override //preencher os dados dalista de dados
    public void onBindViewHolder(ClienteAdapter.ViewHolderCliente holder, int position) {
        if ((dados != null) && (dados.size() > 0 )){  //testando se ja tem algum dado passado e se possui um valor armazenado
            Cliente  cliente = dados.get(position);

            holder.txtNome.setText(cliente.nome);
            holder.txtTelefone.setText(cliente.telefone);
            holder.txtEndereco.setText(cliente.endereco);
            holder.txtEmail.setText(cliente.email);
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
