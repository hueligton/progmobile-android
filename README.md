# Aplicação Android - Venda de Ingressos
**Programação para Dispositivos Móveis 2019/1 - FACOM - UFMS**  
**Sob a orientação da Prof.ª Dra. Ana Karina Dourado Salina de Oliveira**

## Descrição
Aplicação Android para a venda de ingressos com utilização de Web Service REST.

## Equipe
1. Elmo Júnior Ficagna - [@elmojrf](https://github.com/elmojrf) - Web Service
2. Felipe Caggi - [@felipecaggi](https://github.com/felipecaggi) - View
3. Hueligton Pereira de Melo - [@hueligton](https://github.com/hueligton) - Model

## Repositório do webservice
Web Service REST - https://github.com/elmojrf/progmobile-webservice

## Tecnologias
- Implementação: Java 1.8
- Web Service: NodeJS
- Bibliotecas: GSon 2.8.5, Picasso 2.71828, Volley 1.1.1

## Repositórios
- src/main/java/com/example/progmobile_android/ - contém Activities dos layouts
- src/main/java/com/example/progmobile_android/model - contém Facade de acesso aos managers
- src/main/java/com/example/progmobile_android/repository - contém classe de comunicação com o WebService
- src/main/java/com/example/progmobile_android/model/entities - contém as entidades (.java)
- src/main/java/com/example/progmobile_android/model/manager - contém managers das entidades que serão responsáveis pela persistência e interface com o Web Service
- src/main/res/ - contém layouts (.xml), imagens e configurações (strings, cores, network config, styles, etc)
