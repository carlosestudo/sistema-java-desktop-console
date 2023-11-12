package com.ambientese.sistemaformulario.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ambientese.sistemaformulario.entidade.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{ 

}

