# Controle de ponto API
API do sistema de ponto inteligente com Java e Spring Boot.
### Como executar a aplicação
Certifique-se de ter o Git instalado.
```
git clone https://github.com/franklinpr2010/controleponto-api.git
cd controleponto-api
./mvnw spring-boot:run
API será executada em http://localhost:8080

```

Para testar a API de segurança basta executar o postman e http://localhost:8080/auth, colocar o atributo senha e e-mail
quando fizer essa requisição deve receber sucesso e receber o token.  

{ "email": 'franklin@gmail.com', "senha": '123456' }  

Depois do token obtido ir na aba Herders, adicionar o atributo Authorization e o valor Beather: Token e novamente fazer a requisição de algum metodo que requeira o token do usuário.  


Obs: Instalar o maven e configurar a variável de ambiente.  
