package com.ambientese.sistemaformulario.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.ambientese.sistemaformulario.entidade.Formulario;
import com.ambientese.sistemaformulario.entidade.Pergunta;
import com.ambientese.sistemaformulario.servico.FormularioServico;


@Controller
public class FormularioControlador {
    
    private FormularioServico formularioServico;

    public FormularioControlador(FormularioServico formularioServico){
        this.formularioServico = formularioServico; 
    }

    public void salvar(Formulario formulario){
        formularioServico.salvar(formulario);
    }

    public void listarTudo(){
        formularioServico.listarTudo();
    }

    public boolean verificaID(Long id){
        return formularioServico.verificaID(id);
    }

    public Formulario obterPorId(Long id){
        return formularioServico.obterPorId(id);
    }

    public void alterar(Formulario formulario){
        formularioServico.alterar(formulario);
    }

    public void  listaExibiPerguntasDoFormulario(Long id){
        formularioServico. listaExibiPerguntasDoFormulario(id);
    }

    public List<Pergunta> listarPerguntasDoFormulario(Long id){
        return formularioServico.listarPerguntasDoFormulario(id);
    }

    public void removerPerguntaDoFormulario(Long idFormulario, Long idPergunta){
        formularioServico.removerPerguntaDoFormulario(idFormulario, idPergunta);
    }

    public boolean validarFormulario(Formulario formulario){
        return formularioServico.validarFormulario(formulario);
    }

}   
