Seja bem viado ao Test-Exam
===================

Esse projeto tem a finalidade de avaliar a qualidade do código escrito por mim. Desde já agradeço.

----------


Como executar o projeto
-------------

O projeto tem vários caminhos para poder realizar o teste fique a vontade em escolher a melhor forma.

Desejo que tenha uma ótima experiência para avaliar o projeto.

#### docker-compose

Caso queira executar o projeto via docker-compose, basta executar o comando na raiz do projeto `docker-compose up --build`.    
Assim que a imagem ficar up basta acessar a url http://localhost:8080/swagger-ui/index.html

#### Heroku

Disponibilizei a aplicação na Heroku para que possa ter mais tranquilidade ao avalizar o teste, acesse a url https://intense-spire-23008.herokuapp.com/swagger-ui/index.html

#### Postman

Disponibilizei uma collection do postman para poder agilizar no processo de teste, na collection terá os dados prontos para poderem ser inserido. Para utilizar, importe o arquivo `Test Exam.postman_collection.json`.

#### Swagger

Caso ainda tenha interesse em testar a aplicação pelo swagger, irei disponibilizar alguns mocks aqui para facilitar os testes.
##### HealthcareInstitution
```json
{
  "cnpj": "47.676.383/0001-86",
  "name": "Bernardo e Isabelly Pães e Doces ME"
}
```
##### Exam
```json
{
  "healthcareInstitutionId": 1,
  "patientAge": 63,
  "patientGender": "Feminino",
  "patientName": "Débora Carolina Monteiro",
  "physicianCRM": "149639",
  "physicianName": "DANILO BOAVENTURA JOSÉ DE OLIVEIRA",
  "procedureName": "Creatinofosfoquinase MB"
}
```

Considerações Finais
-------------

- Sobre a parte da instituição poderia acessar somente seus próprios exames criado, creio que poderia melhorar utilizando uma autenticação para a instituição, com isso acredito que ajudaria da credibilidade que aquela instituição criada estaria de fato acessando somente os seus próprios exames, da forma que eu implementei se uma outra instituição soubesse do id, conseguiria acessar o exame.
-  Sobre acessar na documentação do swagger para testar, acredito que seria um pouco chato acessar somente pelo swagger para realizar testes na api, seria interessante termos um front nem que seja simples para que se torne mais agradável para quem for testar, nesse projeto tentei amenizar isso utilizando o postman para poder acelerar. 
