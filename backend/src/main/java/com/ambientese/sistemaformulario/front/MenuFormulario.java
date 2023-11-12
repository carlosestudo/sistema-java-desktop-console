package com.ambientese.sistemaformulario.front;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ambientese.sistemaformulario.controlador.EmpresaControlador;
import com.ambientese.sistemaformulario.controlador.FormularioControlador;
import com.ambientese.sistemaformulario.entidade.Desempenho;
import com.ambientese.sistemaformulario.entidade.Formulario;
import com.ambientese.sistemaformulario.entidade.Pergunta;
import com.ambientese.sistemaformulario.entidade.enums.TipoFormulario;
import com.ambientese.sistemaformulario.servico.DesempenhoServico;
import com.ambientese.sistemaformulario.servico.ServicoScanner;

@Component
public class MenuFormulario {
    
    private final ServicoScanner scanner;
    private FormularioControlador formularioControlador;
    private EmpresaControlador empresaControlador;
    private DesempenhoServico desempenhoServico;
    
    public MenuFormulario(ServicoScanner scanner, FormularioControlador formularioControlador, EmpresaControlador empresaControlador, DesempenhoServico desempenhoServico) {
        this.scanner = scanner;
        this.formularioControlador = formularioControlador;
        this.empresaControlador = empresaControlador;
        this.desempenhoServico = desempenhoServico;
    }

    public void exibirTelaFormulario() {
        // Declaração da variável opcao
        int opcao = 0;

        // Condição de parada
        do {   
            telaFormulario();

            // Lê a opção selecionada pelo usuário
            opcao = scanner.scanner().nextInt();

            // Executa a ação correspondente à opção selecionada
            switch (opcao) {
                case 1:
                    criarFormulario();
                    break;
                case 2:
                    System.out.println("\n***** LISTA FORMULARIO *****\n");
                    formularioControlador.listarTudo();
                break;
                case 3:
                    alterarFormulario();
                    break;
                case 4:
                    responderFormulario();
                    break;    
                case 0:
                    break;
                default:
                    System.out.println("\n//////// OPÇÃO INVALIDA, VERIFIQUE E TENTE NOVAMENTE ////////\n");
                    break;
            }
        } while (opcao != 0);
    }
     
    public void telaFormulario(){
        System.out.println("\n************ MENU FORMULARIO ************\n");

        System.out.println("1-> Criar formulario");
        System.out.println("2-> Listar Formulario");
        System.out.println("3-> Alterar Formulario");
        System.out.println("4-> Responder Formulario");
        System.out.println("0-> Voltar");

        System.out.println("\n**************************************\n");
        System.out.println("SELECIONE UMA OPÇÃO: ");
    }
    
    public void criarFormulario() {

        System.out.println("\n********* CRIAR FORMULARIO *********\n");
        Formulario novoFormulario = novoFormulario();
        
        
        while (true) {
            Pergunta pergunta = novaPergunta();
            novoFormulario.adicionarPergunta(pergunta);
            
            System.out.println("\nDESEJA ADICIONAR UMA NOVA PERGUNTA? (S/N)");
            String continuar = scanner.scanner().nextLine();
            
            if (!continuar.toLowerCase().equals("s")) {
                break; 
            }
        }
    
        formularioControlador.salvar(novoFormulario);
    }

    public void alterarFormulario(){
        System.out.println("\n************ ALTERAR FORMULARIO ************\n");
        System.out.println("\n***** LISTA FORMULARIO *****\n");
        formularioControlador.listarTudo();
    
        System.out.println("SELECIONE O ID: ");
        Long idFormulario = (long) scanner.scanner().nextInt();
    
        Formulario formularioSelecionado = formularioControlador.obterPorId(idFormulario);
    
        if (formularioSelecionado != null) {
            int opcao = 0;
            do{
                System.out.println("\n***** ALTERAR FORMULARIO *****\n");
            
                System.out.println("FORMULARIO SELECIONADO: " + formularioSelecionado.getTitulo().toUpperCase());
                
                System.out.println("\n1-> Adicionar pergunta");
                System.out.println("2-> Remover pergunta");
                System.out.println("3-> Listar perguntas");
                System.out.println("0-> Voltar");
                
                System.out.println("\n******************************\n");
                System.out.println("\nSELLECIONE UMA OPÇÃO:");
                opcao = scanner.scanner().nextInt();
        
                switch (opcao) {
                    case 1:
                        adicionarPerguntaAoFormulario(formularioSelecionado);
                        break;
                    case 2:
                        removePergunta(formularioSelecionado);
                        break;
                    case 3:
                        formularioControlador. listaExibiPerguntasDoFormulario(formularioSelecionado.getId());
                        break;
                    case 0:
                        System.out.println("\n//////// SAINDO DO MENU DE ALTERAÇÃO ////////\n");
                        break;
                    default:
                        System.out.println("\n//////// OPÇÃO INVALIDA, VERIFIQUE E TENTE NOVAMENTE ////////\n");
                        break;
                }
            }while(opcao != 0);

        } else {
            System.out.println("/////// FORMULARIO NÃO ENCONTRADO, VERIFIQUE E TENTE NOVAMENTE ///////");
        }
    }

    private void removePergunta(Formulario formulario) {

        formularioControlador.listaExibiPerguntasDoFormulario(formulario.getId());

        System.out.println("\nSELECIONE O ID: ");
        Long idPergunta = scanner.scanner().nextLong();
        formularioControlador.removerPerguntaDoFormulario(formulario.getId(), idPergunta );
        System.out.println("\n********** PERGUNTA REMOVIDA COM SUCESSO **********\n");
    }

