import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class SistemaEstoque {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n##### Sistema de Estoque #####");
            System.out.println("( 1 ) Cadastrar novos veiculos ");
            System.out.println("( 2 ) Remover novos veiculos");
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
                        bw.write(modeloVeiculo +" | "+ placaVeiculo +" | "+ anoFabricacao +" | "+ capacidadeCarga +" | "+ tipoMedida +" | ");
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

                case 4:
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
}