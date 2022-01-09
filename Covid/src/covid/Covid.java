package covid;

import java.io.*;
import java.text.DecimalFormat;

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
        System.out.println("2. Percetagem de Positivos e Negativos");
        System.out.println("3. Valor total obtido pela Empresa");
        System.out.println("4. Pessoa mais velha e mais nova contaminada");
        System.out.println("5. Quantidade de pessoas que precisam de vacina");
        System.out.println("0. Voltar");
        do {
            System.out.print("Opcao: ");
            op = Byte.parseByte(x.readLine());
            if(op < 0 || op > 5)
                System.out.println("Opcao invalida!!!");
        }while(op < 0 || op > 5);
        return op;
    }
    
    // calcula a qtd de testes rapidos
    static short qtdTestesR(String d[][], int qtd) {
        short total = 0;
        
        for(int i = 0; i < qtd; i++){
            if(d[0][i].equalsIgnoreCase("R"))
                total++;
        }
        return total;
    }
    
    // calcula a qtd de testes pcr
    static short qtdTestesP(String d[][], int qtd) {
        short total = 0;
        
        for(int i = 0; i < qtd; i++){
            if(d[0][i].equalsIgnoreCase("P"))
                total++;
        }
        return total;
    }
    
    // retorna o indice da menor pessoa contaminada
    static short contaminadoMenor(String d[][], int qtd) {
        short pos = 0;
        
        byte auxm = Byte.parseByte(d[1][0]);
        
        for(int i = 1; i < qtd; i++){
            if(d[4][i].equalsIgnoreCase("P")){ // verifica se e a pessoa esta contaminada
                if(auxm > Byte.parseByte(d[1][i])) { // verifica se a pessoa e a maior
                    pos =(short) i; // actualiza o indice
                    auxm = Byte.parseByte(d[1][i]); // actualiza a idade da maior pessoa contamidada
                }
            }
        }
        
        return pos;
    }
    
    // retorna o indice da maior pessoa contaminada
    static short contaminadoMaior(String d[][], int qtd) {
        short pos = 0;
        
        byte auxm = Byte.parseByte(d[1][0]);
        
        for(int i = 1; i < qtd; i++){
            if(d[4][i].equalsIgnoreCase("P")){ // verifica se e a pessoa esta contaminada
                if(auxm < Byte.parseByte(d[1][i])) { // verifica se a pessoa e a maior
                    pos =(short) i; // actualiza o indice
                    auxm = Byte.parseByte(d[1][i]); // actualiza a idade da maior pessoa contamidada
                }
            }
        }
        
        return pos;
    }
    
    // calcula o total da empresa
    static int totalEmpresa(String d[][], int qtd) {
        final short R = 1000;
        final short P = 3500;
        
        int total = 0;
        
        for(int i = 0; i < qtd; i++){
            if(d[0][i].equalsIgnoreCase("P"))
               total += P;

            if(d[0][i].equalsIgnoreCase("R"))
                total += R;
        }
        return total;
    }
    
    // calcula a percentagem de pessoas posetivas
    static float porcentagemP(String d[][], int qtd) {
        int p = 0;
 
        for(int i = 0; i < qtd; i++){
            if(d[4][i].equalsIgnoreCase("P"))
                p++;
        }
        
        return (float) p/(qtdTestesR(d,qtd)+qtdTestesP(d,qtd));
    }
    
    // calcula a percentagem de pessoas negativas
    static float porcentagemN(String d[][], int qtd) {
        int n = 0;
 
        for(int i = 0; i < qtd; i++){
            if(d[4][i].equalsIgnoreCase("N"))
                n++;
        }
        return (float)n/(qtdTestesR(d,qtd)+qtdTestesP(d,qtd));
    }
    
    // calcula o numero de pessoas que precisam de vacina
    static short naoVacinados(String d[][], int qtd) {
        short n = 0;
        
        for(int i=0; i < qtd; i++)
            if(d[5][i].equalsIgnoreCase("N"))
                n++;
        
        return n;
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
        char tipoTeste;
        // leitura e validacao da entrada da tipo de teste
        do {
            tipoTeste =  texto("Opcao: ",1).charAt(0);
            if(tipoTeste != 'R' && tipoTeste != 'P')
                System.out.println("Opcao invalida!");
        }while(tipoTeste != 'R' && tipoTeste != 'P');
        d[0][pos] = String.valueOf(tipoTeste);
        
        byte idade = (byte) num("Idade: ",0, 120);
        d[1][pos] = "" + idade;
        
        String contacto = texto("Contacto: ",9);
        d[2][pos] = contacto;
        
        System.out.println("Genero:");
        System.out.println("M - Masculino");
        System.out.println("F - Femenino");
        char genero;
        // leitura e validacao da entrada da genero
        do {
            genero = texto("Opcao: ",1).charAt(0);
            if(genero != 'M' && genero != 'F')
                System.out.println("Opcao invalida!");
        }while(genero != 'M' && genero != 'F');
        d[3][pos] = "" + genero;
        
        System.out.println("Resultado:");
        System.out.println("P - Positivo");
        System.out.println("N - Negativo");
        char resultado;
        // leitura e validacao da entrada da resultado
        do {
            resultado = texto("Opcao: ",1).charAt(0);
            if(resultado != 'P' && resultado != 'N')
                System.out.println("Opcao invalida!");
        }while(resultado != 'P' && resultado != 'N');
        d[4][pos] = String.valueOf(resultado);
        
        System.out.println("Ja vacinou?");
        System.out.println("S - Sim");
        System.out.println("N - Nao");
        char vacina;
        // leitura e validacao da entrada da vacina
        do {
            vacina = texto("Opcao: ",1).charAt(0);
            if(vacina != 'S' && vacina != 'N')
                System.out.println("Opcao invalida!");
        }while(vacina != 'S' && vacina != 'N');
        d[5][pos] = "" + vacina;
    }
    
    //Metodo para visualizar dados
    /*
        Parametros
        String d[][] - Recebe um array bidimensional
        int qtd - qtd de elemento disponiveis no array 
    */
    static void visualizar(String d[][], int qtd) {
        // formatacao da tabela
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                            "Tipo de Teste", "Idade", "Contacto",
                            "Genero", "Resultado", "Ja vacinou?");
        for(int i=0;i<qtd; i++) {
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                              d[0][i].equals("R") ? "Rapido" : "PCR" , d[1][i], d[2][i], 
                              d[3][i].equals("M") ? "Masculino" : "Femenino", 
                              d[4][i].equals("P") ? "Positivo" : "Negativo", 
                              d[5][i].equals("S") ? "Sim" : "Nao");
        }
    }
    
    // visualiza um elemento do array numa posicao especifica
    static void visIndice(String d[][], int ind) {
        // formatacao da tabela
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                            "Tipo de Teste", "Idade", "Contacto",
                            "Genero", "Resultado", "Ja vacinou?");
        
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n",
                          d[0][ind].equals("R") ? "Rapido" : "PCR" , d[1][ind], d[2][ind], 
                          d[3][ind].equals("M") ? "Masculino" : "Femenino", 
                          d[4][ind].equals("P") ? "Positivo" : "Negativo", 
                          d[5][ind].equals("S") ? "Sim" : "Nao");

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
        short numElemento = 0;
        
        DecimalFormat t = new DecimalFormat("###,###.00 Mt");
        
        byte op, op1;
        
        do {
            op = menu();
            switch(op) {
                case 1:
                    receber(dado,numElemento++);
                break;
                case 2:
                    visualizar(dado,numElemento);
                break;
                case 3:
                    System.out.println("\n");
                    op1 = subMenu();
                    switch(op1) {
                        case 1:
                            short qtdPcr = qtdTestesP(dado,numElemento);
                            short qtdR = qtdTestesR(dado,numElemento);
                            
                            System.out.println("\nPCR: " + qtdPcr + "\nRapido: " + qtdR +"\n");
                        break;
                        case 2:
                            float perP = porcentagemP(dado,numElemento);
                            float perN = porcentagemN(dado,numElemento);
                            
                            System.out.println("\nPosetivos: " + perP + "%\nNegativos: " + perN + "%\n");
                        break;
                        case 3:
                            System.out.println();
                            System.out.println("Total da Empresa: " + t.format(totalEmpresa(dado,numElemento)));
                        break;
                        case 4:
                            System.out.println("\n\tA pessoa mais velha contaminada:\n");
                            
                            short mo = contaminadoMaior(dado,numElemento);
                            
                            visIndice(dado,mo);
                            
                            System.out.println("\n\tA mais nova contaminada:\n");
                            
                            short mn = contaminadoMenor(dado,numElemento);
                            
                            visIndice(dado,mn);
                            
                            System.out.println();
                        break;
                        case 5:
                            System.out.println("\nNumero de pessoas: " + naoVacinados(dado,numElemento) + " Pessoas\n");
                        break;
                    }
            }
            System.out.println("\n");
        }while(op != 0);
        
    }
    
}
