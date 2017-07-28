package pesagem.ovinospanorama.com.pesagemcordeiros.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import pesagem.ovinospanorama.com.pesagemcordeiros.R;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagem;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagens;

public class PesagemObsActivity extends AppCompatActivity {

    private EditText obsEditText;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesagem_obs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume(){
        super.onResume();
        Bundle b = getIntent().getExtras();

        Pesagem pesagem;
        try {
            pesagem = objectMapper.readValue(b.getString("pesagem"), Pesagem.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        initializeFieldValues(pesagem);
        obsEditText = (EditText) findViewById(R.id.obsText);
        initializeEditText(obsEditText, pesagem);
    }


    private void initializeFieldValues(Pesagem pesagem){
        TextView textView = (TextView)findViewById(R.id.infoTextView);
        textView.setText(String.format("Número: %d         Peso: %.2f", pesagem.getNumero(), pesagem.getPeso()));

        EditText editText = (EditText) findViewById(R.id.obsText);
        String obs = pesagem.getObs();
        if(obs!=null) editText.setText(obs.toCharArray(), 0, obs.length());
    }

    private void initializeEditText(final EditText editText, Pesagem pesagem){
        final Pesagem pesagemOriginal = Pesagens.getPesagens(pesagem.getPesagemType()).get(pesagem.getNumero()-1);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                return;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                pesagemOriginal.setObs(editText.getText().toString());
            }
        });
    }

}
