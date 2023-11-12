package com.ambientese.sistemaformulario.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="tb_pergunta")
public class Pergunta {
    
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean resposta;
    private String enumciado;

    @ManyToOne
    @JoinColumn(name = "formulario_id") 
    private Formulario formulario;

    
    public Pergunta(){
        
    }
    
    public Pergunta(boolean resposta, String enumciado, Formulario formulario) {
        this.resposta = resposta;
        this.enumciado = enumciado;
        this.formulario = formulario;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean isResposta() {
        return resposta;
    }
    
    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }


    
    public String getEnumciado() {
        return enumciado;
    }
    
    public void setEnumciado(String enumciado) {
        this.enumciado = enumciado;
    }
    
    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pergunta other = (Pergunta) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    

}
