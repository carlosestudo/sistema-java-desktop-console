package com.ambientese.sistemaformulario.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ambientese.sistemaformulario.entidade.Pergunta;

public interface PerguntaRepositorio extends JpaRepository<Pergunta, Long>{ 

}

