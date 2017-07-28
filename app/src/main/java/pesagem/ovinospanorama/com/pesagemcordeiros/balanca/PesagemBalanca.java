package pesagem.ovinospanorama.com.pesagemcordeiros.balanca;


import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PesagemBalanca implements IndicadorEventListener {

    private static final Long START_DELAY = 10000L;
    private static final Long READ_INTERVAL = 1000L;

    private Float peso;

    private IndicadorBalanca.Indicador indicador;

    private List<IndicadorEventListener> eventListeners;

    public PesagemBalanca(IndicadorBalanca.Indicador indicador, List<IndicadorEventListener> eventListeners) {
        this.indicador = indicador;
        this.eventListeners = eventListeners;
        if(eventListeners == null){
            eventListeners = new ArrayList<IndicadorEventListener>();
        }
        eventListeners.add(this);
    }

    public void addIndicadorEventListener(IndicadorEventListener indicadorEventListener){
        this.eventListeners.add(indicadorEventListener);
    }

    public Float getPeso() {
        return this.peso;
    }

    @Override
    public void onGetPesoEvent(Float peso) {
        this.peso = peso;
    }

    public void initialize(){
        Timer timer = new Timer();
        TimerTask timerTask = new BalancaTimer(indicador, eventListeners);
        timer.schedule(timerTask, START_DELAY, READ_INTERVAL);
    }



    private static class BalancaTimer extends TimerTask {

        private IndicadorBalanca.Indicador indicador;
        private List<IndicadorEventListener> eventListeners;

        public BalancaTimer(IndicadorBalanca.Indicador indicador,
                            List<IndicadorEventListener> eventListeners){
            this.indicador = indicador;
            this.eventListeners = eventListeners;
        }

        @Override
        public void run() {
            if(indicador.isOpen()){
                Float peso = Float.valueOf(indicador.getPeso());
                for(IndicadorEventListener listener : eventListeners){
                    listener.onGetPesoEvent(peso);
                }
            }
        }
    }
}
