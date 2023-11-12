package com.ambientese.sistemaformulario.servico;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ambientese.sistemaformulario.entidade.Formulario;
import com.ambientese.sistemaformulario.entidade.Pergunta;
import com.ambientese.sistemaformulario.repositorio.FormularioRepositorio;
import com.ambientese.sistemaformulario.repositorio.PerguntaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class FormularioServico {

    private final FormularioRepositorio formularioRepositorio;
    private final PerguntaRepositorio perguntaRepositorio;

    @Autowired
    public FormularioServico(FormularioRepositorio formularioRepositorio, PerguntaRepositorio perguntaRepositorio) {
        this.formularioRepositorio = formularioRepositorio;
        this.perguntaRepositorio = perguntaRepositorio;
    }

    public void salvar(Formulario formulario) {
        formularioRepositorio.save(formulario);
    }

    public void listarTudo() {
        Iterable<Formulario> formularios = formularioRepositorio.findAll();
    
        
        for (Formulario formulario : formularios) {
            System.out.println("\n==============================\n");
            System.out.println("ID: " + formulario.getId());
            System.out.println("Titulo: " + formulario.getTitulo());
            System.out.println("Tipo Formulario: " + formulario.getTipoFormulario());
            System.out.println("\n==============================\n");
        }
    }


    public boolean verificaID(Long id){
        return formularioRepositorio.existsById(id);
    }

    public Formulario obterPorId(Long id) {
        return formularioRepositorio.findById(id).orElse(null);
    }

    public void alterar(Formulario formulario) {

        if (verificaID(formulario.getId())) {
    
            Formulario atualizarFormulario = formularioRepositorio.findById(formulario.getId()).orElse(null);
    
            if (atualizarFormulario != null) {
                // Update the fields with values from the provided formulario
                atualizarFormulario.setTipoFormulario(formulario.getTipoFormulario());
                atualizarFormulario.setTitulo(formulario.getTitulo());
    
                // Clear existing questions and add new questions
                atualizarFormulario.getPerguntas().clear();
                atualizarFormulario.getPerguntas().addAll(formulario.getPerguntas());
    
                // Save the updated Formulario back to the repository
                formularioRepositorio.save(atualizarFormulario);
            }
        }
    }

    public void listaExibiPerguntasDoFormulario(Long idFormulario) {
        try {
            
            Formulario formulario = formularioRepositorio.findById(idFormulario)
                    .orElseThrow(() -> new NoSuchElementException("Formulário não encontrado com o ID: " + idFormulario));
    
          
            Hibernate.initialize(formulario.getPerguntas());
    
            // Verifica se o formulário possui perguntas
            if (formulario.getPerguntas() != null && !formulario.getPerguntas().isEmpty()) {
                // Itera sobre as perguntas e imprime suas informações no console
                System.out.println("\n*****************************\n");
                System.out.println("PERGUNTAS DO FORMULÁRIO " + formulario.getTitulo());
                System.out.println("\n*****************************\n");
    
                for (Pergunta pergunta : formulario.getPerguntas()) {
                    System.out.println("\n=============================\n");
                    System.out.println("ID: " + pergunta.getId());
                    System.out.println("Enunciado: " + pergunta.getEnumciado());
                    System.out.println("Resposta: " + pergunta.isResposta());
                    System.out.println("\n=============================\n");
                }
            } else {
                System.out.println("\n*************************************\n");
                System.out.println("O FORMULÁRIO " + formulario.getTitulo() + " NÃO POSSUI PERGUNTAS");
                System.out.println("\n*************************************\n");
            }
    
        } catch (NoSuchElementException e) {
            System.out.println("\n///////// FORMULÁRIO NÃO ENCONTRADO COM O ID: " + idFormulario + " /////////\n");
        }
    }
    

    public List<Pergunta> listarPerguntasDoFormulario(Long idFormulario) {
        // Obtém o formulário pelo ID
        Formulario formulario = formularioRepositorio.findById(idFormulario).orElse(null);
    
        // Verifica se o formulário foi encontrado
        if (formulario != null) {
            // Certifique-se de que o formulário está carregado com suas perguntas
            Hibernate.initialize(formulario.getPerguntas());
    
            // Retorna a lista de perguntas
            return formulario.getPerguntas();
        } else {
            System.out.println("Formulário não encontrado com ID: " + idFormulario);
            // Se o formulário não for encontrado, retorna uma lista vazia ou lança uma exceção, dependendo do seu design
            return Collections.emptyList(); // ou return new ArrayList<>();
        }
    }
    

    @Transactional
    public void removerPerguntaDoFormulario(Long idFormulario, Long idPergunta) {
        // Obtém o formulário pelo ID
        Formulario formulario = formularioRepositorio.findById(idFormulario).orElse(null);

        // Verifica se o formulário foi encontrado
        if (formulario != null) {
            // Certifique-se de que o formulário está carregado com suas perguntas
            Hibernate.initialize(formulario.getPerguntas());

            // Encontrar a pergunta pelo ID
            Pergunta pergunta = formulario.getPerguntas().stream()
                    .filter(p -> p.getId().equals(idPergunta))
                    .findFirst()
                    .orElse(null);

            // Verifica se a pergunta foi encontrada
            if (pergunta != null) {
                // Remova a pergunta da coleção
                formulario.getPerguntas().remove(pergunta);

                perguntaRepositorio.delete(pergunta);
                
                formularioRepositorio.save(formulario);
            } else {
                System.out.println("Pergunta não encontrada com ID: " + idPergunta);
            }
        } else {
            System.out.println("Formulário não encontrado com ID: " + idFormulario);
        }
    }


    public boolean validarFormulario(Formulario formulario) {

        try {
    
            if (formulario.getTitulo() == null || formulario.getTitulo().isEmpty()) {
                throw new IllegalArgumentException("////// DIGITE UM TÍTULO PARA O FORMULÁRIO //////\n");
            }
    
            if (formulario.getQtdMinimaAcerto() == null) {
                throw new IllegalArgumentException("////// DIGITE O NÚMERO MÍNIMO DE ACERTOS //////\n");
            }
    
            return true;
    
        } catch (IllegalArgumentException e) {
            // Tratar a exceção ou apenas imprimir a mensagem de erro
            System.out.println("\nERRO DE VALIDAÇÃO: " + e.getMessage());
            return false; // Alguma validação falhou
        }
    
    }
    


}


  
    

    

