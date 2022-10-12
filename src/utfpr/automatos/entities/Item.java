package utfpr.automatos.entities;

public class Item {

    private String nome;
    private Integer moedaMassim;
    private int lat;
    private int lon;
    private int peso;

    public Item(String nome, Integer moedaMassim, int lat, int lon, int peso) {
        this.nome = nome;
        this.moedaMassim = moedaMassim;
        this.lat = lat;
        this.lon = lon;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getMoedaMassim() {
        return moedaMassim;
    }

    public void setMoedaMassim(Integer moedaMassim) {
        this.moedaMassim = moedaMassim;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLon() {
        return lon;
    }

    public void setLon(Integer lon) {
        this.lon = lon;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", moedaMassim=" + moedaMassim +
                ", lat=" + lat +
                ", lon=" + lon +
                "} \n";
    }
}
