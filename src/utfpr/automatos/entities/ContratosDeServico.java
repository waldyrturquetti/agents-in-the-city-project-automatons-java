package utfpr.automatos.entities;

public class ContratosDeServico {

    private String nomeContrato;
    private Integer recompensa;
    private int latCarga;
    private int lonCarga;
    private int latDescarga;
    private int lonDescarga;
    private int pesoCarga;
    private boolean finalizado;

    public ContratosDeServico(String nomeContrato, Integer recompensa, int latCarga, int lonCarga, int latDescarga, int lonDescarga) {
        this.nomeContrato = nomeContrato;
        this.recompensa = recompensa;
        this.latCarga = latCarga;
        this.lonCarga = lonCarga;
        this.latDescarga = latDescarga;
        this.lonDescarga = lonDescarga;
        this.finalizado = false;
    }

    public String getNomeContrato() {
        return nomeContrato;
    }

    public void setNomeContrato(String nomeContrato) {
        this.nomeContrato = nomeContrato;
    }

    public Integer getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(Integer recompensa) {
        this.recompensa = recompensa;
    }

    public int getLatCarga() {
        return latCarga;
    }

    public void setLatCarga(int latCarga) {
        this.latCarga = latCarga;
    }

    public int getLonCarga() {
        return lonCarga;
    }

    public void setLonCarga(int lonCarga) {
        this.lonCarga = lonCarga;
    }

    public int getLatDescarga() {
        return latDescarga;
    }

    public void setLatDescarga(int latDescarga) {
        this.latDescarga = latDescarga;
    }

    public int getLonDescarga() {
        return lonDescarga;
    }

    public void setLonDescarga(int lonDescarga) {
        this.lonDescarga = lonDescarga;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public int getPesoCarga() {
        return pesoCarga;
    }

    public void setPesoCarga(int pesoCarga) {
        this.pesoCarga = pesoCarga;
    }

    @Override
    public String toString() {
        return "ContratosDeServico{" +
                "nomeContrato='" + nomeContrato + '\'' +
                ", recompensa=" + recompensa +
                ", latCarga=" + latCarga +
                ", lonCarga=" + lonCarga +
                ", latDescarga=" + latDescarga +
                ", lonDescarga=" + lonDescarga +
                ", pesoCarga=" + pesoCarga +
                ", finalizado=" + finalizado +
                '}';
    }
}
