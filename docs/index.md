# Documentação do Programa Java

## Visão Geral

Este projeto é um exemplo de aplicação Java que demonstra a funcionalidade básica de um utilitário de saída para o terminal com formatação personalizada. O objetivo é fornecer uma ferramenta simples para imprimir mensagens formatadas no console.

## Estrutura do Projeto

O projeto é composto pelos seguintes pacotes e classes:

- **`com.example.util`**
  - **`ConsoleFormatter.java`**: Classe responsável pela formatação da saída no console.
  - **`Main.java`**: Classe principal que contém o método `main` e demonstra o uso do `ConsoleFormatter`.

## Dependências

- Java 17 ou superior

## Instalação

1. Clone o repositório:
    ```bash
    git clone https://github.com/usuario/projeto-java.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd projeto-java
    ```

3. Compile o projeto:
    ```bash
    javac -d bin src/com/example/util/*.java src/Main.java
    ```

## Uso

Para executar o programa e ver a formatação personalizada, siga os passos abaixo:

1. Navegue até o diretório `bin`:
    ```bash
    cd bin
    ```

2. Execute a classe principal:
    ```bash
    java com.example.util.Main
    ```

## Exemplo de Código

### `ConsoleFormatter.java`

```java
package com.example.util;

public class ConsoleFormatter {

    public static void printBold(String message) {
        System.out.println("\033[1m" + message + "\033[0m");
    }

    public static void printItalic(String message) {
        System.out.println("\033[3m" + message + "\033[0m");
    }
}
