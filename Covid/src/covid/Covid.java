package covid;

import java.io.*;

public class Covid {
    
    static byte menu() throws IOException {
        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
        byte op;
        
        System.out.println("1. Receber dados do Paciente");
        System.out.println("2. Visualizar todos os dados");
        System.out.println("3. Estatistica");
        System.out.println("4. Dados do programador");
        System.out.println("0. Sair");
        do {
            System.out.print("Opcao: ");
            op = Byte.parseByte(x.readLine());
            if(op < 0 || op > 4)
                System.out.println("Opcao invalida!!!");
        }while(op < 0 || op > 4);
        return op;
    }

    static byte subMenu() throws IOException {
        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
        byte op;
        
        System.out.println("1. Qtd de testes feitos para cada tipo");
        System.out.println("2. Total de Positivos e Negativos");
        System.out.println("3. Valor total obtido pela Empresa");
        System.out.println("4. Pessoa mais velha e mais nova contaminada");
        System.out.println("0. Voltar");
        do {
            System.out.print("Opcao: ");
            op = Byte.parseByte(x.readLine());
            if(op < 0 || op > 4)
                System.out.println("Opcao invalida!!!");
        }while(op < 0 || op > 4);
        return op;
    }
    
    static void receber(String d[][], int pos) throws IOException {
        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Tipo de Teste:");
        System.out.println("R - Rapido");
        System.out.println("P - PCR");
        char tipoTeste =  texto("Opcao: ",1).charAt(0);
        d[0][pos] = "" + tipoTeste;
        
        byte idade = (byte) num("Idade: ",0, 120);
        d[1][pos] = "" + idade;
        
        String contacto = texto("Contacto: ",9);
        d[2][pos] = contacto;
        
        System.out.println("Genero:");
        System.out.println("M - Masculino");
        System.out.println("F - Femenino");
        char genero = texto("Opcao: ",1).charAt(0);
        d[3][pos] = "" + genero;
        
        System.out.println("Resultado:");
        System.out.println("P - Positivo");
        System.out.println("N - Negativo");
        char resultado = texto("Opcao: ",1).charAt(0);
        d[4][pos] = "" + resultado;
        
        System.out.println("Ja vacinou?");
        System.out.println("S - Sim");
        System.out.println("N - Nao");
 
        char vacina = texto("Opcao: ",1).charAt(0);
        d[5][pos] = "" + vacina;
    }
    
    static void visualizar(String d[][], int pos) {
        //String format = ;
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                            "Tipo de Teste", "Idade", "Contacto",
                            "Genero", "Resultado", "Ja vacinou?");
        for(int i=0;i<pos; i++) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                              d[0][i].equals("R") ? "Rapido" : "PCR" , d[1][i], d[2][i], 
                              d[3][i].equals("M") ? "Masculino" : "Femenino", 
                              d[4][i].equals("P") ? "Positivo" : "Negativo", 
                              d[5][i].equals("S") ? "Sim" : "Nao");
        }
    }
    
    public static double num(String t, int i, int f) throws IOException {
        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
        double n=0;
        do {
            System.out.print(t);
            n = Double.parseDouble(x.readLine());
            if(n < i || n > f)
                System.out.println("Input Invalido");
        }while(n < i || n > f);
        return n;
    }
    
    public static String texto(String t, int mTam) throws IOException {
        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
        String txt = "";
        do {
            System.out.print(t);
            txt = x.readLine();
            if(txt.isEmpty() || txt.length() < mTam)
                System.out.println("Input Invalido");
        }while(txt.isEmpty() || txt.length() < mTam);
        return txt;
    }
    
    public static void main(String[] args) throws IOException {
        String dado[][] =  new String[6][50];
        short pos = 0;
        
        final short R = 1000;
        final short P = 3500;
        
        short qtdPcr;
        short qtdR;
        
        short qtdP;
        short qtdN;
        
        short qtdV;
        
        byte op, op1;
        
        do {
            op = menu();
            switch(op) {
                case 1:
                    receber(dado,pos++);
                break;
                case 2:
                    visualizar(dado,pos);
                break;
                case 3:
                    System.out.println("\n");
                    op1 = subMenu();
                    switch(op1) {
                        case 1:
                        
                        break;
                        case 2:
                            
                        break;
                        case 3:
                            
                        break;
                        case 4:
                            
                        break;
                    }
                break;
                case 4:
                    
                break;
            }
            System.out.println("\n");
        }while(op != 0);
        
    }
    
}
