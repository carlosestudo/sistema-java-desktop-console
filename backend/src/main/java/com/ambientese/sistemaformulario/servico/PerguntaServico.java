package com.ambientese.sistemaformulario.servico;

import org.springframework.stereotype.Service;

import com.ambientese.sistemaformulario.entidade.Pergunta;
import com.ambientese.sistemaformulario.repositorio.PerguntaRepositorio;

@Service
public class PerguntaServico {
    
    private PerguntaRepositorio perguntaRepositorio;

    public PerguntaServico(PerguntaRepositorio perguntaRepositorio){
        this.perguntaRepositorio = perguntaRepositorio;
    }

    public void salvar(Pergunta pergunta){
        perguntaRepositorio.save(pergunta);
    }
}
