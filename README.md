# Classificados de Veículos
<img src="https://cdn.discordapp.com/attachments/1221951787318378607/1221954522549719060/image.png?ex=661474d4&is=6601ffd4&hm=36309bbfbabb8306423a48ad5909e2f9c4094a969b238945fd3980ddc02c5c4b&" alt="Imagem da aplicação de classificados"/>
<p>Esta aplicação é responsável por criar um sistema de classificados de veículos, utilizando uma fila de mensagens para processamento assíncrono. Ao adicionar um novo veículo, uma mensagem é criada e colocada na fila. Essa mensagem contém as informações do veículo a ser cadastrado. Em seguida, o consumidor da fila consome essa mensagem, recupera as informações do veículo e persiste os dados no banco de dados criado em memória, utilizando o H2. Por fim, a lista atualizada de veículos é exibida na tabela de classificados.</p>

<p>Para esse projeto foi utilizado o provedor JMS ActiveMQ, então será necessário ter essa ferramenta instalada para a execução, você pode obtê-la em: http://activemq.apache.org/</p>

<p>Para acessar o ActiveMQ: <strong>http://localhost:8161/admin</strong><br>
<strong>user: admin</strong><br>
<strong>password: admin</strong>
</p>
<p>Ao executar a aplicação Spring, é possível ver a fila de mensagens criada e suas informações em "Queues".</p>

<hr>

<table>
<caption><strong>Urls da aplicação:</strong></caption>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Descrição</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>http://localhost:8080/classificados/listar</td>
      <td>Listar veículos</td>
    </tr>
    <tr>
      <td>http://localhost:8080/classificados/adicionar</td>
      <td>Adicionar um novo veículo</td>
    </tr>
  </tbody>
</table>

## Ferramentas:

- Spring Framework
- Bootstrap
- ActiveMQ
- Thymeleaf
- JDK 11
- Banco de dados H2
- Eclipse IDE
- Maven


