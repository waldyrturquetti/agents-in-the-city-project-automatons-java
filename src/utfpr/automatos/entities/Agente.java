package utfpr.automatos.entities;

import utfpr.automatos.enums.TipoAgent;
import utfpr.automatos.enums.TipoLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Agente {

    private String nome;
    private Integer qtdBateria;
    private TipoAgent tipoAgent;
    private int armazenamentoLivre;
    private int lat;
    private int lon;
    private int moedas;
    private List<Item> itemList;

    public Agente(String nome, Integer qtdBateria, TipoAgent tipoAgent, int lat, int lon, int armazenamentoLivre) {
        this.nome = nome;
        this.qtdBateria = qtdBateria;
        this.tipoAgent = tipoAgent;
        this.lat = lat;
        this.lon = lon;
        this.armazenamentoLivre = armazenamentoLivre;
        this.itemList = new ArrayList<Item>();
        this.moedas = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdBateria() {
        return qtdBateria;
    }

    public void setQtdBateria(Integer qtdBateria) {
        this.qtdBateria = qtdBateria;
    }

    public TipoAgent getTipoAgent() {
        return tipoAgent;
    }

    public void setTipoAgent(TipoAgent tipoAgent) {
        this.tipoAgent = tipoAgent;
    }

    public int getArmazenamentoLivre() {
        return armazenamentoLivre;
    }

    public void setArmazenamentoLivre(int armazenamentoLivre) {
        this.armazenamentoLivre = armazenamentoLivre;
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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        return "Agente{" +
                "nome='" + nome + '\'' +
                ", qtdBateria=" + qtdBateria +
                ", tipoAgent=" + tipoAgent +
                ", armazenamentoLivre=" + armazenamentoLivre +
                ", lat=" + lat +
                ", lon=" + lon +
                ", itemList=" + itemList +
                '}';
    }

    public void percorreMatriz(String[][] matriz, int i, int j, List<Item> items){

        matriz[this.lat][this.lon] = "0";

        while (true){

            if( this.lat < i ){
                ++this.lat;
                --this.qtdBateria;
            } else if(this.lat > i){
                --this.lat;
                --this.qtdBateria;
            } else if( this.lon < j ){
                ++this.lon;
                --this.qtdBateria;
            } else if(this.lon > j){
                --this.lon;
                --this.qtdBateria;
            } else {
                break;
            }

            if(!matriz[this.lat][this.lon].equals("0")){
                char caractere = matriz[this.lat][this.lon].charAt(0);
                if(caractere == 'i'){
                    for (Item item : items){
                        if(item.getLat() == this.lat && item.getLon() == this.lon){
                            this.itemList.add(item);
                            this.armazenamentoLivre =- item.getPeso();
                            matriz[item.getLat()][item.getLon()] = "0";
                            item.setLat(0); item.setLon(0);
                            System.out.println("1 ITEM FOI PEGO PELO AGENTE");
                        }
                    }
                }
            }
        }

        matriz[i][j] = this.nome;
    }

    public Boolean verificaBateria(){
        if(this.getQtdBateria() < 50){
            System.out.println("VAI POSTO");
            return true;
        } else {
            return false;
        }
    }

    public void vaiPosto(List<Local> locals, String[][] matriz, List<Item> itemList){

        int aux1; int aux2; int aux3; int aux4;
        int i = locals.get(0).getLat();
        int j = locals.get(0).getLon();

        aux4 = getAux4(i, j);

        for (Local local : locals){

            if(local.getTipoLocal().equals(TipoLocal.POSTO)){

                if(this.lat > local.getLat()){
                    aux1 = this.lat - local.getLat();
                } else {
                    aux1 = local.getLat() - this.lat;
                }

                if(this.lon > local.getLon()){
                    aux2 = this.lon - local.getLon();
                } else {
                    aux2 = local.getLon() - this.lat;
                }

                aux3 = aux1 + aux2;
                if( aux4 > aux3 ) {
                    i = local.getLat();
                    j = local.getLon();
                }
            }
        }

        if(j < 19){
            j++;
        } else {
            j--;
        }

        this.percorreMatriz(matriz, i, j, itemList);
        this.setQtdBateria(100);
    }

    public void vaiLixao(List<Local> locals, String[][] matriz, List<Item> itemList){

        int aux1; int aux2; int aux3; int aux4;
        int i = locals.get(9).getLat();
        int j = locals.get(9).getLon();

        aux4 = getAux4(i, j);

        for (Local local : locals){

            if(local.getTipoLocal().equals(TipoLocal.LIXAO)){

                if(this.lat > local.getLat()){
                    aux1 = this.lat - local.getLat();
                } else {
                    aux1 = local.getLat() - this.lat;
                }

                if(this.lon > local.getLon()){
                    aux2 = this.lon - local.getLon();
                } else {
                    aux2 = local.getLon() - this.lat;
                }

                aux3 = aux1 + aux2;
                if( aux4 > aux3 ) {
                    i = local.getLat();
                    j = local.getLon();
                }
            }
        }

        if(j < 19){
            j++;
        } else {
            j--;
        }

        this.percorreMatriz(matriz, i, j, itemList);

        System.out.println("Itens do Agent:");
        for (Item item : this.itemList)
            System.out.println(item);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual item vc deseja tirar? (Se é o 1, digite 1. Se for o segundo digite 2. etc.)");
        int op1 = scanner.nextInt();
        scanner.nextLine();
        this.itemList.remove(op1 - 1);

//        scanner.close();
    }

    public void vaiOficina(List<Local> locals, String[][] matriz, List<Item> itemList) {

        int aux1; int aux2; int aux3; int aux4;
        int i = locals.get(6).getLat();
        int j = locals.get(6).getLon();

        aux4 = getAux4(i, j);

        for (Local local : locals){

            if(local.getTipoLocal().equals(TipoLocal.OFICINA)){

                if(this.lat > local.getLat()){
                    aux1 = this.lat - local.getLat();
                } else {
                    aux1 = local.getLat() - this.lat;
                }

                if(this.lon > local.getLon()){
                    aux2 = this.lon - local.getLon();
                } else {
                    aux2 = local.getLon() - this.lat;
                }

                aux3 = aux1 + aux2;
                if( aux4 > aux3 ) {
                    i = local.getLat();
                    j = local.getLon();
                }
            }
        }

        if(j < 19){
            j++;
        } else {
            j--;
        }

        this.percorreMatriz(matriz, i, j, itemList);

        int moeda = 0;
        int peso = itemList.get(0).getPeso();

        for (Item item : this.itemList){
            moeda += item.getMoedaMassim();
        }

        this.itemList.removeAll(this.itemList);
        this.itemList.add( new Item("ItemOfficina", moeda*2, 0, 0, peso));
        for(Item item : this.itemList){
            System.out.println(item);
        }
    }

    public void vaiMercado(List<Local> locals, String[][] matriz, List<Item> itemList) {

        int aux1; int aux2; int aux3; int aux4;
        int i = locals.get(3).getLat();
        int j = locals.get(3).getLon();

        aux4 = getAux4(i, j);

        for (Local local : locals){

            if(local.getTipoLocal().equals(TipoLocal.OFICINA)){

                if(this.lat > local.getLat()){
                    aux1 = this.lat - local.getLat();
                } else {
                    aux1 = local.getLat() - this.lat;
                }

                if(this.lon > local.getLon()){
                    aux2 = this.lon - local.getLon();
                } else {
                    aux2 = local.getLon() - this.lat;
                }

                aux3 = aux1 + aux2;
                if( aux4 > aux3 ) {
                    i = local.getLat();
                    j = local.getLon();
                }
            }
        }

        if(j < 19){
            j++;
        } else {
            j--;
        }

        this.percorreMatriz(matriz, i, j, itemList);

        int moeda = 0;
        for ( Item item : this.itemList ){
            moedas += item.getMoedaMassim();
        }
        this.itemList.removeAll(this.itemList);
        this.moedas = moedas;

        System.out.println("Foram vendidos os itens");
    }

    private int getAux4(int i, int j) {
        int aux1;
        int aux2;
        int aux4;
        if(this.lat > i){
            aux1 = this.lat - i;
        } else {
            aux1 = i - this.lat;
        }

        if(this.lon > j){
            aux2 = this.lon - j;
        } else {
            aux2 = this.lat - j;
        }
        aux4 = aux1 + aux2;
        return aux4;
    }
}
