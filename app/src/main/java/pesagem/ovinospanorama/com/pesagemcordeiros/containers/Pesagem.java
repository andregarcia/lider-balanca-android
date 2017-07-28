package pesagem.ovinospanorama.com.pesagemcordeiros.containers;


public class Pesagem {

    private Integer numero;

    private Float peso;

    private String obs;

    private Pesagens.PesagensType pesagemType;

    public Pesagem(){

    }

    public Pesagem(Float peso) {
        this.peso = peso;
    }

    public Pesagem(Float peso, Pesagens.PesagensType type){
        this.peso = peso;
        this.pesagemType = type;
    }

    public Float getPeso() {
        return peso;
    }

    public void setNumero(int numero){
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public String getObs() {
        return obs;
    }

    public Pesagens.PesagensType getPesagemType() {
        return pesagemType;
    }

    public void setPesagemType(Pesagens.PesagensType pesagemType) {
        this.pesagemType = pesagemType;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        String s = String.format("%d\t\t\t %.2f", numero, peso);
        if(obs!=null){
            s += "\t\t\t obs=" + obs;
        }
        return s;
    }


    public String toCsv(){
        return String.format("%s;%s;%s;%s\n",
                toStringEmptyIfNull(numero),
                toStringEmptyIfNull(peso),
                toStringEmptyIfNull(obs),
                toStringEmptyIfNull(pesagemType)
                );
    }

    private String toStringEmptyIfNull(Object o){
        return o==null ? "" : String.valueOf(o);
    }
}
