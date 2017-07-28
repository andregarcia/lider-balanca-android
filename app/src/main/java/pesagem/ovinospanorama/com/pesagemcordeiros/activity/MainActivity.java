package pesagem.ovinospanorama.com.pesagemcordeiros.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import pesagem.ovinospanorama.com.pesagemcordeiros.R;
import pesagem.ovinospanorama.com.pesagemcordeiros.adapter.SectionPageAdapter;
import pesagem.ovinospanorama.com.pesagemcordeiros.balanca.IndicadorBalanca;
import pesagem.ovinospanorama.com.pesagemcordeiros.balanca.IndicadorEventListener;
import pesagem.ovinospanorama.com.pesagemcordeiros.balanca.PesagemBalanca;

public class MainActivity extends AppCompatActivity {


    private LinearLayout tabBalancaLayout;
    private LinearLayout tabListaPesagemLayout;

    private PesagemBalanca pesagemBalanca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pesagemBalanca = new PesagemBalanca(IndicadorBalanca.getIndicadorBalancaMock(), new ArrayList<IndicadorEventListener>());
        pesagemBalanca.initialize();

        initializeTabs();
    }

    private void initializeTabs(){
        Button gravarPesagemButton = findViewById(R.id.buttonGravarPesagem);
        Button gravarPesagemAbateButton = findViewById(R.id.buttonGravarPesagemAbate);

        ViewPager viewPager = findViewById(R.id.container);
        setupViewPager(viewPager, gravarPesagemButton, gravarPesagemAbateButton);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager, Button gravarPesagemButton, Button gravarPesagemAbateButton){
        viewPager.setOffscreenPageLimit(4);
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        TabBalancaFragment tabBalancaFragment = new TabBalancaFragment();
        sectionPageAdapter.addFragment(tabBalancaFragment, "BALANÇA");
        tabBalancaFragment.setPesagemBalanca(pesagemBalanca);

        TabListaPesagensFragment tabListaPesagensFragment = new TabListaPesagensFragment();
        sectionPageAdapter.addFragment(tabListaPesagensFragment, "LISTA PESAGEM");

        TabListaPesagensAbateFragment tabListaPesagensAbateFragment = new TabListaPesagensAbateFragment();
        sectionPageAdapter.addFragment(tabListaPesagensAbateFragment, "LISTA ABATE");

        TabSaveToFile tabSaveToFile = new TabSaveToFile();
        sectionPageAdapter.addFragment(tabSaveToFile, "SALVAR");

        tabBalancaFragment.setTabListaPesagemAbateFragment(tabListaPesagensAbateFragment);
        tabBalancaFragment.setTabListaPesagensFragment(tabListaPesagensFragment);


        viewPager.setAdapter(sectionPageAdapter);
    }

}
