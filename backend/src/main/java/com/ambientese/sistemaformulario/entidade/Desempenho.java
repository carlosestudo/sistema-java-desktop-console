package com.ambientese.sistemaformulario.entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name="tb_desempenho")
public class Desempenho {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int qtdPerguntasCertas;
    private int qtdPerguntasErradas;
    private boolean aprovado;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne
    @JoinColumn(name = "formulario_id")
    private Formulario formulario;
    
    public Desempenho() {
    }

    public Desempenho(Long id, int qtdPerguntasCertas, int qtdPerguntasErradas, boolean aprovado, Empresa empresa,
            Formulario formulario) {
        this.id = id;
        this.qtdPerguntasCertas = qtdPerguntasCertas;
        this.qtdPerguntasErradas = qtdPerguntasErradas;
        this.aprovado = aprovado;
        this.empresa = empresa;
        this.formulario = formulario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQtdPerguntasCertas() {
        return qtdPerguntasCertas;
    }

    public void setQtdPerguntasCertas(int qtdPerguntasCertas) {
        this.qtdPerguntasCertas = qtdPerguntasCertas;
    }

    public int getQtdPerguntasErradas() {
        return qtdPerguntasErradas;
    }

    public void setQtdPerguntasErradas(int qtdPerguntasErradas) {
        this.qtdPerguntasErradas = qtdPerguntasErradas;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
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
        Desempenho other = (Desempenho) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    
}
