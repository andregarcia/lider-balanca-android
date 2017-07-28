package pesagem.ovinospanorama.com.pesagemcordeiros.containers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pesagens {

    private int cont = 0;

    private List<Pesagem> pesagens;

    private PesagensType pesagensType;

    private static final Map<PesagensType, Pesagens> pesagensByType = new HashMap<PesagensType, Pesagens>();

    public static Pesagens getPesagens(PesagensType type){
        if(!pesagensByType.containsKey(type)){
            pesagensByType.put(type, new Pesagens(type));
        }
        return pesagensByType.get(type);
    }

    private Pesagens(PesagensType type){
        this.pesagensType = type;
        this.pesagens = new ArrayList<Pesagem>();
    }

    public void addPesagem(float peso){
        Pesagem pesagem = new Pesagem(peso);
        pesagem.setPesagemType(this.pesagensType);
        this.pesagens.add(pesagem);
    }

    public void addPesagem(Pesagem pesagem){
        cont += 1;
        pesagem.setPesagemType(this.pesagensType);
        this.pesagens.add(pesagem);
    }

    public PesagensType getPesagensType() {
        return pesagensType;
    }

    public void setPesagensType(PesagensType pesagensType) {
        this.pesagensType = pesagensType;
    }

    public Pesagem get(int i){
        return pesagens.get(i);
    }

    public void remove(int i){
        this.pesagens.remove(i);
    }

    public void removeAndUpdateNumber(int i){
        this.pesagens.remove(i);
        this.updateNumbers();
    }

    public void addPesagemAndSetNumber(Pesagem pesagem){
        cont += 1;
        pesagem.setNumero(cont);
        pesagem.setPesagemType(pesagensType);
        pesagens.add(pesagem);
    }

    public void updateNumbers(){
        int cont = 1;
        for(Pesagem p : pesagens){
            p.setNumero(cont);
            cont+=1;
        }
    }

    public List<Pesagem> getAll(){
        return new ArrayList<Pesagem>(this.pesagens);
    }

    public int getSize(){
        return this.pesagens.size();
    }

    public List<Pesagem> getList(){
        return pesagens;
    }


    public enum PesagensType{
        PESAGEM,
        PESAGEM_ABATE
    }
}
