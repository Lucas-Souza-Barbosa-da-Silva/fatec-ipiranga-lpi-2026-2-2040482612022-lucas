import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class SistemaEstoque {
    //definindo o tamanho maximo de motoristas que a empresa tem
        final int MAX_MOTORISTAS = 30;
        
        String informacoesMotorista = "Codigo;Nome;Cnh;Situacao";
        
        //arrays para os motoristas
        int[] codigosMotorista = new int[MAX_MOTORISTAS];

        String[] nomesMotoristas = new String[MAX_MOTORISTAS];

        int[] categoriasCnh = new int[MAX_MOTORISTAS];
        int[] situacoesMotoristas = new int[MAX_MOTORISTAS];

        int qtdMotoristas = 0; //qtd = Quantidade

        // definindo o tamanho maximo da frota da empresa
        final int MAX_VEICULOS = 20;
        
        String informacoesVeiculo = "Placa;Modelo;Tipo;Capacidade_KG;Consumo_KML;Situacao";
        //arrays para os veiculos
        String[] placas = new String[MAX_VEICULOS];
        String[] modelos = new String[MAX_VEICULOS];
        
        int[] tiposVeiculo = new int[MAX_VEICULOS];
        
        double[] capacidadesKg = new double[MAX_VEICULOS];
        double[] consumosKmL = new double[MAX_VEICULOS];

        int[] situacoesVeiculo = new int[MAX_VEICULOS];        
        int[] codigosVeiculo = new int[MAX_VEICULOS];

        int qtdVeiculos = 0; //qtd = Quantidade 

        //defindo o tamanho maximo de entregas 
        final int MAX_ENTREGAS = 300;
        
        String informacoesEntregas = "Codigo;Destino;Peso_KG;Distancia_KM;Placa;Codigo_Motorista;Dia;Situacao";
        
        //arrays para as entregas
        int[] codigosEntregas = new int[MAX_ENTREGAS];

        String[] destinos = new String[MAX_ENTREGAS];

        double[] pesosKg = new double[MAX_ENTREGAS];
        double[] distanciasKm = new double[MAX_ENTREGAS];

        int[] entVeiculo = new int[MAX_ENTREGAS]; //ent = entrega
        int[] entMotorista = new int[MAX_ENTREGAS];
        int[] entDia = new int[MAX_ENTREGAS];
        int[] situacoesEntrega = new int[MAX_ENTREGAS];
        //as matrizes desse sistema
        double[][] kmVeiculoDia = new double[MAX_VEICULOS][6];

        int[][] entregasVeiculoDia = new int[MAX_VEICULOS][6];


        int qtdEntregas = 0; //qtd = Quantidade 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //
        carregarDados(20, 30, 300);

        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n##### Sistema de Estoque #####");
            System.out.println("( 1 ) Cadastrar");
            System.out.println("( 2 ) Remover");
            System.out.println("( 3 ) Verificar a frota" );
            System.out.println("( 4 ) Exit ");
            System.out.print("Escolha uma opção: ");
            
            opcao = sc.nextInt();
            
            switch (opcao) {
                case 1:
                    
                    System.out.println("\nQual o veiculo quer cadastrar");
                    System.out.println("( 1 ) Van");
                    System.out.println("( 2 ) Vuc");
                    System.out.println("( 3 ) Caminhao");
                    int modeloVeiculo = sc.nextInt();
                    
                    sc.nextLine(); // Limpa o buffer do scanner, nesse caso para não pegar o espaço

                    System.out.println("##### qual a placa do veiculo ######");
                    System.out.print("coloque no seguinte formato (FFF0F00): ");
                    String placaVeiculo = sc.nextLine();
                    
                    System.out.println("##### ano do veiculo ######");
                    System.out.print("coloca no seguinte formato (1999): ");
                    int anoFabricacao = sc.nextInt();

                    System.out.println("##### capacidade de carga #####");
                    System.out.print("coloque no seguinte formato (100): ");
                    Double capacidadeCarga = sc.nextDouble();
                    
                    sc.nextLine(); // Limpa o buffer do scanner, porque esta trocando a entrada de numero para String
                    
                    System.out.print("Unidade de medida (kg ou t): ");
                    String tipoMedida = sc.nextLine();

                    while (!tipoMedida.equalsIgnoreCase("t") && !tipoMedida.equalsIgnoreCase("kg")) {
                        System.out.print("Unidade inválida. Repita novamente (kg ou t): ");
                        tipoMedida = sc.nextLine();
                    }

                    // Salva o veículo no vetor da frota
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter("Frota_Da_Empresa.txt"))){
                        bw.write(modeloVeiculo +" ; "+ placaVeiculo +" ; "+ anoFabricacao +" ; "+ capacidadeCarga +" ; "+ tipoMedida +" ; ");
                        bw.newLine();

                    } catch (IOException e) {
                        // TODO: handle exception
                    }                    

                    System.out.println("Veículo cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n##### Frota Cadastrada #####");
                    
                    try (BufferedReader br = new BufferedReader(new FileReader("Frota_Da_Empresa.txt"))){
                    String linha;
                    while ((linha = br.readLine()) != null) {
                            System.out.println(linha);      
                        }
                    } catch (IOException e) {
                    // TODO: handle exception
                    }        
                   
                    
                    break;

                case 3:
                    try (BufferedReader br = new BufferedReader(new FileReader("Frota_Da_Empresa.txt"))){
                    String linha;
                    while ((linha = br.readLine()) != null) {
                            System.out.println(linha);      
                        }
                    } catch (IOException e) {
                    // TODO: handle exception
                    }
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
            
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
                    System.out.println("Tenta outra opção.");

                    break;
            }
        }
        
        sc.close();
    }
    public static void carregarDados(int MAX_VEICULOS, int MAX_MOTORISTAS, int MAX_ENTREGAS){
        int contador = 0;
        //lendo o veiculo.txt
        try(BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] bloco = linha.split(";");
                
                //colocando os dados em seus respectivos arreys
                placas[contador] = bloco[0];
                modelos[contador] = bloco[1];
                
                tiposVeiculo[contador] = Integer.parseInt(bloco[2]);
                
                capacidadesKg[contador] = Double.parseDouble(bloco[3]);
                consumosKmL[contador] = Double.parseDouble(bloco[4]);

                situacoesVeiculo[contador] = Integer.parseInt(bloco[5]);

                contador++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        contador = 0;

        //lendo motoristas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("motoristas.txt"))){
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] bloco = linha.split(";");
                //colocando os dados em seus respectivos arreys
                codigosMotorista[contador] = Integer.parseInt(bloco[0]);
                
                nomesMotoristas[contador] = bloco[1];

                categoriasCnh[contador] = Integer.parseInt(bloco[2]);
                situacoesMotoristas[contador] = Integer.parseInt(bloco[3]);
                
                contador++;
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        contador = 0;
        
        //lendo entregas.txt
        try (BufferedReader br = new BufferedReader(new FileReader("entregas.txt"))){
            String linha;
            while ((linha = br.readLine()) != null){
                String[] bloco = linha.split(";");

                codigosEntregas[] = Integer.parseInt(bloco[0]);

                destinos[] = bloco[1];

                pesosKg[] = Double.parseDouble(bloco[2]);
                distanciasKm = Double.parseDouble(bloco[3]);
            
                entVeiculo = Integer.parseInt(bloco[4]);
                entMotorista = Integer.parseInt(bloco[5]);
                entDia = Integer.parseInt(bloco[6]);
                situacoesEntrega = Integer.parseInt(bloco[7]);

                contador++;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
    
    }
}