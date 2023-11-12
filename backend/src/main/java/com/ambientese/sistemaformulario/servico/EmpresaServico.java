package com.ambientese.sistemaformulario.servico;

import org.springframework.stereotype.Service;

import com.ambientese.sistemaformulario.entidade.Empresa;
import com.ambientese.sistemaformulario.repositorio.EmpresaRepositorio;

@Service
public class EmpresaServico {
    
    private EmpresaRepositorio empresaRepositorio;

    public EmpresaServico(EmpresaRepositorio empresaRepositorio){
        this.empresaRepositorio = empresaRepositorio;
    }

    public void salvar(Empresa empresa){

        if (verificarEmpresa(empresa)) {
            if (empresa.getId() != null && verificarID(empresa.getId())) {
                // Se o ID não for nulo e for válido, realiza a alteração
                empresaRepositorio.save(empresa);

                System.out.println("\n***** EMPRESA ALTERADA COM SUCESSO *****\n");
            } else {
                // Se o ID for nulo, realiza a operação de salvar
                empresaRepositorio.save(empresa);
                System.out.println("\n***** EMPRESA SALVA COM SUCESSO *****\n");
            }
        } else {
            System.out.println("/////////// PREENCHA TODOS OS CAMPOS ///////////");
        }
    }
    

    public void listaTudo(){
       
            Iterable<Empresa> empresas = empresaRepositorio.findAll();
        
           
            for (Empresa empresa : empresas) {
                System.out.println("\n==============================\n");

                System.out.println("ID: " + empresa.getId());
                System.out.println("Razão Social: " + empresa.getRazaoSocial());
                System.out.println("CNPJ: " + empresa.getCnpj());
                System.out.println("Endereço: " + empresa.getEndereco());

                System.out.println("\n==============================\n");
            }
        
    }

    public void deletar(Long id){
        empresaRepositorio.deleteById(id);
    }

    public boolean verificarEmpresa(Empresa empresa) {
        if (empresa.getRazaoSocial() == null || empresa.getRazaoSocial().trim().isEmpty()) {
            throw new IllegalArgumentException("A razão social não pode ser nula ou vazia.");
        }
    
        if (empresa.getPorte() == null) {
            throw new IllegalArgumentException("O porte não pode ser nulo.");
        }
    
        if (empresa.getEndereco() == null || empresa.getEndereco().trim().isEmpty()) {
            throw new IllegalArgumentException("O endereço não pode ser nulo ou vazio.");
        }
    
        if (empresa.getCnpj() == null || empresa.getCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("O CNPJ não pode ser nulo ou vazio.");
        }
    
        // Se todas as validações passaram, retorna true indicando que a empresa é válida
        return true;
    }
    
    public boolean verificarID(Long id){
        return empresaRepositorio.existsById(id);
    }

    public Empresa obterPorId(Long id) {
        return empresaRepositorio.findById(id).orElse(null);
    }

}
