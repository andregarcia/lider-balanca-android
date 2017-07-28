package pesagem.ovinospanorama.com.pesagemcordeiros.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pesagem.ovinospanorama.com.pesagemcordeiros.R;
import pesagem.ovinospanorama.com.pesagemcordeiros.activity.PesagemObsActivity;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagem;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagens;




public class PesagemListAdapter extends BaseAdapter {

    private Pesagens pesagens;

    private Context context;

    private ObjectMapper objectMapper;

    public PesagemListAdapter(Context context, Pesagens pesagens){
        this.context = context;
        this.pesagens = pesagens;
        objectMapper = new ObjectMapper();
    }

    public void addItem(Pesagem pesagem){
        this.pesagens.addPesagem(pesagem);
    }

    @Override
    public int getCount() {
        return pesagens.getSize();
    }

    @Override
    public Object getItem(int i) {
        return pesagens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return pesagens.get(i).getNumero();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_lista_pesagens, null);
        }

        //Handle TextView and display string from your list
        final Pesagem pesagem = pesagens.get(i);
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(pesagem.toString());
        listItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PesagemObsActivity.class);
                Bundle b = new Bundle();
                try {
                    b.putString("pesagem", objectMapper.writeValueAsString(pesagem));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                intent.putExtras(b);
                notifyDataSetChanged();
                view.getContext().startActivity(intent);
                notifyDataSetChanged();
            }
        });

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);

        final int position = i;
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                pesagens.removeAndUpdateNumber(position); //or some other task
                notifyDataSetChanged();
            }
        });
        notifyDataSetChanged();
        return view;
    }
}
