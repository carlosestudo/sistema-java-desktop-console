package com.ambientese.sistemaformulario.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;


import com.ambientese.sistemaformulario.entidade.Formulario;

public interface FormularioRepositorio extends JpaRepository<Formulario, Long>{ 

}

