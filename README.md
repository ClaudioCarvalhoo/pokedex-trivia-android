# PokéQuizz

## Descrição do projeto

_PokéQuizz_ é um aplicativo de quiz online, onde os jogadores podem competir entre si para comparar seus conhecimentos sobre _Pokémon_. Nosso diferencial é a conexão com a [PokeApi](https://pokeapi.co/) para obter todos os recursos disponíveis sobre os jogos principais da série e gerar automaticamente perguntas baseadas nas categorias escolhidas pelos jogadores.

Nosso foco é que a experiência dos usuários seja altamente personalizável, assim como as competições que eles poderão criar com seus amigos. Assim, cada um pode ter o nível de complexidade e dificuldade que mais se adeque aos seus conhecimentos enquanto joga.

O aplicativo é voltado para fãs da série _Pokémon_, procurando por uma forma divertida e competitiva de testar seus conhecimentos. Por meio de uma personalização interativa do conteúdo das perguntas, buscamos atingir o público dos fãs hardcore da série (que tem carência desse conteúdo), sem perder os fãs mais casuais.

Existem diversos aplicativos similares no mercado, trazendo uma proposta similar quanto ao que pretendem oferecer. A grande maioria, porém, traz informações de um nível muito superficial, provavelmente tentando atingir a um público mais casual e, ainda assim, falhando em trazer muito mais que apenas perguntas pré-definidas, o que deixa a desejar pois a franquia é muito rica e contém muito mais conteúdo que os fãs gostariam de ver nesse tipo de aplicativo.

Nós gostaríamos de dar ao jogador a possibilidade de escolher que categorias de perguntas ele vai receber, englobando todas as informações dos jogos principais da franquia, obtidos por meio da PokeApi. Assim, o próprio usuário tem o poder de decidir se quer perguntas sobre temas mais superficiais como nomes dos Pokemon quanto temas mais avançados como efeitos dos items. Da maneira como estruturamos nosso servidor e a conexão do aplicativo com este, é bastante fácil adicionar novas categorias com o tempo, e nada terá que ser mudado no aplicativo para que essas novas categorias apareçam para ser utilizadas na criação de novas salas.

## Organização dos arquivos

Escolhemos estruturar nossos arquivos de acordo com as classes que eles implementavam ou sua abstração e função no restante do código:

- `activities` - Implementando `AppCompatActivity`
- `adapters` - Implementando `RecyclerView.Adapter` e `FragmentPagerAdapter`'
- `apiHelper` - Abstraindo as chamadas à API e responsável pela configuração do `Retrofit`
- `apiHelper.entities` - Classes que representam as entidades do banco de dados da API
- `fragments` - Implementando `Fragment`
- `viewModels` - Implementando `ViewModel`

## Dependências externas

- [Retrofit](https://square.github.io/retrofit/) - Abstração das chamadas para API REST, facilitando o processo de chamadas de _threads_ para não bloquear a _thread_ principal
- [GSON](https://github.com/google/gson) - Serialização de JSON's vindos da API para as `entities` definidas (e vice-versa)
- [Picasso](https://square.github.io/picasso/) - Abstração de _download_ de imagens, facilitando o processo de criação de _threads_ para lidar com _bitmaps_
- [StepView](https://github.com/shuhart/StepView) - Implementação da _view_ de passos usada na listagem de perguntas, para saber qual pergunta o usuário estava respondendo no momento

## Escolhas para boas práticas

### ListView vs. RecyclerView

A tela de encontrar uma sala lista todas as salas criadas na API, dessa forma:

<div style="width: 100%; display: flex; flex-direction: row; justify-content: center">
<img src="[https://i.imgur.com/upjgXFM.png](https://i.imgur.com/upjgXFM.png)" />
</div>

No escopo do nosso projeto, com poucos usuários, essa lista não será muito grande. Porém, em um ambiente real para o público alvo, logo a quantidade de salas poderia subir bastante.

Por isso, decidimos usar `RecyclerView` ao invés de `ListView`. Apesar de possuir uma implementação menos intuitiva, o uso de `RecylerView` é melhor recomendado para listas de tamanhos dinâmicos que possam vim a crescer bastante.

A mesma lógica foi aplicada para o fragmento _Leaderboard_, na visualização de informações da sala.

### Passando informações entre salas

Quanto ao problema de passar informações entre salas, foi-se pesquisado implementações que o faziam através de referências entre classes. Por exemplo, supondo a transição entre a tela de listagem de salas e informações da sala escolhida, a informação de qual sala foi escolhida poderia ser compartilha pelas atividades da seguinte forma:

- Configurar um atributo público `selectedRoom` para `activities.FindRoom`;
- Quando o usuário clicasse numa sala, configurava o atributo `selectedRoom` com essa sala;
- Transitava para a `activities.RoomInfo`;
- `activities.RoomInfo` poderia acessar a sala escolhida através de `activities.FindRoom.selectedRoom`.

Isso apresenta algumas desvantagens:

1. Caso o aplicativo iniciasse a partir de `activities.RoomInfo`, o atributo `selectedRoom` nunca teria sido configurado;
2. Caso a instância de `activities.FindRoom` fosse terminada, o _garbage colector_ iria anular a referência de `selectedRoom`.

**Como passar informações, então?**
Passando uma instância de `Bundler` para o `intent`:

```kotlin
/* activities.FindRoom */
private lateinit val rooms

override fun onCreate() {
	[...]
	rooms = getRooms() /* Chamada à API, por exemplo. */
}

/* Listener passado para a lista de salas */
private fun setItemListener(selectedRoom : Room) {
	[...]
	goToRoomInfo(room : Room)
}

private fun goToRoomInfo(room : Room) {
	val intent = Intent(this, RoomInfo::class.java)
	val b = Bundle()
	b.putString("roomId", room.id)
	intent.putExtras(b)
	startActivity(intent)
}
```

Dessa forma, a _activity_ de informações da sala se torna independente, fazendo sua própria chamada à API para requisitar uma sala a partir de seu ID.

## Escolhas de usabilidade

Queríamos ter certeza que cada ação que o usuário tomasse seria refletida na tela, de um ponto de vista de design. Por isso, realizamos alguns passos extras:

- Chamadas à API: _ spinner_ de _loading_ sempre que uma chamada à API é feita, para que o usuário saiba que algo está sendo carregado (ver `activities.FindRoom`, `activities.RoomInfo`, `activities.Submit`, etc.;
- Jornada do usuário: após responder as perguntas, o usuário volta para a tela de informações da sala, onde pode ver o _leaderboard_ e ver sua posição (ver `activities.Questions`).
- Fluxo bem definido: uma vez que o jogador terminasse de jogar e estivesse na tela de informações da sala, a última _activity_ que ele esteve presente foi a de perguntas. Portanto, se ele voltasse com a _feature_ de voltar de _activity_ embutida no Android, ele ficaria preso em um _looping_. Por isso, implementamos o botão de navegação no _topbar_ em `activities.RoomInfo`, que **sempre** volta para a listagem de campanha. O fluxo se torna coerente e intuitivo para o usuário.

<div style="width: 100%; display: flex; flex-direction: row; justify-content: center">
<img src="[https://i.imgur.com/gc4hPPL.png](https://i.imgur.com/gc4hPPL.png)" />
</div>

## Issues/To do

1. Quando rodado em um simulador, a primeira chamada à API **sempre** leva _timeout_. Para corrigir, basta voltar para a tela principal e ir para a listagem de salas novamente.
2. Uma vez que uma chamada à API falha, o usuário não recebe um ponto de ação (por exemplo, "aperte aqui para tentar requisitar novamente).

## Créditos

- [Henrique Caúla](https://github.com/hcaula)
- [Claudio Carvalho](https://github.com/ClaudioCarvalhoo)
