package com.ambientese.sistemaformulario.entidade;

import java.util.ArrayList;
import java.util.List;

import com.ambientese.sistemaformulario.entidade.enums.TipoFormulario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_formulario")
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private TipoFormulario tipoFormulario;
    private Integer qtdMinimaAcerto;

    @OneToMany(mappedBy = "formulario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pergunta> perguntas = new ArrayList<>();


    public Formulario() {
    }

    public void adicionarPergunta(Pergunta pergunta) {
        // Configura a relação bidirecional
        pergunta.setFormulario(this);
        // Adiciona a pergunta à lista
        this.perguntas.add(pergunta);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoFormulario getTipoFormulario() {
        return tipoFormulario;
    }

    public void setTipoFormulario(TipoFormulario tipoFormulario) {
        this.tipoFormulario = tipoFormulario;
    }

    public Integer getQtdMinimaAcerto() {
        return qtdMinimaAcerto;
    }

    public void setQtdMinimaAcerto(Integer qtdMinimaAcerto) {
        this.qtdMinimaAcerto = qtdMinimaAcerto;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
        Formulario other = (Formulario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
