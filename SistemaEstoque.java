import java.util.Scanner;

class Veiculo {
    int tamanhoFrota = 12;
    int[][] idEntrega = new int[12][];
    String placaVeiculo;
    String modeloVeiculo;
    int anoFabricacao;
    double capacidadeCarga;
    String tipoMedida;
    boolean statusManutencao = false;

    public void adicionarCarro(String placaVeiculo, String modeloVeiculo, int anoFabricacao, double capacidadeCarga, String tipoMedida) {
        this.placaVeiculo = placaVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.anoFabricacao = anoFabricacao;
        this.capacidadeCarga = capacidadeCarga;
        this.statusManutencao = false; 
        this.tipoMedida = tipoMedida;
    }

    public void exibirDados() {
        System.out.println("Placa: " + placaVeiculo + " | Modelo: " + modeloVeiculo + 
                           " | Ano: " + anoFabricacao + " | Capacidade: " + capacidadeCarga + " " + tipoMedida);
    }
}

public class SistemaEstoque {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Array para armazenar os veículos cadastrados
        Veiculo[] frota = new Veiculo[12];
        int totalVeiculos = 0;
        int opcao = 0;

        // Laço para manter o menu ativo até a opção 4 (Exit)
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
                    if (totalVeiculos >= frota.length) {
                        System.out.println("Limite da frota atingido!");
                        break;
                    }

                    Veiculo veiculo = new Veiculo();
                    System.out.println("\nQual o veiculo quer cadastrar");
                    System.out.println("( 1 ) Van");
                    System.out.println("( 2 ) Vuc");
                    System.out.println("( 3 ) Caminhao");
                    int opcaoModelo = sc.nextInt();
                    
                    switch (opcaoModelo) {
                        case 1:
                            veiculo.modeloVeiculo = "Van";
                            break;
                        case 2:
                            veiculo.modeloVeiculo = "Vuc";
                            break;
                        case 3:
                            veiculo.modeloVeiculo = "Caminhao";
                            break;
                        default:
                            veiculo.modeloVeiculo = "Outro";
                            break;
                    }
                
                    sc.nextLine(); // Limpa o buffer do scanner
                    System.out.println("##### qual a placa do veiculo ######");
                    System.out.print("coloque no seguinte formato (FFF0F00): ");
                    veiculo.placaVeiculo = sc.nextLine();
                    
                    System.out.println("##### ano do veiculo ######");
                    System.out.print("coloca no seguinte formato (1999): ");
                    veiculo.anoFabricacao = sc.nextInt();

                    System.out.println("##### capacidade de carga #####");
                    System.out.print("coloque no seguinte formato (100): ");
                    veiculo.capacidadeCarga = sc.nextDouble();
                    
                    sc.nextLine(); // Limpa o buffer do scanner
                    System.out.print("Unidade de medida (kg ou t): ");
                    veiculo.tipoMedida = sc.nextLine();

                    // Repete até a entrada ser 't' ou 'kg'
                    while (!veiculo.tipoMedida.equalsIgnoreCase("t") && !veiculo.tipoMedida.equalsIgnoreCase("kg")) {
                        System.out.print("Unidade inválida. Repita novamente (kg ou t): ");
                        veiculo.tipoMedida = sc.nextLine();
                    }

                    // Salva o veículo no vetor da frota
                    frota[totalVeiculos] = veiculo;
                    totalVeiculos++;

                    System.out.println("Veículo cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Funcionalidade de remoção a ser implementada.");
                    break;

                case 3:
                    System.out.println("\n##### Frota Cadastrada #####");
                    if (totalVeiculos == 0) {
                        System.out.println("Nenhum veículo cadastrado.");
                    } else {
                        for (int i = 0; i < totalVeiculos; i++) {
                            frota[i].exibirDados();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        
        sc.close();
    }
}