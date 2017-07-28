package pesagem.ovinospanorama.com.pesagemcordeiros.balanca;

import java.util.Random;


public class IndicadorMock implements IndicadorBalanca.Indicador {

    private Random random = new Random();

    @Override
    public boolean openSerial(String porta, int baudeRate) {
        return false;
    }

    @Override
    public boolean openTcp(String host, int porta) {
        return false;
    }

    @Override
    public String getPeso() {
        return String.valueOf(random.nextInt(50) + random.nextFloat());
    }

    @Override
    public String getTara() {
        return null;
    }

    @Override
    public String getPesoBruto() {
        return null;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public void waiting() {

    }

    @Override
    public int tarar() {
        return 0;
    }

    @Override
    public int tararManual(int peso) {
        return 0;
    }

    @Override
    public boolean zerar() {
        return false;
    }
}
