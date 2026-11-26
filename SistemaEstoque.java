import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class SistemaEstoque {
    //definindo o tamanho maximo de motoristas que a empresa tem
        static final int MAX_MOTORISTAS = 30;
        
        static String informacoesMotorista = "Codigo;Nome;Cnh;Situacao";
        
        //arrays para os motoristas
        static int[] codigosMotorista = new int[MAX_MOTORISTAS];

        static String[] nomesMotoristas = new String[MAX_MOTORISTAS];

        static int[] categoriasCnh = new int[MAX_MOTORISTAS];
        static int[] situacoesMotoristas = new int[MAX_MOTORISTAS];

        static int qtdMotoristas = 0; //qtd = Quantidade

        // definindo o tamanho maximo da frota da empresa
        static final int MAX_VEICULOS = 20;
        
        static String informacoesVeiculo = "Placa;Modelo;Tipo;Capacidade_KG;Consumo_KML;Situacao";
        //arrays para os veiculos
        static String[] placas = new String[MAX_VEICULOS];
        static String[] modelos = new String[MAX_VEICULOS];
        
        static int[] tiposVeiculo = new int[MAX_VEICULOS];
        
        static double[] capacidadesKg = new double[MAX_VEICULOS];
        static double[] consumosKmL = new double[MAX_VEICULOS];

        static int[] situacoesVeiculo = new int[MAX_VEICULOS];        
        static int[] codigosVeiculo = new int[MAX_VEICULOS];

        static int qtdVeiculos = 0; //qtd = Quantidade 

        //defindo o tamanho maximo de entregas 
        static final int MAX_ENTREGAS = 300;
        
        static String informacoesEntregas = "Codigo;Destino;Peso_KG;Distancia_KM;Placa;Codigo_Motorista;Dia;Situacao";
        
        //arrays para as entregas
        static int[] codigosEntregas = new int[MAX_ENTREGAS];

        static String[] destinos = new String[MAX_ENTREGAS];

        static double[] pesosKg = new double[MAX_ENTREGAS];
        static double[] distanciasKm = new double[MAX_ENTREGAS];

        static int[] entVeiculo = new int[MAX_ENTREGAS]; //ent = entrega
        static int[] entMotorista = new int[MAX_ENTREGAS];
        static int[] entDia = new int[MAX_ENTREGAS];
        static int[] situacoesEntrega = new int[MAX_ENTREGAS];
        //as matrizes desse sistema
        static double[][] kmVeiculoDia = new double[MAX_VEICULOS][6];

        static int[][] entregasVeiculoDia = new int[MAX_VEICULOS][6];


        static int qtdEntregas = 0; //qtd = Quantidade 

    public static void main(String[] args) {
        //
        carregarDados();

        String resultado;
        int opcao = 0;

        do {
            System.out.println("\n##### Sistema de Estoque #####");
            System.out.println("( 1 ) Cadastrar Veiculo");
            System.out.println("( 2 ) Listar Veiculos");
            System.out.println("( 3 ) Alterar Situacao" );
            System.out.println("( 4 ) Cadastrar motorista");
            System.out.println("( 5 ) Gerenciar Motoristas");
            System.out.println("( 6 ) Cadastrar Entrega");
            System.out.println("( 7 ) Relatorio do Semanal");
            System.out.println("( 0 ) Sair");

            
            System.out.print("Escolha uma opção: ");
            
            opcao = lerInteiro();
            
            switch (opcao) {
                case 0:
                    System.out.println("Saindo do sistema...");
            
                    break;
                    
                case 1:
                    String placa, modelo, tipos;
                    double capacidadesKg = 0, consumosKml = 0;

                    System.out.println("##### qual a placa do veiculo ######");
                    System.out.print("coloque no seguinte formato (FFF0F00): ");
                    
                    do{
                        placa = lerTexto();
                        if (placa.length() != 7) {
                          System.out.println(escolhaErrada()); 
                           
                        } else {}
                    }while (placa.length() != 7);
                    
                    System.out.println("\n##### Qual o modelo quer cadastrar #####");
                    System.out.println("( 1 ) Utilitário");
                    System.out.println("( 2 ) Caminhão leve");
                    do{
                        modelo = lerTexto();
                    }while (modelo == "1" || modelo == "2");
                    
                    
                    System.out.println("\n##### Qual o tipo do veiculo #####");
                    tipos = lerTexto();
                    
                    System.out.println("##### capacidade de carga ######");
                    System.out.print("coloca no seguinte formato (100): ");

                    capacidadesKg = lerDecimal();  

                    System.out.println("##### consumo por km/h #####");
                    System.out.print("coloque no seguinte formato (100): ");
                    
                    consumosKml = lerDecimal();
                    
                    //salva atraves do metodo
                    resultado = cadastrarVeiculo(placa,modelo,tipos,capacidadesKg,consumosKml);                  

                    System.out.println(resultado);
                    break;

                case 2:
                    System.out.println("\n##### Frota Cadastrada #####");
                         
                    listarVeiculos();
                    
                    break;

                case 3:
                    break;
                case 4:
                    String nome;
                    int codigo = 0, cnh = 0;

                    System.out.println("##### Qual e o codigo do motorista ######");
                    do{
                        codigo = lerInteiro();
                        if (codigo < 1 || codigo > 999) {
                          System.out.println("coloca um codigo valido ex:(1)"); 

                        } else {}
                    }while (codigo < 1 || codigo > 999);



                    System.out.println("##### Qual e o nome motorista ######");
                    nome = lerTexto();
                    
                    System.out.println("##### Qual a Categoria do CNH #####");
                    System.out.println("( 1 ) CNH B");
                    System.out.println("( 2 ) CNH C");
                    System.out.println("( 3 ) CNH D");

                    do{
                        cnh = lerInteiro();
                        if (cnh < 1 || cnh > 3) {
                          System.out.println(escolhaErrada()); 
                           
                        } else {}
                    }while (cnh < 1 || cnh > 3);
                                                          
                    //sc.nextLine(); // Limpa o buffer do scanner, porque esta trocando a entrada de numero para String
                    
                    //salva atraves do metodo
                    cadastrarMotorista(codigo, nome, cnh);

                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;

                default:
                    System.out.println(escolhaErrada());

                    break;
            }
        }while (opcao != 0);
        
    }
    public static void carregarDados(){
        String linha;
        
        //lendo o veiculo.txt
        try(BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"))) {
            linha = br.readLine();
           
            while ((linha = br.readLine()) != null) {
                String[] bloco = linha.split(";");
                
                //colocando os dados em seus respectivos arreys
                placas[qtdVeiculos] = bloco[0];
                modelos[qtdVeiculos] = bloco[1];
                
                tiposVeiculo[qtdVeiculos] = Integer.parseInt(bloco[2]);
                
                capacidadesKg[qtdVeiculos] = Double.parseDouble(bloco[3]);
                consumosKmL[qtdVeiculos] = Double.parseDouble(bloco[4]);

                situacoesVeiculo[qtdVeiculos] = Integer.parseInt(bloco[5]);

                qtdVeiculos++;
            }
        } catch (IOException e) {
            System.out.println("erro de leitura do arquivo: veiculos.txt");

        }

        //lendo motoristas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("motoristas.txt"))){
            linha = br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] bloco = linha.split(";");
                //colocando os dados em seus respectivos arreys
                codigosMotorista[qtdMotoristas] = Integer.parseInt(bloco[0]);
                
                nomesMotoristas[qtdMotoristas] = bloco[1];

                categoriasCnh[qtdMotoristas] = Integer.parseInt(bloco[2]);
                situacoesMotoristas[qtdMotoristas] = Integer.parseInt(bloco[3]);
                
                qtdMotoristas++;
            }
        } catch (IOException e) {
            System.out.println("erro de leitura do arquivo: motoristas.txt");
        }        
        //lendo entregas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("entregas.txt"))){
            linha = br.readLine();

            while ((linha = br.readLine()) != null){
                String[] bloco = linha.split(";");

                codigosEntregas[qtdEntregas] = Integer.parseInt(bloco[0]);

                destinos[qtdEntregas] = bloco[1];

                pesosKg[qtdEntregas] = Double.parseDouble(bloco[2]);
                distanciasKm[qtdEntregas] = Double.parseDouble(bloco[3]);
            
                entVeiculo[qtdEntregas] = Integer.parseInt(bloco[4]);
                entMotorista[qtdEntregas] = Integer.parseInt(bloco[5]);
                entDia[qtdEntregas] = Integer.parseInt(bloco[6]);
                situacoesEntrega[qtdEntregas] = Integer.parseInt(bloco[7]);

                qtdEntregas++;
            }
        } catch (IOException e) {
            System.out.println("erro de leitura do arquivo: entregas.txt");
        }
        
    }
    static int lerInteiro(){
        Scanner sc = new Scanner(System.in);
        
        int valor;
        
        while (true) {
            try {
                String entrada = sc.nextLine();
                
                entrada = entrada.replace(",", ".");
                
                valor = Integer.parseInt(entrada);
                
                return valor;
            } catch (NumberFormatException e) {
                System.out.println(escolhaErrada());

                }
            sc.close();
            }
        }

    static double lerDecimal() {
        Scanner sc = new Scanner(System.in);

        double valor;

        while (true) {

            try {

                String entrada = sc.nextLine();

                entrada = entrada.replace(",", ".");

                valor = Double.parseDouble(entrada);

                return valor;

            } catch (NumberFormatException e) {
                System.out.println(escolhaErrada());

            }
        sc.close();
        }
    }
    static String lerTexto(){
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            
            try {
                
                String entrada = sc.nextLine().trim();
                //verifica se a entrada não e vazia
                if (entrada != null) {
                   return entrada;  
                } else {
                    System.out.println("entrada vazia, tenta novamente");

                }
                
            } catch (Exception e) {
                System.out.println(escolhaErrada());
            }
        sc.close();
        }
    }    

    // Cadastrar veículo (RF1)
    public static String cadastrarVeiculo(String placa, String modelo, String tipo, Double cabacidadeMax, Double consumoKML){
        if (qtdVeiculos >= MAX_VEICULOS) {
            return "Não ha espaco na frota";
            
        } else {
            //colocando todas as informacoes no arrey, em sua posicao especifica 
            placas[qtdVeiculos] = placa;
            modelos[qtdVeiculos] = modelo;

            tiposVeiculo[qtdVeiculos] = Integer.parseInt(tipo);

            capacidadesKg[qtdVeiculos] = cabacidadeMax;
            consumosKmL[qtdVeiculos] = consumoKML;

            situacoesVeiculo[qtdVeiculos] = 1;

            qtdVeiculos++;
            // o try catch é caso não consiga gravar o arquivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("veiculos.txt"))){
                // escreve o cabecario do arquivo
                bw.write(informacoesVeiculo);
                // indo para a proxima linha
                bw.newLine();

                //escreve toda a informacao que esta no arreys, reescrevendo
                for(int a = 0; a < qtdVeiculos; a ++) {
                    bw.write(placas[a] +";"+ modelos[a] +";"+ tiposVeiculo[a] +";"+ capacidadesKg[a] +";"+ consumosKmL[a] +";"+ situacoesVeiculo[a]);
                    bw.newLine();

                } 
            }catch (IOException | NumberFormatException e) {
                System.out.println("erro de escrever no arquivo: veiculos.txt");
                } 
            return "\nVeículo cadastrado com sucesso!";  
        }
       
    }
    //listar veiculos (RF2)
    public static void listarVeiculos(){
        try (BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"))){
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
            
        } catch (IOException e) {
                System.out.println("erro em ler arquivo: veiculos.txt");
        }
    }
    public static String escolhaErrada(){
        return "\nOpção invalida, Tenta novamente"; //mensagem basica de erro
    }
    //cadastra motorista RF4
    public static String cadastrarMotorista(int codigo, String nome, int cnh){
        if (qtdMotoristas >= MAX_MOTORISTAS ) {
            return "Não ha espaco na empresa";
    
        } else {
                //colocando os dados no arrey
                codigosMotorista[qtdMotoristas] = codigo;
                
                nomesMotoristas[qtdMotoristas] = nome;
                
                categoriasCnh[qtdMotoristas] = cnh;
                
                situacoesMotoristas[qtdMotoristas] = 1;
            
                qtdMotoristas++;
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("motoristas.txt"))){
                //escrever o cabecario
                bw.write(informacoesMotorista);
                // mudar de linha 
                bw.newLine(); 
                
                //repetindo, guarda todo o arrey
                for(int a = 0; a < qtdMotoristas; a ++) {
                bw.write(codigosMotorista[a] +";"+ nomesMotoristas[a] +";"+ categoriasCnh[a] +";"+ situacoesMotoristas[a]);
                //pular linha
                bw.newLine();
 
                }    
            } 
            
            catch (IOException | NumberFormatException e) {
                System.out.println("erro de escrita do arquivo: veiculos.txt");
                
                }
            return "\nMotorista cadastrado com sucesso!";
        }
        
    }
    static void cadastrarEntrega(){

    }
}