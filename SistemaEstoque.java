import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class SistemaEstoque {
    // definindo o tamanho maximo de motoristas que a empresa tem
        static final int MAX_MOTORISTAS = 30;
        // cabecario do motoristas.txt
        static String informacoesMotorista = "Codigo;Nome;Cnh;Situacao";
        // arrays para os motoristas
        static int[] codigosMotorista = new int[MAX_MOTORISTAS];

        static String[] nomesMotoristas = new String[MAX_MOTORISTAS];

        static int[] categoriasCnh = new int[MAX_MOTORISTAS];
        static int[] situacoesMotoristas = new int[MAX_MOTORISTAS];

        static int qtdMotoristas = 0; //qtd = Quantidade
        // definindo o tamanho maximo da frota da empresa
        static final int MAX_VEICULOS = 20;
        // cabecario do veiculos.txt
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
        // cabecario do entregas.txt
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
        static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //
        carregarDados();
        boolean verificador = false;
        int opcao = 0;
        String resultado;

        do {
            System.out.println("\n##### Sistema de Estoque #####");
            System.out.println("( 1 ) Cadastrar Veiculo");
            System.out.println("( 2 ) Listar Veiculos");
            System.out.println("( 3 ) Alterar Situacao Veiculos");
            System.out.println("( 4 ) Cadastrar motorista");
            System.out.println("( 5 ) Listar Motoristas");
            System.out.println("( 6 ) Cadastrar Entrega");
            System.out.println("( 7 ) Despachar entrega");
            System.out.println("( 8 ) Concluir entrega");
            System.out.println("( 9 ) Cancelar entrega");
            System.out.println("( 10 ) Listar entrega");
            System.out.println("( 11 ) Relatório de veiculo e dia");
            System.out.println("( 12 ) Relatório de combustível");
            System.out.println("( 0 ) Sair");
            System.out.println("\n###############################");

            System.out.print("Escolha uma opção: ");
            
            opcao = lerInteiro();
            
            switch (opcao) {
                case 0:// sair do sistema
                    System.out.println("Saindo do sistema...");
            
                    break;
                case 1:// cadastrar veiculo
                    String placa, modelo;
                    double capacidadesKg = 0, consumosKml = 0;
                    System.out.println("\n##### qual a placa do veiculo #####");
                    System.out.print("coloque no seguinte formato (FFF0F00): ");
                    
                    do{
                        placa = lerTexto().toUpperCase();
                        if (placa.length() != 7 || buscarIndiceVeiculo(placa) != -1) {
                          System.out.println(escolhaErrada()); 
                            
                        } else {}
                        
                    }while (placa.length() != 7 || buscarIndiceVeiculo(placa) != -1);
                    
                    System.out.println("\n##### Qual o modelo quer cadastrar #####");
                    modelo = lerTexto();
                    
                    int tipo;

                        do {
                    System.out.println("1 - Utilitario");
                    System.out.println("2 - Caminhao leve");

                    tipo = lerInteiro();

                    if (tipo < 1 || tipo > 2) {
                         System.out.println("Tipo invalido.");
                    }

                } while (tipo < 1 || tipo > 2);
                    System.out.println("\n##### capacidade de carga #####");
                    //limitar a carga para cada tipo de veiculo
                    if(tipo == 1){ 
                        while (verificador == false) {
                            System.out.println("capacidade permitida e 100 a 1500 kg");
                            capacidadesKg = lerDecimal(); 
                            if (capacidadesKg < 100 || capacidadesKg > 1500){
                                System.out.println("erro de capacidade, coloca a carga permitida");
                            }
                            else{
                                verificador = true;
                            }
                        }
                        
                    }
                    else{  
                        while (verificador == false) {
                            System.out.println("capacidade permitida e 100 a 6000 kg");
                            capacidadesKg = lerDecimal();
                            if (capacidadesKg < 100 || capacidadesKg > 6000){
                                System.out.println("erro de capacidade, coloca a carga permitida");

                            }
                            else{
                                verificador = true;
                            }
                        }
                    }   

                    verificador = false;

                    System.out.println("\n##### consumo por km/h #####");
                    System.out.println("tem que ser maior 0 km/h e menor que 31 km/h");
                    do {
                       consumosKml = lerDecimal();
                       if (consumosKml > 0 && consumosKml <= 30 ) {
                        // sair do loop
                        verificador = true;
                       } 
                       else{
                        // quando coloca o consumo alto ou baixo demais.
                        System.out.println("esse consumo é invalido, tenta outro");
                       }
                    } while (verificador == false);
                    
                    //salva o metodo
                    resultado = cadastrarVeiculo(placa,modelo,tipo,capacidadesKg,consumosKml);

                    if (resultado.equals("processo concluido")) {
                        //salva e exibe o resultado
                        System.out.println(salvarVeiculos());

                    } else {
                        System.out.println(resultado);
                    }
                    break;

                case 2:// listar veiculo
                    System.out.println("\n##### Frota Cadastrada #####");
                         
                    listarVeiculos();
                    
                    break;

                case 3:// alterar situacao veiculo
                    int escolhaVeiculo = 0;
                    // mostra todos os veiculos da empresa;
                    listarVeiculos();

                    System.out.print("coloca a placa do veiculo voce quer alterar: ");
                    do{
                        placa = lerTexto();
                    
                        if(buscarIndiceVeiculo(placa) == -1){
                            System.out.print("colocar um codigo que existe: ");
                            
                        }
                        else{  
                            
                        }
                    }while (buscarIndiceVeiculo(placa) == -1);
                    
                    escolhaVeiculo = buscarIndiceVeiculo(placa);
                    
                    
                    if(situacoesVeiculo[escolhaVeiculo] == 1){
                        System.out.println("\nTem certeza de mudar: disponivel para manutencao");
                        System.out.println("digite (1) para sim ou (2) para nao");

                        opcao = lerInteiro();

                        if (opcao == 1) {
                            situacoesVeiculo[escolhaVeiculo] = 3;
                            salvarVeiculos();   
                        }

                    }else if(situacoesVeiculo[escolhaVeiculo] == 3){
                        System.out.println("\nTem certeza de mudar: manutencao para disponivel");
                        System.out.println("digite (1) para sim ou (2) para nao");

                        opcao = lerInteiro();

                        if (opcao == 1) {
                            situacoesVeiculo[escolhaVeiculo] = 1;
                            salvarVeiculos();   
                        }

                    }else{
                        System.out.println("Não pode alterar veiculo em rota");
                    }

                    break;
                case 4:// cadastro motorista
                    String nome;
                    int codigo = 0, cnh = 0;

                    System.out.println("##### Qual e o codigo do motorista ######");
                    do {
                        codigo = lerInteiro();
                        if (codigo < 1 || codigo > 999 || buscarIndiceMotorista(codigo) != -1) {
                            System.out.println("coloca um codigo valido ex:(1) e nao repitido");
                            }
                    } while (codigo < 1 || codigo > 999 || buscarIndiceMotorista(codigo) != -1);



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
                    
                    //cadastra no array
                    resultado = cadastrarMotorista(codigo, nome, cnh);

                    if (resultado.equals("processo concluido")) {
                        //salva e exibe o resultado
                        System.out.println(salvarMotoristas());

                    } else {
                        System.out.println(resultado);
                    }
                    
                    break;
                case 5:// lista dos Motoristas
                    System.out.println("\n##### Lista dos Motoristas #####");
                         
                    listarMotoristas();
                    
                    break;
                case 6:// cadastrar entrega
                    String destinoEntrega;
                    double pesoEntrega = 0, distanciaEntrega = 0;

                    System.out.println("\n##### qual e o destino #####");
                    destinoEntrega = lerTexto();
                    System.out.println("\n##### qual e o peso vai levar #####");
                     while (verificador == false) {
                            System.out.println("capacidade permitida e 100 a 6000 kg");
                            pesoEntrega = lerDecimal();
                            if (pesoEntrega < 100 || pesoEntrega > 6000){
                                System.out.println("erro de capacidade, coloca o peso permitio");
                            }
                            else{
                                verificador = true;
                            }
                        }
                    System.out.println("\n##### qual a distancia #####");
                    distanciaEntrega = lerDecimal();
                    
                    resultado = cadastrarEntrega(destinoEntrega, pesoEntrega, distanciaEntrega);

                    if (resultado.equals("processo concluido")) {
                        //salva e exibe o resultado
                        System.out.println(salvarMotoristas());

                    } else {
                        System.out.println(resultado);
                    }
                    
                    break;
                case 7:// desparchar entrega
                    //mostra todas as entregas
                    int escolhaEstoque, escolhaMotorista;
                    listarEntregas();
                    
                    System.out.print("coloca o codigo da entrega que quer desparchar: ");
                    do{
                        codigo = lerInteiro();
                    
                        if(buscarIndiceEntrega(codigo) == -1){
                            System.out.print("colocar um codigo que existe: ");
                            
                        }
                        else{  
                            
                        }
                    }while (buscarIndiceEntrega(codigo) == -1);

                    escolhaEstoque = buscarIndiceEntrega(codigo);

                    System.out.println("\n##### qual veiculo vai utilizar #####");
                    listarVeiculos();
                    System.out.print("coloca a placa do veiculo voce usar: ");
                   
                    do{
                        placa = lerTexto();
                    
                        if(buscarIndiceVeiculo(placa) == -1){
                            System.out.print("colocar uma placa que existe: ");
                            
                        }
                        else {  
                            
                        }
                    }while (buscarIndiceVeiculo(placa) == -1);
                    
                    escolhaVeiculo = buscarIndiceVeiculo(placa);
                    
                    System.out.println("\n##### qual motorista vai utilizar #####");

                    listarMotoristas();

                    do{
                        codigo = lerInteiro();
                    
                        if(buscarIndiceMotorista(codigo) == -1){
                            System.out.print("colocar um codigo que existe: ");
                            
                        }
                        else {  
                            
                        }
                        
                    }while (buscarIndiceMotorista(codigo) == -1);

                    escolhaMotorista = buscarIndiceEntrega(codigo);
    
                    System.out.println("\n##### qual dia #####");
                    int dia = 0;
                    dia = lerInteiro();
                    break;
                case 8:// concluir entrega
                    break;
                case 9:// cancelar entrega
                    break;
                case 10:// lista de entregas
                    System.out.println("\n##### Lista de Entregas #####");
                         
                    listarEntregas();
                    
                    break;
                case 11:// relatorio de veiculo e dia
                    break;
                case 12:// relatorio de combustivel
                    break;
                default:// colocou outra coisa, do que as opcoes
                    System.out.println(escolhaErrada());

                    break;
            }
        }while (opcao != 0);
    }
    // ler todos arquivos e carrega dentro dos arrays
    public static void carregarDados(){
        String linha;
        
        //lendo o veiculo.txt
        try(BufferedReader br = new BufferedReader(new FileReader("veiculos.txt"))) {
            linha = br.readLine();
           
            while ((linha = br.readLine()) != null) {
                String[] bloco = linha.split(";",-1);
                if (bloco.length < 6) { // ajuste conforme o arquivo (6, 4 ou 8)
                System.out.println("Linha invalida ignorada em veiculos.txt: " + linha);
                continue;
    }
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
                String[] bloco = linha.split(";", -1);
                if (bloco.length < 4) {
                    System.out.println("Linha invalida ignorada em motoristas.txt: " + linha);
                    continue;
                }
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
                String[] bloco = linha.split(";", -1);
                if (bloco.length < 8) {
                System.out.println("Linha invalida ignorada em entregas.txt: " + linha);
                continue;
                }
                codigosEntregas[qtdEntregas] = Integer.parseInt(bloco[0]);

                destinos[qtdEntregas] = bloco[1];

                pesosKg[qtdEntregas] = Double.parseDouble(bloco[2]);
                distanciasKm[qtdEntregas] = Double.parseDouble(bloco[3]);
            
                entVeiculo[qtdEntregas] = buscarIndiceVeiculo(bloco[4]);

                entMotorista[qtdEntregas] = buscarIndiceMotorista(Integer.parseInt(bloco[5]));
                entDia[qtdEntregas] = Integer.parseInt(bloco[6]);
                situacoesEntrega[qtdEntregas] = Integer.parseInt(bloco[7]);

                qtdEntregas++;
            }
        } catch (IOException e) {
            System.out.println("erro de leitura do arquivo: entregas.txt");
        }
        
    }
    // esse metodos de leitura quebra um galho, no codigo
    // ler int do sistema
    public static int lerInteiro(){
        
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
            }
        }
    // ler double do sistema
    public static double lerDecimal() {

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
        }
    }
    // ler String do sistema
    public static String lerTexto(){
        
        while (true) {
            
            try {
                
                String entrada = sc.nextLine().trim();
                //verifica se a entrada não e vazia
                if (!entrada.isEmpty()) {
                   return entrada;  
                } else {
                    System.out.println("entrada vazia, tenta novamente");

                }
                
            } catch (Exception e) {
                System.out.println(escolhaErrada());
            }
        }
    }    
    // Cadastrar veículo (RF1)
    public static String cadastrarVeiculo(String placa, String modelo, int tipo, Double cabacidadeMax, Double consumoKML){
        if (qtdVeiculos >= MAX_VEICULOS) {
            return "Não ha espaco na frota";
            
        } else {
            //colocando todas as informacoes no arrey, em sua posicao especifica 
            placas[qtdVeiculos] = placa;
            modelos[qtdVeiculos] = modelo;

            tiposVeiculo[qtdVeiculos] = tipo;

            capacidadesKg[qtdVeiculos] = cabacidadeMax;
            consumosKmL[qtdVeiculos] = consumoKML;

            situacoesVeiculo[qtdVeiculos] = 1;

            qtdVeiculos++;
            return "processo concluido";
        }
       
    }
    //  listar veiculos (RF2)
    public static void listarVeiculos() {
        if (qtdVeiculos == 0) {
        System.out.println("Nenhum veiculo cadastrado.");
        return;
    }
    System.out.println(informacoesVeiculo);

    for (int i = 0; i < qtdVeiculos; i++) {
        
        System.out.println(placas[i] +";"+ modelos[i] +";"+ tiposVeiculo[i] +";"+ capacidadesKg[i] +";"+ consumosKmL[i] +";"+ situacoesVeiculo[i]);
        
        }
    }
    // Quando escolhe uma opcao invalida
    public static String escolhaErrada(){
        return "\nOpção invalida, Tenta novamente"; //mensagem basica de erro
    }
    //  alterar situacao (RF3)
    public static int alterarSituacaoVeiculo(String placa){
        for(int i = 0; i < qtdVeiculos; i++){
            if(placas[i].equalsIgnoreCase(placa)){
                return i; 
            }
            
        }
        return -1;
    }
    //  cadastra motorista (RF4)
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
            return "processo concluido";
        }

    }
    // listar motoristas (RF5)
    public static void listarMotoristas(){
        if (qtdMotoristas == 0) {
            System.out.println("Nenhum motorista cadastrado.");
            return;
        }

        System.out.println(informacoesMotorista);

        for (int i = 0; i < qtdMotoristas; i++) {
            System.out.println(codigosMotorista[i] +";"+ nomesMotoristas[i] +";"+ categoriasCnh[i] +";"+ situacoesMotoristas[i]);
            
        }

    }
    // cadastrar entrega (RF6)
    static String cadastrarEntrega(String destino, double peso, double distancia) {
        if (qtdEntregas >= MAX_ENTREGAS) {
            return "Não pode fazer mais entregas";
        }

        codigosEntregas[qtdEntregas] = qtdEntregas + 1;
        destinos[qtdEntregas] = destino;
        pesosKg[qtdEntregas] = peso;
        distanciasKm[qtdEntregas] = distancia;
        entVeiculo[qtdEntregas] = 0;
        entMotorista[qtdEntregas] = 0;
        entDia[qtdEntregas] = 0;
        situacoesEntrega[qtdEntregas] = 1; // 1 = pendente

        qtdEntregas++;
        return "processo concluido";
    }
    // listar entrega (RF10)
    public static void listarEntregas(){
        if (qtdEntregas == 0) {
            System.out.println("Nenhum motorista cadastrado.");
            return;
        }

        System.out.println(informacoesEntregas);

        for (int i = 0; i < qtdEntregas; i++) {
            System.out.print(codigosEntregas[i] +";"+ destinos[i] +";"+ pesosKg[i] +";"+ distanciasKm[i]);
            
            System.out.print(entVeiculo[i] +";"+ entMotorista[i] +";"+ entDia[i] +";"+ situacoesEntrega[i]);

        }

    }
    // salva de maneira permanente
    public static String salvarVeiculos(){
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
                return "erro de escrever no arquivo: veiculos.txt";

            }       
            return "foi salvo com sucesso";
            
    }
    public static String salvarMotoristas(){
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
                return "erro de escrita do arquivo: motoristas.txt";
            }            
            return "salvo com exito";
    }
    public static String despacharEntrega(int escolhaEstoque, int escolhaVeiculo, int escolhaMotorista, int dia){
        // fiz isso para não ter salvar no lugar errado;
        int i = escolhaEstoque - 1;

        entVeiculo[i] = escolhaVeiculo;
        entMotorista[i] = escolhaMotorista;
        entDia[i] = dia;
        situacoesEntrega[i] = 2;

        return "processo concluido";
    }
    public static int buscarIndiceVeiculo(String placa) {
        
        for (int i = 0; i < qtdVeiculos; i++) {
            
            if (placas[i] != null && placas[i].equalsIgnoreCase(placa)) {
                return i;
            }
        }
        return -1;
    }
    public static int buscarIndiceMotorista(int codigo) {
    for (int i = 0; i < qtdMotoristas; i++) {
        if (codigosMotorista[i] == codigo) {
            return i;
        }
    }
    return -1;
    }
    public static int buscarIndiceEntrega(int codigo) {
    for (int i = 0; i < qtdEntregas; i++) {
        if (codigosEntregas[i] == codigo) {
            return i;
        }
    }
    return -1;
    }
}