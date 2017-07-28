package pesagem.ovinospanorama.com.pesagemcordeiros.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import pesagem.ovinospanorama.com.pesagemcordeiros.R;
import pesagem.ovinospanorama.com.pesagemcordeiros.adapter.PesagemListAdapter;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagem;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagens;


public class TabListaPesagensFragment extends Fragment {

    private ListView listaPesagensListView;

    private Pesagens pesagens;
    private PesagemListAdapter listaPesagensListViewArrayAdapter;


    public Pesagens getPesagens(){
        return pesagens;
    }

    public PesagemListAdapter getListaPesagensListViewArrayAdapter(){
        return listaPesagensListViewArrayAdapter;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View view = (LinearLayout) inflater.inflate(R.layout.tab_lista_pesagens, container, false);

        pesagens = Pesagens.getPesagens(Pesagens.PesagensType.PESAGEM);
        listaPesagensListViewArrayAdapter = new PesagemListAdapter(this.getContext(), pesagens);
        listaPesagensListView = (ListView) view.findViewById(R.id.listaPesos);
        listaPesagensListView.setAdapter(listaPesagensListViewArrayAdapter);
        return view;
    }


    @Override
    public void onResume(){
        super.onResume();
        listaPesagensListViewArrayAdapter.notifyDataSetChanged();
    }
}
