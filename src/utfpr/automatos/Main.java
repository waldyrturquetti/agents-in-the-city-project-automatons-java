package utfpr.automatos;

import utfpr.automatos.entities.Agente;
import utfpr.automatos.entities.Item;
import utfpr.automatos.entities.Local;
import utfpr.automatos.enums.TipoAgent;
import utfpr.automatos.enums.TipoLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Object Localidade;

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
        listLocal.add(local4); listLocal.add(local5); listLocal.add(local6);

        Local local7 = new Local("O1", 16, 4, TipoLocal.OFICINA);
        Local local8 = new Local("O2", 2, 0, TipoLocal.OFICINA );
        Local local9 = new Local("O3", 19, 1, TipoLocal.OFICINA );
        listLocal.add(local7); listLocal.add(local8); listLocal.add(local9);

        Local local10 = new Local("L1", 15, 3, TipoLocal.LIXAO );
        Local local11 = new Local("L2", 2, 16, TipoLocal.LIXAO );
        Local local12 = new Local("L3", 5, 11, TipoLocal.LIXAO );
        listLocal.add(local10); listLocal.add(local11); listLocal.add(local12);

        //CRIAÇÃO DOS ITENS
        List<Item> itemList = new ArrayList<Item>();
        Item item1 = new Item("i1", 15, random.nextInt(20), random.nextInt(20), 20);
        Item item2 = new Item("i2", 20, random.nextInt(20), random.nextInt(20), 10);
        Item item3 = new Item("i3", 25, random.nextInt(20), random.nextInt(20), 5);
        Item item4 = new Item("i4", 50, random.nextInt(20), random.nextInt(20), 30);
        Item item5 = new Item("i5", 35, random.nextInt(20), random.nextInt(20), 15);
        itemList.add(item1); itemList.add(item2); itemList.add(item3);  itemList.add(item4); itemList.add(item5);

        //CRIAÇÃO DO AGENTE
        Agente agente = new Agente("AGENT", 100, TipoAgent.CARRO, 0, 0, 100);
        
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                matriz[i][j] = "0";
            }
        }

        for (Item item : itemList){
            matriz[item.getLat()][item.getLon()] = item.getNome();
        }

        for (Local local: listLocal){
            matriz[local.getLat()][local.getLon()] = local.getNome();
        }
        matriz[agente.getLat()][agente.getLon()] = agente.getNome();
        printMatriz(matriz);
        int op;
        String fita = "";

        for (int k = 0; k < 30; k++){

            if(agente.verificaBateria()){
                agente.vaiPosto(listLocal,matriz, itemList);
                fita = fita + "bb";
            }

            System.out.println("Digite 1 para PROCURAR MAIS ITENS");
            System.out.println("Digite 2 para JOGAR SEUS ITENS FORA");
            System.out.println("Digite 3 para IR ATÉ O MERCADO");
            System.out.println("Digite 4 para IR ATÉ O OFICINA");
            System.out.println("Digite qualquer outra valor para SAIR");
            op = sc.nextInt();

            switch(op){
                case 1: {
                    agente.percorreMatriz(matriz, random.nextInt(20), random.nextInt(20), itemList);
                    printMatriz(matriz);
                    fita = fita + "ccc";
                } break;
                case 2: {
                    agente.vaiLixao(listLocal,matriz, itemList);
                    fita = fita + "aa";
                    printMatriz(matriz);
                } break;
                case 3: {
                    agente.vaiMercado(listLocal,matriz, itemList);
                    fita = fita + "ef";
                    printMatriz(matriz);
                } break;
                case 4: {
                    agente.vaiOficina(listLocal,matriz, itemList);
                    fita = fita + "dd";
                    printMatriz(matriz);
                }break;
                default: {
                    System.out.println("Fita: \n" + fita);
                    return;
                }
            }
        }
        sc.close();
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
