## 1 INTRODUÇÃO
Nosso trabalho tem como base em um tema específico em relação à Agents in 
the City, na qual visava uma competição no ano de 2018 que consistia em um cenário 
que a distribuição de água estava comprometida por falta de poços e o tema proposto 
para os participantes era no desenvolvimento de vários agentes que pudessem se 
movimentar pelas ruas de uma cidade realista e um dos objetivos dessas equipes que 
se enfrentavam era na construção de poços com massium (moeda do jogo), que por 
sua vez era uma recompensa para as equipes quando completava certos trabalhos 
ou em trocas de itens perante o jogo. Cada agente possui características e são elas: 
bateria (tempo de locomoção sem a necessidade de recarregar), capacidade (quanto 
volume consegue carregar), velocidade, visão (distância do campo de visão) e 
habilidade (tempo que pode completar certas tarefas).
O cenário continha 4 responsabilidades distintas: carros, drones, motocicletas 
e caminhões, categorizado por sua capacidade e energia, além disso, o mapa das 
cidades disponíveis eram obtidas dos dados do OpenStreetMap, assim como o 
roteamento que era fornecido pelo servidor do concurso. Cada simulação 
apresentava em um conjunto de itens aleatórios perante os mapas e esses itens 
divergem por seu volume e a forma que poderiam ser adquiridos. Os agentes da 
mesma forma que os itens, também eram posicionados aleatoriamente no mapa, 
assim como as instalações (lojas, oficinas, estação de carregamento da bateria, lixões 
e etc). Nas lojas os itens montados podem ser negociados pelos agentes, nas 
estações de carregamento a bateria dos agentes eram recarregadas, nas oficinas os 
agentes podem montar itens a partir de outros itens, nos lixões os itens podem ser 
destruídos em despejos e nos armazéns os itens podiam ser guardados até um 
determinado limite de volume específico.
Os trabalhos eram criados aleatoriamente pelo sistema e os jobs disponíveis 
eram na aquisição, montagem e transporte de mercadorias, as localizações no 
cenário eram fornecidas pela latitude e longitude, ou seja, cada item, cada trabalho e 
missão, possuía uma localização com esse par de valores de latitude e longitude.

