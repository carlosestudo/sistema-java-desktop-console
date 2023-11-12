package com.ambientese.sistemaformulario.controlador;

import org.springframework.stereotype.Controller;

import com.ambientese.sistemaformulario.entidade.Empresa;
import com.ambientese.sistemaformulario.servico.EmpresaServico;

@Controller
public class EmpresaControlador {
    
    private EmpresaServico empresaServico;

    public EmpresaControlador(EmpresaServico empresaServico){
        this.empresaServico = empresaServico; 
    }

    public void salvar(Empresa empresa){
        empresaServico.salvar(empresa);
    }

    public void listarTudo(){
        empresaServico.listaTudo();

    }

    public void deletar(Long id){
        empresaServico.deletar(id);
    }


    public boolean verificarID(Long id){
       return empresaServico.verificarID(id);
    }

    public Empresa obterPorId(Long id){
        return empresaServico.obterPorId(id);
    }
}
