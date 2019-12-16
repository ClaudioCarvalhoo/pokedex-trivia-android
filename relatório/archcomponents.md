# Architecture Components

Em dois casos, usamos a estrutura de `Fragment`, `Adapter` e `ViewModel`:

### Room Info

<div style="width: 100%; display: flex; flex-direction: row; justify-content: center">
  <img src="https://i.imgur.com/UhNBW2x.png">
  <img src="https://i.imgur.com/gbvIkvM.png">
</div>

- A _activity_ de informação de sala consiste de um **ViewPager**, que permite o _scroll_ entre as duas tabs, "GENERAL" e "LEADERBOARD".
- A esse `ViewPager`, é dado um **RoomInfoAdapter**, que será capaz de controlar que `Fragment` deverá ser exibido para cada tab - `GeneralFragment` ou `LeaderboardFragment`;
- Ambos os `Fragment` configuram aparições de UI, como o `RecylerView` do leaderboard. Eles pegam seus dados através do mesmo `ViewModel`, o `RoomInfoViewModel`.

Utilizar essa arquitetura foi útil para manter controle de que janela deve ser exibida em cada tab com maior segurança e fluidez, sem precisar trocar de `activity`.

### Questions

<div style="width: 100%; display: flex; flex-direction: row; justify-content: center">
  <img src="https://i.imgur.com/MXcfSQw.png">
  <img src="https://i.imgur.com/qXKt6kJ.png">
</div>

A mesma arquitetura foi utilizada. Uma peculiridade é que o `QuestionsFragment` recebe um par de respostas já selecionadas e as alternativas da pergunta. Isso se tornou necessário, pois, para evitar _memory leak_, escolhemos remover a referência do _callback_ deste _fragment_. O `ViewPager` sempre mata todas as páginas que não são a próxima e a anterior (para prover uma animação fluida de _scroll_), tendo grande risco de vazamento de memória. Por conta disso, cada _fragment_ tinha que saber que respostas já foram selecionadas, pois, quando forem renderizar as alternativas, já marcar aquelas que já foram seleciondas.

Mais sobre nossa caça de aos _memory leaks_ [aqui](https://github.com/ClaudioCarvalhoo/pokedex-trivia-android/blob/master/relat%C3%B3rio/memoria.md).
