package com.ambientese.sistemaformulario.servico;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ServicoScanner {

    public Scanner scanner() {
        return new Scanner(System.in);
    }
}