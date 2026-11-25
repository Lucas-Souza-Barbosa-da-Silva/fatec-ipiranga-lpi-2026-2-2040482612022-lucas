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
        Scanner sc = new Scanner(System.in);
        //
        carregarDados();

        int opcao = 0;

        while (opcao != 0) {
            System.out.println("\n##### Sistema de Estoque #####");
            System.out.println("( 1 ) Cadastrar Veiculo");
            System.out.println("( 2 ) Listar Veiculos");
            System.out.println("( 3 ) Alterar Situacao" );
            System.out.println("( 4 ) Cadastrar motorista");
            System.out.println("( 5 ) Gerenciar Motoristas");
            System.out.println("( 6 ) Cadastrar Entrega");
            System.out.println("( 7 ) Relatorio do Semanal");

            
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            
            sc.nextLine(); //limpar o buffer , quebra de linha

            switch (opcao) {
                case 0:
                    System.out.println("Saindo do sistema...");
            
                    break;
                    
                case 1:
                    System.out.println("##### qual a placa do veiculo ######");
                    System.out.print("coloque no seguinte formato (FFF0F00): ");
                    String placa = sc.nextLine();
                    
                    System.out.println("\n##### Qual o modelo quer cadastrar #####");
                    System.out.println("( 1 ) Fiorino, tem menos capacidade sendo mais economico na gasolina");
                    System.out.println("( 2 ) Delivery, tem mais capacidade sendo mais gastao na gasolina");
                    String modelo = sc.nextLine();
                    
                    sc.nextLine(); // Limpa o buffer do scanner, nesse caso para não pegar o espaço

                    System.out.println("\n##### Qual o tipo do veiculo #####");
                    String tipos = sc.nextLine();
                    
                    System.out.println("##### capacidade de carga ######");
                    System.out.print("coloca no seguinte formato (100): ");
                    double capacidadesKg = sc.nextDouble();

                    System.out.println("##### consumo por km/h #####");
                    System.out.print("coloque no seguinte formato (100): ");
                    Double consumosKml = sc.nextDouble();

                    sc.nextLine(); // Limpa o buffer do scanner, porque esta trocando a entrada de numero para String
                    
                    //salva atraves do metodo
                    cadastrarVeiculo(placa,modelo,tipos,capacidadesKg,consumosKml);                  

                    System.out.println("Veículo cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n##### Frota Cadastrada #####");
                         
                    listarVeiculos();
                    
                    break;

                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;

                default:
                    System.out.println("Opção inválida!");
                    System.out.println("Tenta outra opção.");

                    break;
            }
        }
        
        sc.close();
    }
    public static void carregarDados(){
        String linha,cabecario;
        
        //lendo o veiculo.txt
        try(BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"))) {
            cabecario = br.readLine();
           
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
        } catch (Exception e) {
            // TODO: handle exception
        }

        //lendo motoristas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("motoristas.txt"))){
            cabecario = br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] bloco = linha.split(";");
                //colocando os dados em seus respectivos arreys
                codigosMotorista[qtdMotoristas] = Integer.parseInt(bloco[0]);
                
                nomesMotoristas[qtdMotoristas] = bloco[1];

                categoriasCnh[qtdMotoristas] = Integer.parseInt(bloco[2]);
                situacoesMotoristas[qtdMotoristas] = Integer.parseInt(bloco[3]);
                
                qtdMotoristas++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }        
        //lendo entregas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("entregas.txt"))){
            cabecario = br.readLine();

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

        } catch (Exception e) {
            // TODO: handle exception
        }
    
    }
    public static void cadastrarVeiculo(String placa, String Modelo, String Tipo, Double CabacidadeMax, Double ConsumoKML){
        String situcaoVeiculo = "Disponivel";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("veiculos.txt"))){
            
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    
    public static void listarVeiculos(){
        try (BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"))){
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}