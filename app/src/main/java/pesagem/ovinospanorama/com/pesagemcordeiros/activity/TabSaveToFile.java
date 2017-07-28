package pesagem.ovinospanorama.com.pesagemcordeiros.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

import pesagem.ovinospanorama.com.pesagemcordeiros.R;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagem;
import pesagem.ovinospanorama.com.pesagemcordeiros.containers.Pesagens;
import pesagem.ovinospanorama.com.pesagemcordeiros.util.FileUtil;


public class TabSaveToFile extends Fragment {

    private Button saveToFileButton;


    private static final String DIR_NAME = "PESAGEM_BALANCA";


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        if(container==null){
            return null;
        }

        View view = (LinearLayout) inflater.inflate(R.layout.tab_save_to_file, container, false);
        saveToFileButton = (Button) view.findViewById(R.id.saveToFileButton);
        saveToFileButton.setOnClickListener(new GravarButtonOnClickListener());

        return view;
    }



    private class GravarButtonOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            this.saveToFile(Pesagens.getPesagens(Pesagens.PesagensType.PESAGEM));
            this.saveToFile(Pesagens.getPesagens(Pesagens.PesagensType.PESAGEM_ABATE));
        }

        private final void saveToFile(Pesagens pesagens){
            String filename = pesagens.getPesagensType() + "_" + DateFormat.getDateTimeInstance().format(new Date());
            filename = filename.replaceAll(":", "_").replaceAll("[^0-9A-Za-z_]+", "_") + ".csv";
            FileOutputStream outputStream;
            File file = FileUtil.getStorageDir(getView().getContext(), DIR_NAME, filename);

            try {
                outputStream = new FileOutputStream(file);
                StringBuffer res = new StringBuffer();
                for(Pesagem pes : pesagens.getAll()){
                    res.append(pes.toCsv());
                }
                outputStream.write(res.toString().getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
