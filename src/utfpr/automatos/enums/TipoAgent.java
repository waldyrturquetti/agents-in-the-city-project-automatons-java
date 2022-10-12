package utfpr.automatos.enums;

public enum TipoAgent {

    CARRO("Carro"),
    MOTO("Moto"),
    CAMINHAO("Caminhao"),
    DRONE("Drone");

    private String nomeTipo;

    TipoAgent(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
}
