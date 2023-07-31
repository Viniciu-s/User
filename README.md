## 💻 Para rodar a aplicação tenha o Java 17 e o docker instalados e configurados em sua maquina.
* Se você tem uma máquina com o sistema operacional Windows, instale o wsl para usar o docker, para mais informações consulte https://learn.microsoft.com/pt-br/windows/wsl/install.

Agora execute os seguintes comandos para baixar as imagens:
* docker pull mysql
* docker pull vieiraenabled/user:1.0
* Depois de baixar as imagens execute o comando: docker compose up, por exemplo "vinicius@DESKTOP-KRTIKUD:/Documentos/User/crud$ docker compose up"
* A aplicação irá rodar na porta 8080, então entre em http://localhost:8080/.
