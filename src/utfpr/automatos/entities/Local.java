package utfpr.automatos.entities;

import utfpr.automatos.enums.TipoLocal;

public class Local {

    private String nome;
    private int lat;
    private int lon;
    private TipoLocal tipoLocal;

    public Local(String nome, int lat, int lon, TipoLocal tipoLocal) {
        this.nome = nome;
        this.lat = lat;
        this.lon = lon;
        this.tipoLocal = tipoLocal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public TipoLocal getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(TipoLocal tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    @Override
    public String toString() {
        return "Local{" +
                "nome='" + nome + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", tipoLocal=" + tipoLocal +
                '}';
    }
}
