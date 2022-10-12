package utfpr.automatos.entities;

public class Pocos {

    private String nomePoco;
    private Integer moedasParaConstrucao;
    private Integer moedasAtual;
    private boolean finalizado;
    private int lat;
    private int lon;

    public Pocos(String nomePoco, Integer moedasParaConstrucao, Integer moedasAtual, boolean finalizado, int lat, int lon) {
        this.nomePoco = nomePoco;
        this.moedasParaConstrucao = moedasParaConstrucao;
        this.moedasAtual = moedasAtual;
        this.finalizado = finalizado;
        this.lat = lat;
        this.lon = lon;
    }

    public String getNomePoco() {
        return nomePoco;
    }

    public void setNomePoco(String nomePoco) {
        this.nomePoco = nomePoco;
    }

    public Integer getMoedasParaConstrucao() {
        return moedasParaConstrucao;
    }

    public void setMoedasParaConstrucao(Integer moedasParaConstrucao) {
        this.moedasParaConstrucao = moedasParaConstrucao;
    }

    public Integer getMoedasAtual() {
        return moedasAtual;
    }

    public void setMoedasAtual(Integer moedasAtual) {
        this.moedasAtual = moedasAtual;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Pocos{" +
                "nomePoco='" + nomePoco + '\'' +
                ", moedasParaConstrucao=" + moedasParaConstrucao +
                ", moedasAtual=" + moedasAtual +
                ", finalizado=" + finalizado +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}

