package com.ambientese.sistemaformulario.front;
import org.springframework.stereotype.Component;

import com.ambientese.sistemaformulario.servico.ServicoScanner;



@Component
public class MenuInicial {
   
    private final ServicoScanner scanner;
    private MenuUsuario menuUsuario;
    private MenuEmpresa menuEmpresa;
    private MenuFormulario menuFormulario;

    public MenuInicial( ServicoScanner scanner ,MenuUsuario menuUsuario, MenuEmpresa menuEmpresa, MenuFormulario menuFormulario){
        this.scanner = scanner;
        this.menuUsuario = menuUsuario;
        this.menuEmpresa = menuEmpresa;
        this.menuFormulario = menuFormulario;
    }

   

    public void exibirTelaInicial() {
        // Declaração da variável opcao
        int opcao = 0;

        // Condição de parada
        do {
            // Exibe o menu principal
            telaInicial();

            // Lê a opção selecionada pelo usuário
            opcao = scanner.scanner().nextInt();
            // Executa a ação correspondente à opção selecionada
            switch (opcao) {
                case 1:
                    menuUsuario.exibirTelaUsuario();
                    break;
                case 2:
                    menuEmpresa.exibirTelaEmpresa();
                    break;
                case 3:
                    menuFormulario.exibirTelaFormulario();
                    break;
                case 0:
                    System.out.println("Saindo do sistema. Até mais!");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.scanner().close();
    }

    public void telaInicial() {
        System.out.println("\n************ MENU PRINCIPAL ************\n");
        System.out.println("1-> Usuários");
        System.out.println("2-> Empresas");
        System.out.println("3-> Formulários");
        System.out.println("0-> Sair");
        System.out.println("\n****************************************\n");
        System.out.println("ESCOLHA UMA OPÇÃO: ");
    }

  
}
