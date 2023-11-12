package com.ambientese.sistemaformulario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ambientese.sistemaformulario.front.MenuInicial;

@SpringBootApplication
public class SistemaformularioApplication implements CommandLineRunner {

    private final MenuInicial menuInicial;


    public SistemaformularioApplication(MenuInicial menuInicial) {
        this.menuInicial = menuInicial;
    }

    public static void main(String[] args) {
        SpringApplication.run(SistemaformularioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        menuInicial.exibirTelaInicial();
    }
}
