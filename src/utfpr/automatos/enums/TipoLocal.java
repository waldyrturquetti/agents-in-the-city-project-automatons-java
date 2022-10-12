package utfpr.automatos.enums;

public enum TipoLocal {

    LIXAO("Lixao"),
    MERCADO("Mercado"),
    OFICINA("Oficina"),
    POSTO("Posto");

    private String nomeTipo;

    TipoLocal(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }
}
