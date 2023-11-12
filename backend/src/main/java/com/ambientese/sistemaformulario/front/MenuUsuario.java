package com.ambientese.sistemaformulario.front;

import org.springframework.stereotype.Component;

import com.ambientese.sistemaformulario.controlador.UsuarioControlador;
import com.ambientese.sistemaformulario.entidade.Usuario;
import com.ambientese.sistemaformulario.entidade.enums.TipoCargo;
import com.ambientese.sistemaformulario.servico.ServicoScanner;



@Component
public class MenuUsuario {

    private final ServicoScanner scanner;
    private UsuarioControlador usuarioControlador;


    public MenuUsuario(ServicoScanner scanner, UsuarioControlador usuarioControlador) {
        this.scanner = scanner;
        this.usuarioControlador = usuarioControlador;
    }

    public void exibirTelaUsuario() {
        // Declaração da variável opcao
        int opcao = 0;

        // Condição de parada
        do {
            // Exibe o menu de usuário
            telaUsuario();

            // Lê a opção selecionada pelo usuário
            opcao = scanner.scanner().nextInt();

            // Executa a ação correspondente à opção selecionada
            switch (opcao) {
                case 1:
                    System.out.println("\n********* CADASTRO DE USUARIO *********\n");
                    criarUsuario(null);
                    break;
                case 2:
                    listarUsuario();
                    break;
                case 3:
                    alterarUsuario();
                    break;
                case 4:
                    deletarUsuario();
                    break;
                case 0:
                    
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    public void telaUsuario() {
        System.out.println("\n************ MENU USUÁRIOS ************\n");

        System.out.println("1-> Cadastro Usuario");
        System.out.println("2-> Listar Usuario");
        System.out.println("3-> Alterar Usuario");
        System.out.println("4-> Deletar Usuario");
        System.out.println("0-> Voltar");
        
        System.out.println("\n**************************************\n");
        System.out.println("SELECIONE UMA OPÇÃO: ");
    }

    public void criarUsuario(Long id){

        
        System.out.print("Nome: ");
        String nome = scanner.scanner().nextLine();

        System.out.print("\nEmail: ");
        String email = scanner.scanner().nextLine();

        System.out.print("\nSenha: ");
        String senha = scanner.scanner().nextLine();

        System.out.println("\nDigite um dos cargos Gerente, Supervisor, Analista");
        System.out.println("\nCargo: ");
        String cargo = scanner.scanner().nextLine().toLowerCase();

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);

            switch (cargo) {
                case "gerente":
                    novoUsuario.setCargo(TipoCargo.GERENTE);
                    break;
                case "supervisor":
                    novoUsuario.setCargo(TipoCargo.SUPERVISOR);
                    break;
                case "analista":
                    novoUsuario.setCargo(TipoCargo.ANALISTA);
                    break;
                default:
                    System.out.println("\n////////// CARGO INVALIDO, VERIFIQUE E TENTE NOVAMENTE //////////\n");
                    System.out.println("\n********* CADASTRO DE USUARIO *********\n"); 
                    criarUsuario(null);
                    break;
            }
        /* ****** ATENÇÃO SE FUNCIONA NÃO É GAMBIARRA É AJUSTE TECNICO PERSONALISADO ******* */

        if(usuarioControlador.validarUsuario(novoUsuario)){
          
            if(id != null){
                novoUsuario.setId(id);
                usuarioControlador.salvar(novoUsuario);
                System.out.println("\n***** USUARIO ATUALIZADO COM SUCESSO *****\n");

            }else{
                usuarioControlador.salvar(novoUsuario);
                System.out.println("\n***** USUARIO SALVO COM SUCESSO *****\n");
            }

        }else{
            System.out.println("\n********* CADASTRO DE USUARIO *********\n");
            criarUsuario(null);  
        }

    }
 

    public void listarUsuario(){
        System.out.println("\n***** LISTA DE USUÁRIOS *****\n");
        usuarioControlador.listarTudo();
    }

     public void alterarUsuario() {
        System.out.println("\n************ ALTERAR  USUARIO ************\n");
        
        listarUsuario();
    
        System.out.println("DIGITE O ID:");
        Long id = (long) scanner.scanner().nextInt();
    
        if (usuarioControlador.verificarExistenciaUsuarioPorId(id)) {
            System.out.println("\nDIGITE OS DADOS\n");
            criarUsuario(id);
        } else {
            System.out.println("////////////// ID INVALIDO, TENTE NOVAMENTE //////////////");
            alterarUsuario();
        }
    }

    public void deletarUsuario(){
        System.out.println("\n************ DELETAR  USUARIO ************\n");

        listarUsuario();
        System.out.println("\nSELECIONE O ID:");
        Long id = (long) scanner.scanner().nextInt();
        
        if(usuarioControlador.verificarExistenciaUsuarioPorId(id)){
            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuarioControlador.deletarUsuario(usuario);
        }else{
            System.out.println("////////////// ID INVALIDO, TENTE NOVAMENTE //////////////");
            deletarUsuario();
        }
       
    }

    
}
