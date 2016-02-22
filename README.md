```
git clone https://gitlab.com/lennonjesus/linkar.git
```

### /linkar-api
  Backend construído com Maven e SpringBoot
  ```
  Deixa o IntelliJ trabalhar.
  ```

### /frontend
  Frontend do site administrativo. Aqui serão cadastrados os dados que alimentarão o site e permitirá aos lojistas visualizarem dashboars estatísticos e etc.
  Usa Angular, Bootstrap, Bower e Gulp
  ```
  $ npm install
  ```
  ```
  $ bower install
  ```
  ```
  $ gulp serve
  ```

### /frontend-site
  Frontend do site público. Template estático Bootstrap que precisa ser convertido para puxar dados do backend.
  Deve ser convertido para usar Angular, Bower e Gulp
  ```
  $ npm install
  ```
  ```
  $ bower install
  ```
  ```
  $ gulp serve
  ```

### /linkar-frontend
  Isso é uma versão mais antiga. Migrei o que tinha feito aqui para o /frontend. Em breve será apagado.

Necessário ter instalado Java 8, Maven, MongoDB, NodeJS.

Configuração do ambiente

### Configuração do ambiente

#### NVM
```
$ curl -o- https://raw.githubusercontent.com/creationix/nvm/v0.31.0/install.sh | bash
```

NODE
```
$ nvm install stable
$ nvm use stable
```

BOWER
```
$ npm install -g bower
```

GULP
```
$ npm install -g gulp
```

GIT FLOW

Um modelo de trabalho com branches: http://danielkummer.github.io/git-flow-cheatsheet/index.pt_BR.html

Um cheatsheet do git glow: http://danielkummer.github.io/git-flow-cheatsheet/index.pt_BR.html

```
$ curl -L -O https://raw.github.com/nvie/gitflow/develop/contrib/gitflow-installer.sh
$ sudo bash gitflow-installer.sh
```