    public Formulario novoFormulario(){
       
        System.out.println("\nNome Formulario: ");
        String titulo = scanner.scanner().nextLine();

        System.out.println("\nDigite um dos tipos de formulário: Governancia, Social, Economico");
        System.out.println("Tipo do formulario: ");
        String tipoFormulario = scanner.scanner().nextLine().toLowerCase();

        System.out.println("\nDigite o numero minimo de acertos para esse formulario:");
        int acertos = scanner.scanner().nextInt();

        Formulario formulario = new Formulario();
        formulario.setTitulo(titulo);
        formulario.setQtdMinimaAcerto(acertos);
        
        switch (tipoFormulario) {
            case "social":
                formulario.setTipoFormulario(TipoFormulario.SOCIAL);
                break;
            case "governancia":
                formulario.setTipoFormulario(TipoFormulario.GOVERNANCIA);
                break;
            case "economico":
                formulario.setTipoFormulario(TipoFormulario.ECONOMICO);
                break;
            default:
                System.out.println("\n///////// TIPO FORMULARIO INVALIDO, VERIFIQUE E TENTE NOVAMENTE /////////\n");
                System.out.println("\n********* CRIAR FORMULARIO *********\n");
                novoFormulario();
                break;
        }

        if(!formularioControlador.validarFormulario(formulario)){
            System.out.println("\n********* CRIAR FORMULARIO *********\n");
            novoFormulario();
        }

        return formulario;
    }

    public Pergunta novaPergunta(){
        System.out.println("\n********* ADICIONAR PERGUNTA *********\n");
        System.out.println("\nDIGITE O ENUMCIADO, AS PERGUNTAS DEVEM SER RESPONDIDAS COM SIM OU NÃO:");
        String enunciado = scanner.scanner().nextLine();
        
        System.out.println("\nDIGITE A RESPOSTA CORRETA: ");
        String resposta = scanner.scanner().nextLine().toLowerCase();

        Pergunta novaPergunta = new Pergunta();
        novaPergunta.setEnumciado(enunciado);
        
        switch(resposta){ 
            case "sim":
                novaPergunta.setResposta(true);
                break;
            case "não":
                novaPergunta.setResposta(false);
                break;
            case "nao":
                novaPergunta.setResposta(false);
                break;
            default:
                System.out.println("////////// RESPOSTA INVALIDA, VERIFIQUE E TENTE NOVAMENTE //////////");
                novaPergunta();
            break;
        }

        return novaPergunta;    
    }

    private void adicionarPerguntaAoFormulario(Formulario formulario) {
        Pergunta pergunta = novaPergunta();
        formulario.adicionarPergunta(pergunta);
        formularioControlador.alterar(formulario);
        System.out.println("****** PERGUNTA ADICIONADA COM SUCESSO ******");
    }

    private void responderFormulario() {

            System.out.println("\n********* RESPONDER FORMULÁRIO *********\n");
        
            formularioControlador.listarTudo();
            System.out.println("\nSELECIONE O ID FORMULARIO: ");
            Long idFormulario = scanner.scanner().nextLong();
        
            if(!formularioControlador.verificaID(idFormulario)){
                System.out.println("\n///////// ID FORMULARIO INVALIDO, VERIFIQUE E TENTE NOVAMENTE /////////\n");
                responderFormulario();
            }

            empresaControlador.listarTudo();
            System.out.println("\nSELECIONE O ID EMPRESA:");
            Long idEmpresa = scanner.scanner().nextLong();

            if(!empresaControlador.verificarID(idEmpresa)){
                System.out.println("\n///////// ID EMPRESA INVALIDO, VERIFIQUE E TENTE NOVAMENTE /////////\n");
                responderFormulario();
            }
        
            System.out.println("\n********* RESPONDA O FORMULÁRIO *********\n");
            int respostasCorretas = 0;
            int respostasIncorretas = 0;
            int numeroQuestao = 1;
        
            List<Pergunta> perguntas = formularioControlador.listarPerguntasDoFormulario(idFormulario);
            for (Pergunta pergunta : perguntas) {
                System.out.println("\nQUESTÃO " + numeroQuestao);
                System.out.println("\n==========================================\n");
                System.out.println(pergunta.getEnumciado());
                System.out.println("\n==========================================\n");
        
                System.out.println("RESPONDA COM S OU N: ");
                String resposta = scanner.scanner().nextLine().toLowerCase();
        
                if (resposta.equalsIgnoreCase(pergunta.isResposta() ? "s" : "n")) {
                    respostasCorretas++;
                } else {
                    respostasIncorretas++;
                }

                numeroQuestao ++;
            }
        
            Desempenho desempenho = new Desempenho();
            desempenho.setFormulario(formularioControlador.obterPorId(idFormulario));
            desempenho.setEmpresa(empresaControlador.obterPorId(idEmpresa));
            desempenho.setQtdPerguntasCertas(respostasCorretas);
            desempenho.setQtdPerguntasErradas(respostasIncorretas);
        
            if (respostasCorretas >= desempenho.getFormulario().getQtdMinimaAcerto()) {
                desempenho.setAprovado(true);
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
            } else {
                desempenho.setAprovado(false);
                System.out.println("\n===========================\n");
                System.out.println("     EMPRESA REPROVADA     ");
                System.out.println("\n===========================\n");
            }
        
            // Salvar o desempenho no banco de dados
            desempenhoServico.salvar(desempenho);
        }

}