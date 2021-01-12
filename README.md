# TrackMySleepQuality (com RecyclerView)

Projeto criado durante o curso Developing Android Apps with Koltin, da Google. É um aplicativo responsável por fazer um "controle" de sua soneca. O projeto aplica vários conceitos
de desenvolvimento android, como ViewModel, Room, DAO, Threads, Entity. Foi feito com auxílio dos componentes do [Android Jetpack](https://developer.android.com/jetpack/getting-started?hl=pt-br).

Diferentemente do outro branch, nesse você encontrará presente um componente chamado RecyclerView. Se trata de um widget muito usado na criação de longas listas 
com conteúdo dinâmico. De maneira geral, ele tem como função mostrar grandes conjuntos de dados na IU enquanto minimiza o uso de memória. Isso se dá pelo fato de reaproveitar 
componentes já mostrados na tela e apenas atualizar suas informações.

Você pode começar o controle do seu sono a partir de um botão chamado "Start" e, quando acordar, clicar no botão "Stop". Logo após apertar o botão, 
você será levado para o fragmento de avaliação, onde poderá dar um *feedback* de como foi seu sono, assim, retornará para o fragmento principal, onde aparecerá a sua nova "soneca".

## Screenshots 📱
Além das screenshots presentes no outro branch, aqui você verá as telas que foram mudadas ou acrescentadas ao aplicativo.
<p align="center">
  <img src="https://github.com/arturbruno17/TrackMySleepQuality/blob/Project-with-RecyclerView/screenshots/sleep_tracker_recycler_home.png" width="45%" />
  <img src="https://github.com/arturbruno17/TrackMySleepQuality/blob/Project-with-RecyclerView/screenshots/sleep_tracker_recycler_detail.png" width="45%" />
</p>

## Contribuição
O app foi criado e testado em um dispositivo físico, Redmi Note 9s, mas é disponível para qualquer um que queira contribuir.

Caso tenha alguma ideia de como melhorar o app, realize os seguintes passos:

1. Para contribuir, basta fazer um fork. 
(<https://github.com/arturbruno17/fork>)

2. Crie uma branch para sua modificação
(`git checkout -b feature/fooBar`)

3. Faça o commit
(`git commit -am "Add some fooBar"`)

4. Push
(`git push origin feature/fooBar`)

5. Crie um novo *Pull Request*
