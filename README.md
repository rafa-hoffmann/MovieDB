# MovieDB

### Projeto que tem como objetivo exibir uma lista de filmes da api do TheMovieDB e os detalhes do filme selecionado.

## Descrição

- No projeto, temos uma activity Main, que tem como objetivo configurar a navegação do app e hospedar os fragments.
- Dentro dessa activity, é primeiro exibido o fragment com a lista dos filmes obtidos da api e, quando algum filme é selecionado, é navegado para o fragment de detalhes do filme

### Da comunicação com a API
- Temos um objeto `Constants`, que tem como responsabilidade definir a URL base de API e a URL base para as imagens carregadas no app
- Temos um objeto `RetrofitInstance`, que gerencia a instância que realiza a comunicação com a API
- Temos uma interface `MovieService`, com a declaração do método acessado na API e seu retorno, carregado nas classes `ResponseItems` e `Movie`

#### Carregamento de dados da API
- A lista de filmes é carregada de forma paginada, utilizando o Paging3, do Android
- Na paginação, temos a `MoviesPagingSource`, que controla o carregamento dos dados com paginação
- Também há a classe `MoviesRepository`, que faz comunicação com a `MoviesPagingSource` e transforma a resposta carregada em um LiveData
- O `MovieListViewModel` chama o método da `MoviesRepository` e a activity exibe os valores do primeiro na lista
