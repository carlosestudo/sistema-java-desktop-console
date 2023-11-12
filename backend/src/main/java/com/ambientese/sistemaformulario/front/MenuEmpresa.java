package com.ambientese.sistemaformulario.front;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ambientese.sistemaformulario.controlador.DesempenhoControlador;
import com.ambientese.sistemaformulario.controlador.EmpresaControlador;
import com.ambientese.sistemaformulario.entidade.Desempenho;
import com.ambientese.sistemaformulario.entidade.Empresa;
import com.ambientese.sistemaformulario.entidade.enums.TipoPorte;
import com.ambientese.sistemaformulario.servico.ServicoScanner;

@Component
public class MenuEmpresa {
    
    
    
    private final ServicoScanner scanner;
    private EmpresaControlador empresaControlador;
    private DesempenhoControlador desempenhoControlador;

    public MenuEmpresa(ServicoScanner scanner, EmpresaControlador empresaControlador, DesempenhoControlador desempenhoControlador) {
        this.scanner = scanner;
        this.empresaControlador = empresaControlador;
        this.desempenhoControlador = desempenhoControlador;
    }

    public void exibirTelaEmpresa() {
        // Declaração da variável opcao
        int opcao = 0;

        // Condição de parada
        do {
            // Exibe o menu de usuário
            telaEmpresa();

            // Lê a opção selecionada pelo usuário
            opcao = scanner.scanner().nextInt();

            // Executa a ação correspondente à opção selecionada
            switch (opcao) {
                case 1:
                    System.out.println("\n********* CADASTRO DE EMPRESA *********\n");
                    adicionarEmpresa();
                    break;
                case 2:
                    listarEmpresa();
                    break;
                case 3:
                    System.out.println("\n********* ATUALIZAR DADOS EMPRESA *********\n");
                    alterarEmpresa();
                    break;
                case 4:
                    verificarCertificado(true);
                    break;
                case 5: 
                    verificarCertificado(false);
                    break;
                case 6:
                    System.out.println("\n************** DELETAR EMPRESA **************\n");
                    deletarEmpresa();
                    break;
                case 0:
                    
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }


    public void telaEmpresa() {
        System.out.println("************ MENU EMPRESA ************\n");

        System.out.println("1-> Adicionar Empresa");
        System.out.println("2-> Listar Empresa");
        System.out.println("3-> Alterar Empresa");
        System.out.println("4-> Verificar Certificado Validos");
        System.out.println("5-> Verificar Certificado Reprovados");
        System.out.println("6-> Deletar Empresa");
        System.out.println("0-> Voltar\n");

        System.out.println("\n**************************************\n");
        System.out.println("SELECIONE UMA OPÇÃO: ");
    }
    public Empresa criarEmpresa(){
        
        System.out.print("Razão Social: ");
        String razaoSocial = scanner.scanner().nextLine();

        System.out.print("\nCNPJ: ");
        String cnpj = scanner.scanner().nextLine();

        System.out.print("\nEndereço: ");
        String endereco = scanner.scanner().nextLine();

        System.out.println("\nDigite um dos Portes: Grande, Médio, Pequeno");
        System.out.print("\nPorte: ");

        String porte = scanner.scanner().nextLine().toLowerCase();
        System.out.println("\n**************************************\n");

      
        Empresa novaEmpresa = new Empresa();
        novaEmpresa.setRazaoSocial(razaoSocial);
        novaEmpresa.setCnpj(cnpj);
        novaEmpresa.setEndereco(endereco);


        
            switch (porte) {
                case "grande":
                    novaEmpresa.setPorte(TipoPorte.POTE_GRANDE);
                    break;
                case "medio":
                    novaEmpresa.setPorte(TipoPorte.PORTE_MEDIO);
                    break;
                case "pequeno":
                    novaEmpresa.setPorte(TipoPorte.PORTE_PEQUENO);
                    break;
                default:
                    System.out.println("////////// PORTE INVALIDO, VERIFIQUE E TENTE NOVAMENTE //////////");
                break;
            }

        return novaEmpresa;
    }
   
    public void adicionarEmpresa() {
        empresaControlador.salvar(criarEmpresa());
    }

    public void listarEmpresa(){
        System.out.println("\n***** LISTA DE EMPRESAS *****\n");
        empresaControlador.listarTudo();
    }

    public void alterarEmpresa() {
        
        listarEmpresa();
    
        System.out.println("DIGITE O ID DA EMPRESA:");
        Long id = (long) scanner.scanner().nextInt();
    
        if (empresaControlador.verificarID(id)) { 
            System.out.println("\nDIGITE OS DADOS\n");
            Empresa atualizaEmpresa = criarEmpresa();
            atualizaEmpresa.setId(id);
            empresaControlador.salvar(atualizaEmpresa);
        } else {
            System.out.println("////////////// ID INVALIDO, TENTE NOVAMENTE //////////////");
            alterarEmpresa();
        }
    }

    public void deletarEmpresa(){
        
        listarEmpresa();
        System.out.println("\nSELECIONE O ID:");
        Long id = (long) scanner.scanner().nextInt();
        empresaControlador.deletar(id);
    }

    private void verificarCertificado(boolean certificado) {
        System.out.println("\n================== VERIFICAR CERTIFICADO ==================\n");
        listarEmpresa();

        System.out.println("\nSELECIONE O ID:");
        Long idEmpresa = scanner.scanner().nextLong();

        List<Desempenho> desempenhos = desempenhoControlador.obterDesempenhoDaEmpresa(idEmpresa);

        if(desempenhos != null && !desempenhos.isEmpty()){
            
            if(certificado){
                        System.out.println("\n================== CERTIFICADO APROVADOS ==================\n");
                    for (Desempenho desempenho : desempenhos) {   
                            if(desempenho.isAprovado()){
                                System.out.println("\n||======================= CERTIFICADO =======================||");
                                System.out.println("||                                                           ||");
                                System.out.println("||                    CERTIFICADO " + desempenho.getFormulario().getTipoFormulario() +"                     ||");
                                System.out.println("||                                                           ||");
                                System.out.println("||                  "+ desempenho.getEmpresa().getRazaoSocial().toUpperCase() + "                  ||");
                                System.out.println("||                                                           ||");
                                System.out.println("||      Por sua notável contribuição para a área " + desempenho.getFormulario().getTipoFormulario() + "      ||");
                                System.out.println("||                                                           ||");
                                System.out.println("||                 Data de Emissão: [DATA]                   ||");
                                System.out.println("||                                                           ||");
                                System.out.println("||===========================================================||");
                            }
                    }
            
            }else{
                System.out.println("\n================== CERTIFICADO REPROVADOS ==================\n");
                for (Desempenho desempenho : desempenhos) {   
                    if(!desempenho.isAprovado()){
                        System.out.println("\n||======================= REPROVADA =======================||");
                        System.out.println("||                                                           ||");
                        System.out.println("||          REPROVADA NO CERTIFICADO " + desempenho.getFormulario().getTipoFormulario() +"          ||");
                        System.out.println("||                                                           ||");
                        System.out.println("||                  "+ desempenho.getEmpresa().getRazaoSocial().toUpperCase() + "                  ||");
                        System.out.println("||                                                           ||");
                        System.out.println("||    QUESTÕES CORRETAS: " + desempenho.getQtdPerguntasCertas() +"      ||");
                        System.out.println("||    QUESTÕES ERRADAS:" + desempenho.getQtdPerguntasErradas() + "    ||");
                        System.out.println("||                                                           ||");
                        System.out.println("||                 Data de Emissão: [DATA]                   ||");
                        System.out.println("||                                                           ||");
                        System.out.println("||===========================================================||");
                    }
                }
            }

        }else{
            System.out.println("\n=========================================\n");
            System.out.println("         EMPRESA NÃO CERTIFICADA         ");
            System.out.println("\n=========================================\n");               
        }

    }


}
