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
                            this.armazenamentoLivre -= item.getPeso();
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

        int auxLat; int auxLon; int menorDistancia; int distancia;
        int i = locals.get(0).getLat();
        int j = locals.get(0).getLon();
        distancia = getDistancia(i, j);

        for (Local local : locals){

            if(local.getTipoLocal().equals(TipoLocal.POSTO)){

                if(this.lat > local.getLat()){
                    auxLat = this.lat - local.getLat();
                } else {
                    auxLat = local.getLat() - this.lat;
                }

                if(this.lon > local.getLon()){
                    auxLon = this.lon - local.getLon();
                } else {
                    auxLon = local.getLon() - this.lat;
                }

                menorDistancia = auxLat + auxLon;
                if( distancia > menorDistancia ) {
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

        int auxLat; int auxLon; int menorDistancia; int distancia;
        int i = locals.get(9).getLat();
        int j = locals.get(9).getLon();
        distancia = getDistancia(i, j);

        for (Local local : locals) {

            if(local.getTipoLocal().equals(TipoLocal.LIXAO)){

                if(this.lat > local.getLat()){
                    auxLat = this.lat - local.getLat();
                } else {
                    auxLat = local.getLat() - this.lat;
                }

                if(this.lon > local.getLon()){
                    auxLon = this.lon - local.getLon();
                } else {
                    auxLon = local.getLon() - this.lat;
                }

                menorDistancia = auxLat + auxLon;
                if( distancia > menorDistancia ) {
                    i = local.getLat();
                    j = local.getLon();
                }
            }
        }

        int auxJ = j;
        if(j < 19){
            auxJ++;
        } else {
            auxJ--;
        }

        this.percorreMatriz(matriz, i, auxJ, itemList);

        System.out.println("Itens do Agent:");
        for (Item item : this.itemList)
            System.out.println(item);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual item vc deseja JOGAR FORA? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");
        int op1 = scanner.nextInt();
        scanner.nextLine();
        Item item = itemList.get(op1 - 1);
        Local lixao = null;
        for (Local local : locals){
            if (local.getLat() == i && local.getLon() == j){
                lixao = local;
            }
        }
        lixao.getItemList().add(item);
        this.itemList.remove(item);
    }

    public void vaiOficina(List<Local> locals, String[][] matriz, List<Item> itemList) {

        int auxLat; int auxLon; int menorDistancia; int distancia;
        int i = locals.get(6).getLat();
        int j = locals.get(6).getLon();
        distancia = getDistancia(i, j);

        for (Local local : locals){

            if(local.getTipoLocal().equals(TipoLocal.OFICINA)){

                if(this.lat > local.getLat()){
                    auxLat = this.lat - local.getLat();
                } else {
                    auxLat = local.getLat() - this.lat;
                }

                if(this.lon > local.getLon()){
                    auxLon = this.lon - local.getLon();
                } else {
                    auxLon = local.getLon() - this.lat;
                }

                menorDistancia = auxLat + auxLon;
                if( distancia > menorDistancia ) {
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - Para Montar 1 item com os itens que o AGENT possui.");
        System.out.println("2 - Para Desmontar 1 item, transformando em 2 itens.");
        System.out.println("3 - Para sair digite qualquer outra coisa");
        int op1 = scanner.nextInt();
        scanner.nextLine();

        switch (op1){
            case 1:{
                this.percorreMatriz(matriz, i, j, itemList);
                int moeda = 0;
                int peso = itemList.get(0).getPeso();
                for (Item item : this.itemList){
                    moeda += item.getMoedaMassim();
                }
                this.itemList.removeAll(this.itemList);
                this.itemList.add( new Item("ItemOficinaMontado", moeda*2, 0, 0, peso));
                for(Item item : this.itemList) {
                    System.out.println(item);
                }
            }break;
            case 2:{
                for(Item item : this.itemList) {
                    System.out.println(item);
                }
                System.out.println("Qual item vc deseja desmontar? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");
                int op2 = scanner.nextInt();
                Item item = itemList.get(op2 - 1);
                this.itemList.add(new Item("ItemOficinaDesmontado1", item.getMoedaMassim()/2, 0, 0, item.getPeso()/2));
                this.itemList.add(new Item("ItemOficinaDesmontado2", item.getMoedaMassim()/2, 0, 0, item.getPeso()/2));
                this.itemList.remove(item);
                for(Item item1 : this.itemList) {
                    System.out.println(item1);
                }
            }break;
            case 3:{
                break;
            }
        }
    }

    public void vaiMercado(List<Local> locals, String[][] matriz, List<Item> itemList) {

        int auxLat; int auxLon; int menorDistancia; int distancia;
        int i = locals.get(3).getLat();
        int j = locals.get(3).getLon();
        distancia = getDistancia(i, j);

        for (Local local : locals){

            if(local.getTipoLocal().equals(TipoLocal.MERCADO)){

                if(this.lat > local.getLat()){
                    auxLat = this.lat - local.getLat();
                } else {
                    auxLat = local.getLat() - this.lat;
                }

                if(this.lon > local.getLon()){
                    auxLon = this.lon - local.getLon();
                } else {
                    auxLon = local.getLon() - this.lat;
                }

                menorDistancia = auxLat + auxLon;
                if( distancia > menorDistancia ) {
                    i = local.getLat();
                    j = local.getLon();
                    distancia = i + j;
                }
            }
        }

        int auxJ = j;
        if(j < 19){
            auxJ++;
        } else {
            auxJ--;
        }

        this.percorreMatriz(matriz, i, auxJ, itemList);

        for (Local localAux : locals) {
            if (localAux.getTipoLocal().equals(TipoLocal.MERCADO) && localAux.getLat() == i && localAux.getLon() == j) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("1 - Para Vender TODOS seus itens.");
                System.out.println("2 - Para Comprar 1 item da loja.");
                System.out.println("3 - Para sair digite qualquer outra coisa");
                int op1 = scanner.nextInt();
                scanner.nextLine();

                switch (op1){
                    case 1:{
                        for ( Item item : this.itemList ){
                            this.moedas += item.getMoedaMassim();
                            this.armazenamentoLivre += item.getPeso();
                        }
                        this.itemList.removeAll(this.itemList);
                        System.out.println("Foram vendidos os itens");
                    }break;
                    case 2:{
                        for (Item item : localAux.getItemList()){
                            System.out.println(item);
                        }
                        System.out.println("Qual item vc deseja Comprar? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");
                        int op2 = scanner.nextInt();
                        Item item = localAux.getItemList().get(op2 - 1);

                        if (this.moedas >= item.getMoedaMassim() && this.armazenamentoLivre >= item.getPeso()){
                            this.itemList.add(item);
                            localAux.getItemList().remove(item);
                            System.out.println("Item comprado e adicionado ao inventario do Agente");
                        } else {
                            System.out.println("Não foi possível comprar esse item");
                        }

                        this.itemList.add(item);
                        localAux.getItemList().remove(item);
                    }break;
                    case 3:{
                        break;
                    }
                }
                break;
            }
        }
        System.out.println("saiu do Mercado");
    }

    public void vaiArmazem(List<Local> locals, String[][] matriz, List<Item> itemList, StringBuilder fita) {

        int auxLat; int auxLon; int menorDistancia;
        int i = locals.get(6).getLat();
        int j = locals.get(6).getLon();
        int distancia = getDistancia(i, j);

        for (Local local : locals) {

            if (local.getTipoLocal().equals(TipoLocal.ARMAZEM)) {

                if (this.lat > local.getLat()) {
                    auxLat = this.lat - local.getLat();
                } else {
                    auxLat = local.getLat() - this.lat;
                }

                if (this.lon > local.getLon()) {
                    auxLon = this.lon - local.getLon();
                } else {
                    auxLon = local.getLon() - this.lat;
                }

                menorDistancia = auxLat + auxLon;
                if (distancia > menorDistancia) {
                    i = local.getLat();
                    j = local.getLon();
                }
            }
        }

        int auxJ;
        if (j < 19) {
            auxJ = j + 1;
        } else {
            auxJ = j - 1;
        }

        this.percorreMatriz(matriz, i, auxJ, itemList);

        for (Local localAux : locals){
            if (localAux.getTipoLocal().equals(TipoLocal.ARMAZEM) && localAux.getLat() == i && localAux.getLon() == j) {

                Scanner scanner = new Scanner(System.in);
                System.out.println("1 - Para inserir um item no Armazem");
                System.out.println("2 - Para Recuperar um item no Armazem");
                System.out.println("3 - Para sair digite qualquer outra coisa");
                int op1 = scanner.nextInt();
                scanner.nextLine();

                switch (op1){
                    case 1:{
                        for (Item item : this.itemList) {
                            System.out.println(item);
                        }
                        System.out.println("Qual item vc deseja Armazenar? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");
                        int op2 = scanner.nextInt();
                        localAux.getItemList().add(this.itemList.get(op2 - 1));
                        this.itemList.remove(op2 - 1);
                        for(Item item : localAux.getItemList()){
                            System.out.println(item);
                        }
                        fita.append("i");
                    }break;
                    case 2:{
                        for(Item item : localAux.getItemList()){
                            System.out.println(item);
                        }
                        System.out.println("Qual item vc deseja retirar do armazem? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");
                        int op2 = scanner.nextInt();
                        localAux.getItemList().add(this.itemList.get(op2 - 1));
                        this.itemList.remove(op2 - 1);
                        for(Item item : localAux.getItemList()){
                            System.out.println(item);
                        }
                        fita.append("j");
                    }break;
                    case 3:{
                        break;
                    }
                }
                break;
            }
        }
        System.out.println("saiu do Armazem");
    }

    public void realizaContrato(List<ContratosDeServico> contratosDeServicoList, String[][] matriz, List<Item> itemList, StringBuilder fita) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual Contrato vc deseja realizar? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");
        for (ContratosDeServico contratosDeServico : contratosDeServicoList){
            System.out.println(contratosDeServico);
        }
        int op1 = scanner.nextInt();
        ContratosDeServico contratosDeServico = contratosDeServicoList.get(op1 - 1);

        if(!contratosDeServico.isFinalizado()) {
            this.percorreMatriz(matriz, contratosDeServico.getLatCarga(), contratosDeServico.getLonCarga(), itemList);
            if (this.armazenamentoLivre >= contratosDeServico.getPesoCarga()){
                this.armazenamentoLivre -= contratosDeServico.getPesoCarga();
                this.percorreMatriz(matriz, contratosDeServico.getLatDescarga(), contratosDeServico.getLonDescarga(), itemList);
                this.armazenamentoLivre += contratosDeServico.getPesoCarga();
                this.moedas += contratosDeServico.getRecompensa();
                System.out.println("A carga foi entrega. Contrato finalizado");
            } else {
                System.out.println("Não ah espaço no agente para carregar esse item");
                return;
            }
        } else {
            System.out.println("Esse contrato já foi realizado");
        }
    }

    public void constroiPoco(List<Pocos> pocosList, String[][] matriz, List<Item> itemList){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual Poco vc deseja depositar moedas para a construção? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");
        for (Pocos pocos : pocosList){
            System.out.println(pocos);
        }
        int op1 = scanner.nextInt();
        Pocos pocos = pocosList.get(op1 - 1);

        if (!pocos.isFinalizado()){
            if (pocos.getMoedasParaConstrucao() >= this.moedas){
                pocos.setMoedasAtual(pocos.getMoedasAtual() + this.moedas);
                this.moedas = 0;
            } else {
                int aux = pocos.getMoedasParaConstrucao() - pocos.getMoedasAtual();
                this.moedas -= aux;
                pocos.setMoedasAtual(pocos.getMoedasAtual() + aux);
            }
            System.out.println("Moedas depositadas, quantidade de moedas do Poço eh: " + pocos.getMoedasAtual());
            if(pocos.getMoedasAtual() >= pocos.getMoedasParaConstrucao()){
                pocos.setFinalizado(true);
                System.out.println("Poço finalizado");
            }
            this.percorreMatriz(matriz, pocos.getLat(), pocos.getLon(), itemList);
        } else {
            System.out.println("Esse Poço já está finalizado");
        }
    }

    public void recuperaItemLixao(List<Local> listLocal, String[][] matriz, List<Item> itemList) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual LIXAO vc deseja ir para recupar um item? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");
        List<Local> lixoes = new ArrayList<Local>();

        for (Local local : listLocal){
            if(local.getTipoLocal().equals(TipoLocal.LIXAO)){
                System.out.println(local);
                lixoes.add(local);
                System.out.println("Itens desse LIXAO:");
                for (Item item : local.getItemList()) {
                    System.out.println(item);
                }
            }
        }

        int op1 = scanner.nextInt();
        Local lixao = lixoes.get(op1 - 1);
        System.out.println("Qual ITEM vc deseja recupar? (Se é o 1°, digite 1. Se for o 2° digite 2. etc.)");

        for (Item item : lixao.getItemList()){
            System.out.println(item);
        }

        op1 = scanner.nextInt();
        Item item = lixao.getItemList().get(op1 - 1);
        this.itemList.add(item);
        lixao.getItemList().remove(item);
        System.out.println("O item está foi devolvido para o agent");
    }


    private int getDistancia(Integer i, Integer j) {
        int aux1;
        int aux2;

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

        return aux1 + aux2;
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
                ", moedas=" + moedas +
                ", itemList=" + itemList +
                '}';
    }
}
