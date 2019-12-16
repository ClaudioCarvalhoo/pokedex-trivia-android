# Memória

O consumo de memória em nosso aplicativo tende a manter-se sempre na faixa dos 55MB, não fugindo muito disso em qualquer momento do uso, com alguns picos rápidos nas telas que contêm as perguntas.
<br />
![image](./imagens/mem-summary.png)

## Caça aos memory leaks

Em algumas situações, nos deparamos com casos de vazamentos de memória, quase sempre associados ao uso de **fragments**. Um caso interessante a se mencionar é o do `inflater` no `QuestionsFragment`.

O `ViewPager` apenas carrega a página atual, a anterior e a próxima, para garantir que a animação é sempre fluída. Isso significa que, quase sempre que o usuário passa de página, uma View será destruída - aumentando o risco de vazamento de memória.

No entanto, o caso de uso do `inflater` em `ViewModels` pareceu causar um _memory leak_ inesperado dentro da função `onCreateView` do _fragment_, apesar de termos usado uma variável de escopo para guardar a instância do `inflater`. O problema pode estar relacionado em como o `ViewPager` lida com `LiveData`, como visto [nessa issue](https://github.com/square/leakcanary/issues/1137).

Para solucionar o problema, guardamos a instância do `inflater` como um atributo privado de `QuestionsFragment`, e a configuramos como `null` quando a View é destruída.