## 2 DESENVOLVIMENTO
Com as informações coletadas do nosso tema e com algumas análises e 
reuniões para definir o desenvolvimento do projeto em prol de simular um cenário que 
fosse parecido com o da competição Agents in the City, 2018, foi decidido que a 
linguagem de programação para o desenvolvimento deste trabalho fosse Java.
Porém, o desafio proposto pela competição era bem detalhado, difícil e bem difícil de 
ser implementado do zero com as próprias ideias em apenas alguns dias, entretanto, 
adotamos praticamente a mesma ideia do cenário, instalações, itens, agentes e 
moedas.
O cenário de simulação em Agents in the City é real, que usa um sistema para 
obter um mapa realista com latitude e longitude, já em nosso cenário para simular a 
competição, consiste em uma matriz 20x20 que inicialmente é gerada com 0 (zero) 
em carácter e para simular a latitude e longitude, adotamos os valores de cada índice 
da matriz, onde latitude é o índice que se refere a linha e a longitude a coluna.
As instalações usadas na competição também foram utilizadas por nós, essas 
instalações estão dentro de um arrayList e eles são: Posto, Oficina, Mercado e Lixão. 
Cada instalação em nosso cenário é fixo com seu nome, latitude, longitude e tipo do 
local sem que haja um estabelecimento sobreposto a outro em nossa matriz. O posto 
é para fins do agente recarregar sua bateria caso ele não tenha armazenado o 
suficiente para estar andando em nosso cenário em prol de cumprir seu papel, à 
oficina é para fins de fazer a montagem dos itens coletados, o mercado ele pode tanto 
comprar itens quanto vender itens e o lixão para jogar determinado item fora.
Possuímos uma lista de arrays de itens, que constituem em 5 itens e esses 
itens possuem um nome, seu valor em moedas e seu peso. Diferente das instalações, 
os itens possuem uma localidade em nosso cenário de forma randômica, ou seja, a 
cada execução do programa, os itens estarão em diferentes localidades da nossa 
matriz.
Nosso projeto possui 4 agentes, esses agentes são: Carro, Moto, Caminhão e 
Drone. Cada agente possui seus respectivos nomes, à quantidade de bateria, o seu 
tipo e quantidade de armazenamento livre, ou seja, a capacidade que tem para 
armazenar os itens em sua “mala” em caso de carro, entretanto, cada agente tem sua 
variação para cada características, além disso, em nosso autômato desenvolvido, os 
agentes são definidos no início de nosso cenário, ou seja, em nossa matriz, os índices 
que representam linha (latitude) - 0 e coluna (longitude) – 0.
Em relação ao desenvolvimento do projeto, é possível afirmar que, o usuário 
vai informar as palavras que serão inseridas em nossa fita e com base nas ações que 
o nosso sistema possui iremos pedir ao usuário para nos informar o tipo de ação que 
ele deseja realizar caso seja possível mediante as suas entradas, além do mais, há 
funcionalidades que são executadas automaticamente, sem que haja interação com 
o usuário, a partir disto os agentes começam a andar pelo mapa a fim de realizar a 
sua ação em nosso cenário e caso esse agente ache um item, este item é capturado 
e armazenado, vale ressaltar que, cada agente possui um limite de capacidade de 
armazenamento e cada item possui seu devido volume. Achando um item o agente 
pode continuar procurando os demais itens até que seu armazenamento esteja cheio 
e/ou após ter capturado o item, o agente pode procurar um local de armazenamento 
dos itens para deixar os itens coletados lá guardados, vale destacar que a cada coleta 
de itens, o agente ganha uma pontuação.
O agente que está andando em nosso mapa tem as respectivas tarefas que são: 
checar o nível da sua bateria para ir à um determinado posto para abastecer de forma 
automática, verificar os itens, a fim de procurá-los, achando um item ele pode tá 
armazenando em um local de armazenagem. O agente pode ir a um mercado para 
comprar itens ou vender os itens disponíveis em sua “bagagem”, ir a uma oficina para 
montar itens a partir de outros itens e até mesmo ir a um lixão para descartar 
determinados itens que não deseja usar.
Nosso programa basicamente vai se encerrar quando o usuário dizer dentre 
as nossas opções que deseja sair, isto é, a cada interação do usuário, é feito uma 
leitura da palavra de entrada e das ações, após ter sido executado o pedido do 
usuário, novamente vai ser impresso o menu para ele inserir outra palavra e ações, 
assim como a finalização do programa.
## 3 AUTÔMATO
Com base no conhecimento e estudos que tivemos na matéria de Linguagens 
Formais e Autômatos, foi definido o tipo de autômato Autômato Finito Não 
Determinístico - AFND pela nossa equipe, ou seja, diferentemente do Autômato Finito 
Determinístico - AFD que não têm transações vazias, o AFND é uma 5-tupla ordenada
M = (Σ, Q, δ, q0, F) e é um autômato que apresentam transações vazia. Foi utilizado 
a ferramenta FSM Simulator para a criação do nosso autômato com os devidos 
conjuntos de estados e transições, a fim de simular palavras aceitas e rejeitadas 
perante o autômato criado por nossa equipe através da ferramenta.
Nosso alfabeto consiste em Σ = {a, b, c, d, e, f}, já nosso conjunto de estados 
possíveis do autômato é Q = {ocioso, verificaBateria, vaiPosto, abastece,
verificaItens, procuraItens, andaAleatoriamente, achoItem, armazenaItem, 
vaiMercado, compraItens, vendeItens, vaiOficina, montaItens, vaiLixao, 
jogarForaItem}, função programa ou transição do nosso autômato δ, o estado inicial 
do autômato Q0 = ocioso e o estado final F = {ocioso}.

![image](https://user-images.githubusercontent.com/44614612/195272257-d194a26a-0c6a-404e-9bf2-2e0335ce7c60.png)

