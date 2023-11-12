package com.ambientese.sistemaformulario.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ambientese.sistemaformulario.entidade.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long>{ 

}

