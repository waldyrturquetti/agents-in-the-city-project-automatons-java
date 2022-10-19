## 1 INTRODUÇÃO
Esse projeto tem como base um tema específico em relação à Agents in 
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
O cenário continha 4 tipos de agentes distintas: carros, drones, motocicletas 
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

![image](https://user-images.githubusercontent.com/44614612/196587848-dd645b3e-89f0-4aee-af2f-3542a360cc78.png)

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
Nosso projeto possui 4 (quatro) agentes, esses agentes são: Carro, Moto, 
Caminhão e Drone. Cada agente possui seus respectivos nomes, à quantidade de 
bateria, o seu tipo e quantidade de armazenamento livre (armazenaItemNaBolsa), ou 
seja, a capacidade que tem para armazenar os itens em sua “mala” em caso de carro, 
entretanto, cada agente tem sua variação para cada características, além disso, em 
nosso autômato desenvolvido, os agentes são definidos no início de nosso cenário, 
ou seja, em nossa matriz, os índices que representam linha (latitude) - 0 e coluna 
(longitude) – 0.
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

## 2.1 FUNCIONALIDADES NOVAS
Foi conversado perante a equipe a respeito do trabalho 2 (dois), onde tínhamos 
propostas para prosseguir com o mesmo trabalho, porém adicionando mais 5 (cinco) 
novas funcionalidades, assim como transformar o autômato em um autômato de pilha
ou escolher um tema específico para fazer um seminário de pesquisa. E, perante as 
alternativas foi discutido e determinado a escolha de prosseguir com o autômato já 
desenvolvido e implementando novas funcionalidades.
As novas funcionalidades adicionadas foram a inserção de construção de 
poços, armazenar itens em um estoque, transportar mercadoria, recuperar item, 
comprar item e desmontar itens.
• Construção de poços
De acordo com o tema da competição e que era proposto para ser realizada pelas 
equipes, a construção de poços foi inserida em nosso código, ou seja, 
implementamos essa funcionalidade quando determinado agente possuir um certo 
montante de moedas (massium), como era algo que aparecia de uma forma aleatória 
no mapa, nós implementamos em alguns lugares fixos, porém, é necessário que a 
equipe tenha ‘x’ moedas para ser realizada tal tarefa.
• Armazenar Itens
Implementamos de fato essa funcionalidade para que o nosso autômato consiga 
ter um local de armazenamento (estoque), pois cada agente tem certa capacidade de 
armazenamento no próprio veículo e como a competição requer capturar 
determinados itens para conseguir alcançar outros objetivos, foi dada a necessidade 
de um local de estoque para que tal agente obtenha outros itens, assim podendo 
realizar outras operações quando retornar ao estoque e ter o suficiente de itens para 
ter êxito ao trocar por outro itens, vender até mesmo os itens para conseguir moedas.
• Transportar Mercadorias
Foi inserido nesse novo autômato afim da equipe realizar outro tipo de trabalho 
para ganhar moedas. Em nosso autômato foi inserido 5 transações para executar esta 
tarefa, essas novas transações são: realizaContrato, vaiLocalCarga, pegaCarga, 
vaiLocalDescarga e ocorreDescarga.
Quando o agente chegar na transição de realizaContrato, vai ser perguntado qual 
tipo de contrato ele que realizar, este contrato se refere a um transporte de uma 
mercadoria de nome qualquer e cada contrato que se refere a um tipo de mercadoria 
tem suas respectivas recompensas e assim que o agente, neste caso o usuário 
escolher o tipo de contrato, o nosso agente vai percorrer nossa matriz 20x20, onde 
fizemos a simulação de uma mapa e usando linha e coluna como latitude e longitude, 
vai percorrer até o determinado local, assim até realizar a descarga dessa mercadoria.
Feito a realização da mercadoria, a equipe ou melhor dizendo, nosso agente, vai 
ganhar uma recompensa, assim colocando-o em vantagem para comprar novos itens 
e ficando disponível para efetuar novas missões.
• Recuperar Item
Após algumas reuniões e análise das funcionalidades que a competição tinha, foi 
visto a necessidade de inserir a capacidade do agente (autômato) de recuperar tal 
item, pois as vezes ao efetuar certa funcionalidades é possível ter feito ela de forma 
inadequada, ou seja, quando o agente for no lixão e descartar determinado item, mas 
esse item foi descartado de forma equivocada, o nosso agente vai ter um estado do 
lixão para o de recuperar item, em outros termos, o autômato vai ter a opção de 
resgatar o item jogado fora de forma errada.
• Comprar Item
Como existia a funcionalidade de comprar item ao ir no mercado e não foi inserido 
a tempo no trabalho 1, mas falado dessa funcionalidade, agora, no trabalho 2 essa 
função foi inserida, então quando nosso agente ir a um mercado, ele vai poder tanto 
comprar um item quanto vender itens para conseguir moedas.
• Desmontar Itens
Outra funcionalidade que foi discutido por não ter essa opção na competição 
Agentes in The City, 2018, foi a respeito de desmontar itens. De acordo com a 
competição, era possível determinados agentes (carro, motocicleta, drone e 
caminhão) irem a oficina para montar itens, ou seja, cada agente levava consigo uma 
certa quantia de itens para serem montados e após ele ser montado o agente poderia 
vender para ganhar moedas (massium) e até mesmo trocar por outro item, lembrando 
que, alguns itens era necessário ter para conseguir fazer determinadas missões que 
eram geradas aleatoriamente na competição, então resolvemos adicionar uma opção 
para que fosse possível o agente desmontar o item, visto que, ele poderia ter montado 
o item errado ou simplesmente desistiu da montagem, visando montar outro item.

## 3 AUTÔMATO
Com base no conhecimento e estudos que tivemos na matéria de Linguagens 
Formais e Autômatos, foi definido o tipo de autômato Autômato Finito Não 
Determinístico - AFND pela nossa equipe, ou seja, diferentemente do Autômato Finito 
Determinístico - AFD que não têm transações vazias, o AFND é uma 5-tupla ordenada 
M = (Σ, Q, δ, q0, F) e é um autômato que apresentam transações vazia. Foi utilizado 
a ferramenta FSM Simulator para a criação do nosso autômato com os devidos 
conjuntos de estados e transições, a fim de simular palavras aceitas e rejeitadas 
perante o autômato criado por nossa equipe através da ferramenta.
Nosso alfabeto consiste em Σ = {a, b, c, d, e, f, g, h, i, j}, já nosso conjunto de 
estados possíveis do autômato é Q = {ocioso, verificaBateria, vaiPosto, abastece, 
verificaItens, vaiArmazem, armazenaItem, desarmazenar, vaiLixao, jogarForaItem, 
recuperaItem, vaiOficina, montaItens, desmontar, ProcuraItens, andaAleatoriamente, 
achoItem, armazenaItemNaBolsa, vaiPoço, depositaMoeda, compraItens, 
vaiMercado, vendeItens, realizaContrato, vaiLocalCarga, pegaCarga, 
vaiLocalDescarga, ocorreDescarga}, função programa ou transição do nosso 
autômato δ, o estado inicial do autômato Q0 = ocioso e o estado final F = {ocioso}.

![image](https://user-images.githubusercontent.com/44614612/195272809-9e2c380f-24ef-424e-839a-653e07411bfb.png)
