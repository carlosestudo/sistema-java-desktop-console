package com.ambientese.sistemaformulario.servico;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ambientese.sistemaformulario.entidade.Desempenho;
import com.ambientese.sistemaformulario.repositorio.DesempenhoRepositorio;

@Service
public class DesempenhoServico {
    
    private DesempenhoRepositorio desempenhoRepositorio;

    public DesempenhoServico(DesempenhoRepositorio desempenhoRepositorio){
        this.desempenhoRepositorio = desempenhoRepositorio;
    }

    public void salvar(Desempenho desempenho){

        desempenhoRepositorio.save(desempenho);
    }

    public List<Desempenho> obterListaDeDesempenhos(){
        return desempenhoRepositorio.findAll();
    }

    
    public List<Desempenho> obterListaDeDesempenhosDaEmpresa(Long idEmpresa) {
        // Supondo que você já tenha uma lista de desempenhos disponível
        List<Desempenho> desempenhos = obterListaDeDesempenhos(); // Implemente este método
    
        // Filtrar a lista para obter apenas os desempenhos da empresa desejada
        List<Desempenho> desempenhosDaEmpresa = desempenhos.stream()
                .filter(desempenho -> desempenho.getEmpresa() != null && desempenho.getEmpresa().getId().equals(idEmpresa))
                .collect(Collectors.toList());
    
        return desempenhosDaEmpresa;
    }
    
    
}
