package com.ambientese.sistemaformulario.controlador;

import org.springframework.stereotype.Controller;

import com.ambientese.sistemaformulario.entidade.Usuario;
import com.ambientese.sistemaformulario.servico.UsuarioServico;

import jakarta.validation.Valid;

@Controller
public class UsuarioControlador {
    
    private UsuarioServico usuarioServico;

    public UsuarioControlador(UsuarioServico usuarioServico){
        this.usuarioServico = usuarioServico; 
    }

    public void salvar(@Valid Usuario usuario){
        usuarioServico.salvar(usuario);
    }

    public void listarTudo(){
        usuarioServico.listarTudo();
    } 

    public void alterarUsuario(Usuario usuario){
        usuarioServico.alterarUsuario(usuario);
    }

    public void deletarUsuario(Usuario usuario){
        usuarioServico.deletarUsuario(usuario);
    }

    public boolean verificarExistenciaUsuarioPorId(Long id) {   
        return usuarioServico.verificarExistenciaUsuarioPorId(id);
    }

    public boolean validarUsuario(Usuario usuario){
        return usuarioServico.validarUsuario(usuario);
    }
}
