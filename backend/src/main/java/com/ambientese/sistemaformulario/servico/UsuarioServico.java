package com.ambientese.sistemaformulario.servico;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ambientese.sistemaformulario.entidade.Usuario;
import com.ambientese.sistemaformulario.repositorio.UsuarioRepositorio;

import jakarta.transaction.Transactional;

@Service

public class UsuarioServico {

    private UsuarioRepositorio usuarioRepositorio;
    
    public UsuarioServico(UsuarioRepositorio usuarioRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
    }
    
  
    public void salvar(Usuario usuario){
        usuarioRepositorio.save(usuario);  
    }

    public void listarTudo() {

        Iterable<Usuario> usuarios = usuarioRepositorio.findAll();
      
        for (Usuario usuario : usuarios) {
            System.out.println("\n==============================\n");
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Cargo: " + usuario.getCargo());
            System.out.println("\n==============================\n");
        }
    }

    /* TA AQUI SÓ FAZENDO PESSO ASSIM QUE DER EU REMOVO */
    @Transactional
    public void alterarUsuario(Usuario usuario) {
        // Verifica se o usuário com o ID fornecido existe no banco de dados
        if (usuarioRepositorio.existsById(usuario.getId())) {
            // Atualiza o usuário no banco de dados
            if (validarUsuario(usuario)) {
                usuarioRepositorio.save(usuario);
                System.out.println("Usuário alterado com sucesso!");
            }
        } else {
            System.out.println("Usuário não encontrado. Nenhuma alteração realizada.");
        }
    }

    
    public void deletarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findById(usuario.getId());
    
        if (usuarioOptional.isPresent()) {
            usuarioRepositorio.delete(usuarioOptional.get());
            System.out.println("\n********* USUARIO DELETADO COM SUCESSO *********\n");
        } else {
            System.out.println("Usuário não encontrado. Nenhuma exclusão realizada.");
        }
    }
    

    public boolean verificarExistenciaUsuarioPorId(Long id) {
        return usuarioRepositorio.existsById(id);
    }


    public boolean validarUsuario(Usuario usuario) {
        try {
            if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException(" ////// DIGITE O NOME DE USUARIO //////\n");
            }
    
            if (usuario.getEmail() == null || !usuario.getEmail().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                throw new IllegalArgumentException(" ////// EMAIL INVALIDO //////\n");
            }
    
            if (usuario.getSenha() == null || usuario.getSenha().length() < 8 || 
                !usuario.getSenha().matches(".*[A-Z].*") || 
                !usuario.getSenha().matches(".*[a-z].*") || 
                !usuario.getSenha().matches(".*\\d.*") || 
                !usuario.getSenha().matches(".*[!@#$%^&*()-_=+{}|;:'\",.<>?/`~\\[\\]\\\\].*")) {
            
                throw new IllegalArgumentException("\n////// SENHA DEVE TER PELO MENOS 8 CARACTERES, INCLUINDO LETRAS MAIÚSCULAS,\n MINÚSCULAS, NÚMEROS E CARACTERES ESPECIAIS. //////\n");
            }
    
            return true; // Todas as validações passaram
        } catch (IllegalArgumentException e) {
            // Tratar a exceção ou apenas imprimir a mensagem de erro
            System.out.println("\nERRO DE VALIDAÇÃO: " + e.getMessage());
            return false; // Alguma validação falhou
        }
    }
    
    
}
