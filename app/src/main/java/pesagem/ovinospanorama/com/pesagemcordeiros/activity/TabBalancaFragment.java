package pesagem.ovinospanorama.com.pesagemcordeiros.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import pesagem.ovinospanorama.com.pesagemcordeiros.R;
import pesagem.ovinospanorama.com.pesagemcordeiros.balanca.IndicadorEventListener;
import pesagem.ovinospanorama.com.pesagemcordeiros.balanca.PesagemBalanca;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagem;


public class TabBalancaFragment extends Fragment {

    private TextView pesoTextView;

    private TextView qtdPesagensTextView;
    private TextView qtdPesagensAbateTextView;
    private TextView qtdTotalTextView;

    private PesagemBalanca pesagemBalanca;

    private Button gravarPesagemButton;
    private Button gravarPesagemAbateButton;

    private TabListaPesagensFragment tabListaPesagensFragment;
    private TabListaPesagensAbateFragment tabListaPesagensAbateFragment;

    private String qtdPesagensFormat = "%d pesagens gravadas";
    private String qtdPesagensAbateFormat = "%d pesagens abate gravadas";
    private String qtdTotalPesagensFormat = "%d total";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }
        View view = (LinearLayout) inflater.inflate(R.layout.tab_balanca_layout, container, false);

        pesoTextView = (TextView) view.findViewById(R.id.textViewPeso);
        PesoTextViewBalancaEventListener pesoTextViewBalancaEventListener = new PesoTextViewBalancaEventListener(pesoTextView);
        pesagemBalanca.addIndicadorEventListener(pesoTextViewBalancaEventListener);

        qtdPesagensTextView = (TextView) view.findViewById(R.id.textViewQtdPesagens);
        qtdPesagensAbateTextView = (TextView) view.findViewById(R.id.textViewQtdPesagensAbate);
        qtdTotalTextView = (TextView) view.findViewById(R.id.textViewQtdPesagensTotal);

        gravarPesagemButton = view.findViewById(R.id.buttonGravarPesagem);
        gravarPesagemAbateButton = view.findViewById(R.id.buttonGravarPesagemAbate);

        initializeGravarButton();
        initializeGravarAbateButton();
        return view;
    }

    public void setTabListaPesagensFragment(TabListaPesagensFragment fragment){
        this.tabListaPesagensFragment = fragment;
    }

    public void setTabListaPesagemAbateFragment(TabListaPesagensAbateFragment fragment){
        this.tabListaPesagensAbateFragment = fragment;
    }

    public void setPesagemBalanca(PesagemBalanca pesagemBalanca){
        this.pesagemBalanca = pesagemBalanca;
    }


    public void initializeGravarButton(){
        gravarPesagemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pesagemBalanca==null) {
                    throw new RuntimeException("Initialization problem, pesagemBalanca not set in activity");
                }
                Pesagem pesagem = new Pesagem(pesagemBalanca.getPeso());
                tabListaPesagensFragment.getPesagens().addPesagemAndSetNumber(pesagem);

                qtdPesagensTextView.setText(String.format(qtdPesagensFormat, tabListaPesagensFragment.getPesagens().getSize()));
                qtdTotalTextView.setText(String.format(qtdTotalPesagensFormat, tabListaPesagensAbateFragment.getPesagens().getSize() + tabListaPesagensFragment.getPesagens().getSize()));
                tabListaPesagensFragment.getListaPesagensListViewArrayAdapter().notifyDataSetChanged();
            }
        });
    }

    public void initializeGravarAbateButton(){
        gravarPesagemAbateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pesagemBalanca==null) {
                    throw new RuntimeException("Initialization problem, pesagemBalanca not set in activity");
                }
                Pesagem pesagem = new Pesagem(pesagemBalanca.getPeso());
                tabListaPesagensAbateFragment.getPesagens().addPesagemAndSetNumber(pesagem);

                qtdPesagensAbateTextView.setText(String.format(qtdPesagensAbateFormat, tabListaPesagensAbateFragment.getPesagens().getSize()));
                qtdTotalTextView.setText(String.format(qtdTotalPesagensFormat, tabListaPesagensAbateFragment.getPesagens().getSize() + tabListaPesagensFragment.getPesagens().getSize()));
                tabListaPesagensAbateFragment.getListaPesagensListViewArrayAdapter().notifyDataSetChanged();
            }
        });
    }


    private static class PesoTextViewBalancaEventListener implements IndicadorEventListener{

        private TextView pesoTextView;

        private PesoTextViewBalancaEventListener(TextView pesoTextView){
            this.pesoTextView = pesoTextView;
        }

        @Override
        public void onGetPesoEvent(Float peso) {
            this.pesoTextView.setText(String.format("%.2f", peso));
        }
    }

}
