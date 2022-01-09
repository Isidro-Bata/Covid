package covid;

import java.io.*;

public class Covid {
    
    //Metodo Menu
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

    //Metodo subMenu do Menu Estatistica
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
    
    static int[] qtdTestesRP(String d[][]) throws IOException {
        int total[] = new int[2];
        total[0] = total[1] = 0;
        
        for(int i = 0; i < d.length; i++){
            if(d[0][i] != null){
                if(d[0][i].equalsIgnoreCase("R"))
                    total[0]++;
                if(d[0][i].equalsIgnoreCase("P"))
                    total[1]++;
            }
        }
        return total;
    }
    
    static String[][] idadeMN(String d[][]) throws IOException {
        String pessoa[][] = new String[2][6];
        if(d[1][0] == null)
            return null;
        
        int auxm = Integer.parseInt(d[1][0]);
        int auxM = auxm;
        
        for(int i = 0; i < d.length; i++){
            if(d[1][i] != null){
                if(d[4][i].equalsIgnoreCase("P")){
                    if(auxm < Integer.parseInt(d[1][i])){
                        pessoa[0][0] = d[0][i];
                        pessoa[0][1] = d[1][i];
                        pessoa[0][2] = d[2][i];
                        pessoa[0][3] = d[3][i];
                        pessoa[0][4] = d[4][i];
                        pessoa[0][5] = d[5][i];
                        auxm = Integer.parseInt(pessoa[0][1]);
                    }
                    else{
                        pessoa[0][0] = d[0][i];
                        pessoa[0][1] = d[1][i];
                        pessoa[0][2] = d[2][i];
                        pessoa[0][3] = d[3][i];
                        pessoa[0][4] = d[4][i];
                        pessoa[0][5] = d[5][i];
                        auxm = Integer.parseInt(pessoa[0][1]);
                    }

                    if(auxM > Integer.parseInt(d[1][i])){
                        pessoa[1][0] = d[0][i];
                        pessoa[1][1] = d[1][i];
                        pessoa[1][2] = d[2][i];
                        pessoa[1][3] = d[3][i];
                        pessoa[1][4] = d[4][i];
                        pessoa[1][5] = d[5][i];
                        auxm = Integer.parseInt(pessoa[0][1]);
                    }
                    else{
                        pessoa[1][0] = d[0][i];
                        pessoa[1][1] = d[1][i];
                        pessoa[1][2] = d[2][i];
                        pessoa[1][3] = d[3][i];
                        pessoa[1][4] = d[4][i];
                        pessoa[1][5] = d[5][i];
                        auxm = Integer.parseInt(pessoa[0][1]);
                    }
                }
            }
        }
        return pessoa;
    }
    static int totalEmpresa(String d[][], int R, int P) throws IOException {
        int total = 1;
        for(int i = 0; i < d.length; i++){
            if(d[4][i] != null){
                if(d[4][i].equalsIgnoreCase("P"))
                   total += i*P;
                 
                if(d[4][i].equalsIgnoreCase("R"))
                    total += i*R;
            }
        }
        return total;
    }
    static int[] porcentagemPN(String d[][]) throws IOException {
        int total[] = new int[2];
        int p = 0, r = 0;
        if(d[4][0] == null)
            return null;
 
        for(int i = 0; i < d.length; i++){
            if(d[4][i] != null){
                if(d[4][i].equalsIgnoreCase("P"))
                    p++;
                 
                if(d[4][i].equalsIgnoreCase("R"))
                    r++;
            }
        }
        
        total[0] = p/100;
        total[1] = r/100;
        return total;
    }
    //Metodo para receber dados
    /*
        Parametros
        String d[][] - Recebe um array bidimensional
        int i - Posicao da insercao
    */
    static void receber(String d[][], int pos) throws IOException {
        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Tipo de Teste:");
        System.out.println("R - Rapido");
        System.out.println("P - PCR");
        char tipoTeste =  texto("Opcao: ",1).charAt(0);
        d[0][pos] = String.valueOf(tipoTeste);
        
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
        d[4][pos] = String.valueOf(resultado);
        
        System.out.println("Ja vacinou?");
        System.out.println("S - Sim");
        System.out.println("N - Nao");
 
        char vacina = texto("Opcao: ",1).charAt(0);
        d[5][pos] = "" + vacina;
    }
    
    //Metodo para visualizar dados
    /*
        Parametros
        String d[][] - Recebe um array bidimensional
        int i - Posicao do elemento desejado 
    */
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
    
    //Metodo para validar numeros
    /*
        Parametros
        String t - O texto que sera impresso
        int i - Tamanho minimo de numeros 
        int f - Tamanho maximo de numeros
    
        Retorno
        Tipo interio n
    */
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
    
    // Metodo para validar um caractere ou conjunto de caracteres
    /*
        Parametros
        String t - O texto que sera impresso
        int mTam - Tamanho ou numero de caracteres minimo 
    
        Retorno
        Tipo String um caractere ou conjunto de caracteres
    */
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
    
    // Metodo main
    /*
        Parametros
        String [] - Recebe array unidimensional de argumentos
    */
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
                            
                            if(idadeMN(dado) != null){
                                System.out.println("\t\tMENOR POSITIVO");
                                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                                "Tipo de Teste", "Idade", "Contacto",
                                "Genero", "Resultado", "Ja vacinou?");
                                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                                idadeMN(dado)[0][0].equals("R") ? "Rapido" : "PCR" , idadeMN(dado)[0][1], idadeMN(dado)[0][2], 
                                idadeMN(dado)[0][3].equals("M") ? "Masculino" : "Femenino", 
                                idadeMN(dado)[0][4].equals("P") ? "Positivo" : "Negativo", 
                                idadeMN(dado)[0][5].equals("S") ? "Sim" : "Nao");

                                    /*for(int i = 0; i < idadeMN(dado).length; i++){
                                    if(idadeMN(dado)[0][i] != null)
                                        visualizar(idadeMN(dado), i);
                                }*/
                                System.out.println("\t\tMAIOR POSITIVO");
                                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                                "Tipo de Teste", "Idade", "Contacto",
                                "Genero", "Resultado", "Ja vacinou?");
                                System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                                idadeMN(dado)[1][0].equals("R") ? "Rapido" : "PCR" , idadeMN(dado)[1][1], idadeMN(dado)[1][2], 
                                idadeMN(dado)[1][3].equals("M") ? "Masculino" : "Femenino", 
                                idadeMN(dado)[1][4].equals("P") ? "Positivo" : "Negativo", 
                                idadeMN(dado)[1][5].equals("S") ? "Sim" : "Nao");
                            }
                        break;
                        
              
                    }
               
            }
            System.out.println("\n");
        }while(op != 0);
        
    }
    
}
