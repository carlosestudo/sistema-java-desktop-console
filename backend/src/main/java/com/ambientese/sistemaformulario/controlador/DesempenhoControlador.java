package com.ambientese.sistemaformulario.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.ambientese.sistemaformulario.entidade.Desempenho;
import com.ambientese.sistemaformulario.servico.DesempenhoServico;

@Controller
public class DesempenhoControlador {

    private DesempenhoServico desempenhoServico;

    public DesempenhoControlador(DesempenhoServico desempenhoServico){
        this.desempenhoServico = desempenhoServico;

    }
    
    public List<Desempenho> obterDesempenhoDaEmpresa(Long idEmpresa){
        return desempenhoServico.obterListaDeDesempenhosDaEmpresa(idEmpresa);
    }
}
