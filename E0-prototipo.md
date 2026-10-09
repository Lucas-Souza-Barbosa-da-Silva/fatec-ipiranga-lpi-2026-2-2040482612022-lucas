# Prototipagem do Sistema de Gestão de Frota e Entregas (Tema 5\)

**Aluno:** Lucas Souza Barbosa da Silva **RA:** 2040482612022 **Curso:** Tecnologia em Análise e Desenvolvimento de Sistemas \- FATEC Ipiranga **Disciplina:** Linguagem de Programação I (2026.2) **Docente:** Prof. Veríssimo **Repositório:** fatec-ipiranga-lpi-2026-2-2040482612022-lucas

---

## 1\. Mapa do Menu Principal

O sistema de gestão de frota apresenta a seguinte estrutura textual de navegação no console:

\#\#\#\#\# SISTEMA DE GESTÃO DE FROTA E ENTREGAS \#\#\#\#\#

( 1 ) Cadastrar Novo Veículo

( 2 ) Registar Quilometragem / Entregas Mensais (Matriz E2)

( 3 ) Relatório de Frota, Validações e Alertas de Manutenção/Excesso de Km (E3)

( 4 ) Relatório da Matriz Mensal de Entregas/Quilometragem (E2)

( 5 ) Persistir / Carregar Dados em Ficheiro (E5)

( 0 ) Sair

---

## 2\. Prototipagem do Console (Mockup)

### Relatório Tabular da Frota e Validação/Alertas (Entrega E3)

Formatado em console com alinhamento tabular (`System.out.printf`):

\===================================================================================================

                                  RELATÓRIO DE FROTA E MANUTENÇÃO

\===================================================================================================

CÓDIGO | PLACA     | MODELO     | ANO  | CAPACIDADE | KM TOTAL | MANUTENÇÃO | ALERTA EXCESSO

\---------------------------------------------------------------------------------------------------

  1    | ABC1D23   | Van        | 2021 |   1500 kg  | 12500 km |  Normal    | \[ALERTA: EXCESSO\]

  2    | XYZ9K88   | Vuc        | 2019 |   3500 kg  |  8200 km |  Oficina   | OK

  3    | DEF4M56   | Caminhão   | 2023 |  12000 kg  | 15400 km |  Normal    | \[ALERTA: EXCESSO\]

\---------------------------------------------------------------------------------------------------

### Relatório da Matriz Mensal de Quilometragem/Entregas (Entrega E2/E5)

Visualização tabular da matriz `double[12][12]` (linhas \= veículos, colunas \= meses):

\===================================================================================================

                                MATRIZ MENSAL DE ENTREGAS / QUILOMETRAGEM

\===================================================================================================

VEÍCULO (PLACA) | JAN   | FEV   | MAR   | ABR   | MAI   | JUN   | ... | DEZ   | TOTAL ANUAL

\---------------------------------------------------------------------------------------------------

ABC1D23         | 1200  | 1100  | 1300  | 1050  | 1400  | 1250  | ... | 1150  | 12500 km

XYZ9K88         |  800  |  750  |  900  |  850  |  700  |  950  | ... |  800  |  8200 km

\---------------------------------------------------------------------------------------------------

---

## 3\. Regras de Validação de Entrada

1. **Código do Veículo:** Deve ser um número inteiro estritamente positivo (`codigo > 0`).  
2. **Placa do Veículo:**  
   - **Formato:** Padrão Mercosul (`FFF0F00`) ou tradicional (`FFF0000`).  
   - **Regex de Validação:** `^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$`  
3. **Ano de Fabricação:** Valor numérico no intervalo `1900 <= ano <= 2026`.  
4. **Capacidade de Carga:** Número decimal estritamente maior que zero (`capacidadeCarga > 0`).  
5. **Unidade de Medida:** Apenas as opções `"kg"` ou `"t"` (validado ignorando maiúsculas/minúsculas).  
6. **Status de Manutenção:** Opções válidas `"Normal"` ou `"Oficina"`.

---

## 4\. Assinatura dos Métodos (Planeamento da Entrega E4)

/\*\*

 \* Valida se a placa informada obedece ao padrão regulamentar (Mercosul ou tradicional).

 \* @param placa String contendo a placa do veículo.

 \* @return boolean true se a placa for válida, false caso contrário.

 \*/

public static boolean validarPlaca(String placa);

/\*\*

 \* Regista o valor de quilometragem/entregas de um veículo num mês específico da matriz.

 \* @param indiceVeiculo Posição do veículo no array.

 \* @param mes Mês de referência (0 a 11).

 \* @param quantidade Valor a ser registado na matriz.

 \*/

public static void registrarMovimentacaoMensal(int indiceVeiculo, int mes, double quantidade);

/\*\*

 \* Percorre a frota e exibe o relatório formatado com avisos de excesso de quilometragem e necessidade de manutenção.

 \* @param frota Array contendo os objetos da classe Veiculo.

 \* @param totalVeiculos Quantidade de veículos registados.

 \* @param limiteKm Limite de quilometragem estabelecido para alerta.

 \*/

public static void gerarRelatorioExcessoKm(Veiculo\[\] frota, int totalVeiculos, double limiteKm);