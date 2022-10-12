package utfpr.automatos;

import utfpr.automatos.entities.*;
import utfpr.automatos.enums.TipoAgent;
import utfpr.automatos.enums.TipoLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        String[][] matriz = new String[20][20];

        //CRIAÇÃO DOS LOCAIS
        List<Local> listLocal = new ArrayList<Local>();

        Local local1 = new Local("P1", 14, 17, TipoLocal.POSTO );
        Local local2 = new Local("P2", 3, 19, TipoLocal.POSTO );
        Local local3 = new Local("P3", 18, 4, TipoLocal.POSTO );
        listLocal.add(local1); listLocal.add(local2); listLocal.add(local3);

        Local local4 = new Local("M1", 11, 14, TipoLocal.MERCADO );
        Local local5 = new Local("M2", 3, 5, TipoLocal.MERCADO );
        Local local6 = new Local("M3", 7, 9, TipoLocal.MERCADO );

        //CRIAÇÃO DOS ITENS DOS MERCADOS
        Item itemMercado1 = new Item("iMercado1", 15, 0, 0, 20);
        Item itemMercado2 = new Item("iMercado2", 56, 0, 0, 40);
        Item itemMercado3 = new Item("iMercado3", 56, 0, 0, 30);
        Item itemMercado4 = new Item("iMercado4", 64, 0, 0, 25);
        Item itemMercado5 = new Item("iMercado5", 23, 0, 0, 46);
        Item itemMercado6 = new Item("iMercado6", 45, 0, 0, 36);
        local4.getItemList().add(itemMercado1); local4.getItemList().add(itemMercado2);
        local5.getItemList().add(itemMercado3); local5.getItemList().add(itemMercado4);
        local6.getItemList().add(itemMercado5); local6.getItemList().add(itemMercado6);
        listLocal.add(local4); listLocal.add(local5); listLocal.add(local6);

        Local local7 = new Local("O1", 16, 4, TipoLocal.OFICINA );
        Local local8 = new Local("O2", 2, 0, TipoLocal.OFICINA );
        Local local9 = new Local("O3", 19, 1, TipoLocal.OFICINA );
        listLocal.add(local7); listLocal.add(local8); listLocal.add(local9);

        Local local10 = new Local("L1", 15, 3, TipoLocal.LIXAO );
        Local local11 = new Local("L2", 2, 16, TipoLocal.LIXAO );
        Local local12 = new Local("L3", 5, 11, TipoLocal.LIXAO );
        listLocal.add(local10); listLocal.add(local11); listLocal.add(local12);

        Local local13 = new Local("A1", 9, 2, TipoLocal.ARMAZEM );
        listLocal.add(local13);


        //CRIAÇÃO DOS ITENS ESPALHADOS NO MAPA
        List<Item> itemList = new ArrayList<Item>();
        Item item1 = new Item("i1", 15, random.nextInt(20), random.nextInt(20), 20);
        Item item2 = new Item("i2", 20, random.nextInt(20), random.nextInt(20), 10);
        Item item3 = new Item("i3", 25, random.nextInt(20), random.nextInt(20), 5);
        Item item4 = new Item("i4", 50, random.nextInt(20), random.nextInt(20), 30);
        Item item5 = new Item("i5", 35, random.nextInt(20), random.nextInt(20), 15);
        Item item6 = new Item("i6", 15, random.nextInt(20), random.nextInt(20), 20);
        Item item7 = new Item("i7", 20, random.nextInt(20), random.nextInt(20), 10);
        Item item8 = new Item("i8", 25, random.nextInt(20), random.nextInt(20), 5);
        Item item9 = new Item("i9", 50, random.nextInt(20), random.nextInt(20), 30);
        Item item10 = new Item("i10", 35, random.nextInt(20), random.nextInt(20), 15);
        itemList.add(item1); itemList.add(item2); itemList.add(item3);  itemList.add(item4); itemList.add(item5);
        itemList.add(item6); itemList.add(item7); itemList.add(item8);  itemList.add(item9); itemList.add(item10);

        // CRIAÇÃO DO AGENTE
        Agente agente = new Agente("AGENT", 100, TipoAgent.CARRO, 0, 0, 100);

        // CRIAÇÂO DOS POÇOS
        List<Pocos> pocosList = new ArrayList<Pocos>();
        Pocos pocos1 = new Pocos("Poço1", 200, 0, false, 12, 17);
        Pocos pocos2 = new Pocos("Poço2", 300, 0, false, 4, 19);
        pocosList.add(pocos1); pocosList.add(pocos2);

        // CRIAÇÃO DOS CONTRATOS
        List<ContratosDeServico> contratosDeServicoList = new ArrayList<ContratosDeServico>();
        ContratosDeServico contratosDeServico1 = new ContratosDeServico("Contrato1", 400, 10, 3, 12, 4);
        ContratosDeServico contratosDeServico2 = new ContratosDeServico("Contrato2", 500, 1, 19, 3, 6);
        contratosDeServicoList.add(contratosDeServico1); contratosDeServicoList.add(contratosDeServico2);

        // CONSTRUÇÃO INICIAL DA MATRIZ
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                matriz[i][j] = "0";
            }
        }

        // ADICIONANDO OS ITENS NA MATRIZ
        for (Item item : itemList){
            matriz[item.getLat()][item.getLon()] = item.getNome();
        }

        // ADICIONANDO OS LOCAIS NA MATRIZ
        for (Local local: listLocal){
            matriz[local.getLat()][local.getLon()] = local.getNome();
        }

        // ADICIONANDO OS POÇOS NA MATRIZ
        for ( Pocos pocos : pocosList){
            matriz[pocos.getLat()][pocos.getLon()] = pocos.getNomePoco();
        }

        matriz[agente.getLat()][agente.getLon()] = agente.getNome();
        printMatriz(matriz);

        int op;
        StringBuilder fita = new StringBuilder();

        while (true){

            if(agente.verificaBateria()){
                agente.vaiPosto(listLocal,matriz, itemList);
                printMatriz(matriz);
                fita.append("bb");
            }

            System.out.println("Digite 1 para PROCURAR MAIS ITENS"); // Feito
            System.out.println("Digite 2 para JOGAR SEUS ITENS FORA"); // Feito
            System.out.println("Digite 3 para RECUPERAR ITENS JOGADOS FORA"); // Feito
            System.out.println("Digite 4 para IR ATÉ O MERCADO"); // Feito
            System.out.println("Digite 5 para IR ATÉ O OFICINA"); // Feito
            System.out.println("Digite 6 para REALIZAR CONTRATOS"); // Feito
            System.out.println("Digite 7 para IR ARMAZEM DE ITENS"); // Feito
            System.out.println("Digite 8 para Construir os Poços"); // Feito
            System.out.println("Digite 9 para Visualizar os atributos do Agente"); // Feito
            System.out.println("Digite qualquer outra coisa para SAIR");
            op = sc.nextInt();

            switch(op){
                case 1: {
                    agente.percorreMatriz(matriz, random.nextInt(20), random.nextInt(20), itemList);
                    printMatriz(matriz);
                    fita.append("ccc");
                } break;
                case 2: {
                    agente.vaiLixao(listLocal,matriz, itemList);
                    fita.append("aa");
                    printMatriz(matriz);
                } break;
                case 3: {
                    agente.recuperaItemLixao(listLocal, matriz, itemList);
                    fita.append("aa");
                    printMatriz(matriz);
                } break;
                case 4: {
                    agente.vaiMercado(listLocal, matriz, itemList);
                    fita.append("ef");
                    printMatriz(matriz);
                }break;
                case 5: {
                    agente.vaiOficina(listLocal, matriz, itemList);
                    fita.append("dd");
                    printMatriz(matriz);
                }break;
                case 6: {
                    agente.realizaContrato(contratosDeServicoList, matriz, itemList, fita);
                    fita.append("f");
                    printMatriz(matriz);
                }break;
                case 7: {
                    agente.vaiArmazem(listLocal, matriz, itemList, fita);
                    fita.append("i");
                    printMatriz(matriz);
                }break;
                case 8: {
                    agente.constroiPoco(pocosList, matriz, itemList);
                    fita.append("jf");
                    printMatriz(matriz);
                }break;
                case 9: {
                    System.out.println(agente);;
                }break;
                default: {
                    System.out.println("Fita: \n" + fita);
                    sc.close();
                    return;
                }
            }
        }
    }


    public static void printMatriz(String[][] str){
        System.out.println();
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                System.out.print(" " + str[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
