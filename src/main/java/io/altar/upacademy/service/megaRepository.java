package io.altar.upacademy.service;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.altar.upacademy.model.Ingrediente;
import io.altar.upacademy.model.Receita;
import io.altar.upacademy.model.Receita_Ingrediente;

@Named("megaBean")
@ApplicationScoped
@Transactional

public class megaRepository extends EntityService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String procura;
	private String procura2;
	private String procura3;

	public String getProcura() {
		return procura;
	}

	public void setProcura(String procura1) {
		this.procura = procura1;
	}

	public String getProcura2() {
		return procura2;
	}

	public void setProcura2(String procura) {
		this.procura2 = procura;
	}

	public String getProcura3() {
		return procura3;
	}

	public void setProcura3(String procura) {
		this.procura3 = procura;
	}


	public void initRct() {
		
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (1,' ','https://s1.postimg.org/hrazrqni7/transparent.png',' ',0,0,0,0,' ',0);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (2,'Esparguete com Frango do Campo em Molho de Tomate','https://www.pingodoce.pt/wp-content/uploads/2015/12/1449085327','Separe os brócolos em raminhos, lave e escorra.Encha 1 tacho com água e leve ao lume. Quando a água ferver, tempere com sal e introduza os raminhos de brócolos. Deixe cozer destapados até estarem macios.Retire os brócolos com uma escumadeira e introduza na mesma água o esparguete. Deixe cozer até estar al-dente.Descasque a cebola e pique-a finamente. Esborrache os dentes de alho e pique-os. Lave o talo de aipo, tire-lhe os fios e corte em fatias finas. Deite o azeite num tacho,leve ao lume e adicione os dentes de alho, depois a cebola picada, o aipo e a folha de louro. Deixe cozinhar sobre lume moderado, mexendo de vez em quando. Limpe o tomate de pele e sementes, pique-o em pedaços e junte ao cozinhado. Deixe o tomate cozinhar até estar macio e regue com o vinho branco. Tempere com sal e pimenta e deixe ferver um pouco até apurar. Adicione 4 a 5 folhas de manjericão fresco, retire do lume e triture com a varinha mágica. Adicione o frango desfiado e leve de novo ao lume até levantar fervura.Escora o esparguete e sirva-o com o frango em molho de tomate, enfeitado com as azeitonas pretas e folhas frescas de manjericão. Acompanhe com os brócolos cozidos.',531,17,19,22,'Geral',6);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (3,'Creme de Tomate','http://www.pittuka.com/wp-content/uploads/2014/05/creme-de-tomate-300x180.jpg','Descasque a cebola e pique-a finamente, elimine os talos rijos do bolbo de funcho, reserve as folhinhas verdes e pique a parte restante. Pele a cenoura e corte-a em cubinhos pequenos.Deite o azeite e os legumes numa panela, tape e leve a cozinhar sobre lume muito brando.Entretanto, pele o tomate, corte-o ao meio e limpe-o de sementes. Pique o tomate em pedaços e junte-o aos restantes legumes. Salpique com cerca de 1 colher de sobremesa de sal, volte a tapar e deixe cozinhar durante cerca de 10 minutos, mexendo de vez em quando.Ao mesmo tempo coza o ovo em água temperada com sal durante 10 minutos.Adicione o açúcar e a água a ferver. Quando os legumes estiverem macios triture-os com a varinha mágica e retifique os temperos se necessário.Descasque o ovo e pique-o finamente.Deite a sopa nos pratos de serviço, salpique com o ovo picado e as folhinhas de funcho e o cebolinho.',571,11,25,9,'Geral',5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (4,'Borrego com Especiarias','http://www.vaqueiro.pt/upload/v4_0/receitas/6000/5163/oa0v2n3c.3vt_b9bdf24a_listing.jpeg','Limpe muito bem a pá de borrego de peles e gorduras. Junte o caril, a noz-moscada, a canela em pó, a paprica, a folha de louro cortada em pedaços pequenos e o vinagre e misture tudo muito bem. Tempere a carne com esta mistura.Pré-aqueça o forno a 170ºC. Lave as batatas, corte-as em gomos e coloque-as num tabuleiro de forno. Tempere com uma colher de sobremesa de sal, o tomilho, o mel e duas colheres de sopa de azeite. Misture bem. Leve ao forno durante cerca de 50 minutos ou até as batatas estarem tenras. Mexa-as de vez em quando.Ao mesmo tempo, frite os pedaços de borrego no restante azeite bem quente até a carne alourar. Reduza o lume e junte a cebola picada e as rodelas de alho-francês. Tape e deixe cozinhar sobre lume brando cerca de 40 minutos.Lave o tomate, limpe-o de sementes e pique-o em pedaços. Junte o tomate e a maçã reineta, previamente pelada e ralada, ao borrego e tempere com uma colher de chá de sal. Volte a tapar e cozinhe sobre lume muito brando mais 20 minutos ou até a carne estar tenra. Retire do lume, salpique com os coentros picados e sirva com as batatas assadas.',533,18,31,22,'Geral',6);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (5,'Strogonoff de Frango','http://img.itdg.com.br/tdg/images/recipes/000/002/462/80094/80094_original.jpg?mode=crop&width=370&height=278','Em uma panela, misture o frango, o alho, a maionese, o sal e a pimenta. Em uma frigideira grande, derreta a manteiga e doure a cebola. Em uma frigideira grande, derreta a manteiga e doure a cebola. Adicione os cogumelos, o ketchup e a mostarda. Incorpore as natas e retire do fogo antes de ferver. Sirva com arroz branco e batata palha.',489,30,22,31,'Geral',4);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (6,'Arroz de Frango com Grão e Pimento','http://www.1001receitasfaceis.net/wp-content/uploads/2017/03/arrozdefrangocomgraoepimento617-e1489698566350.jpg','Corte os bifes de frango em tiras, tempere-as com o sumo do limão, uma colher de chá de sal e um pouco de pimenta. Ferva a água. Deite o azeite num tacho, junte a cebola picada, os pimentos em cubos pequenos, os cogumelos, o arroz, as tiras de frango e o grão bem escorrido. Mexa, junte a água a ferver e o restante sal. Envolva tudo de novo. Tape e leve a lume brando cerca de 30 minutos. Decore com folhas de tomilho e sirva.',218,8,18,17,'Geral',6);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (7,'Feijoada de frango','http://www.1001receitasfaceis.net/wp-content/uploads/2016/03/12715261_995478440511204_3702347544803952411_n.jpg','Demolhe o feijão encarnado de véspera. No dia seguinte, coza-o em água com um fio de azeite, cerca de 40 minutos, numa panela de pressão. Entretanto, corte o frango em pedaços e tempere-os com sal e a massa de pimentão. Descasque e pique a cebola e os dentes de alho; corte o alho francês em rodelas e refogue-os no azeite com a folha de louro. Adicione o frango e deixe corar. Limpe o tomate de peles e grainhas, pique-o e junte-o ao preparado anterior. Refresque com o vinho branco, junte o chouriço, cortado em rodelas, e tempere com sal, pimenta e um pouco de cominhos. Acrescente o feijão encarnado e deixe ferver. Retifique os temperos. Retire, polvilhe com coentros picados e sirva, logo de seguida.',592,24,17,45,'Geral',7);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (8,'Bacalhau desfiado com ovos mexidos','http://s2.glbimg.com/sNb4mjiMkaKINol1R2LrLa7qUpQ=/g.glbimg.com/og/gs/gsat5/f/original/2016/05/11/bacalhaumexido.jpg','Cozinhe o bacalhau dessalgado em azeite no fogo médio. Corte a cebola, o bacon, a copa e refogue tudo. Pique os dois tipos de azeitona e junte ao refogado. Desfie o bacalhau e junte à mistura. Faça um ovo mexido com os ovos e acrescente à mistura. Corte os aspargos em pequenas fatias e puxe levemente na manteiga para que ainda continuem crocantes. Faça um suco de maçã e reduza um pouco no fogo médio para colocar no fundo da colher. Rale a batata e frite. Monte o mexido de bacalhau em cima do suco de maçã, e finalize com a batata frita.',362,20,17,31,'Geral',5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (9,'Mousse de Chocolate','https://i.ytimg.com/vi/vkhgD5LCe7s/hqdefault.jpg','Bata as gemas até dobrarem de volume. Junte o açúcar e continue batendo. Derreta o chocolate e acrescente à gemada. Junte o creme de leite, batendo sempre. Por fim, acrescente as claras em neve (bem batidas mesmo), misturando rapidamente na batedeira. Leve à geladeira e sirva-se, deliciando o melhor mousse e mais simples de fazer.',259,11,34,6,'Sobremesa',10);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (10,'Doce da Avó','https://i2.wp.com/receitasavolili.com/wp-content/uploads/2013/05/Doce%20da%20Av%201.jpg?w=600','Comece por bater bem as natas frescas até ganharem uma consistência mais firme. Adicione o leite condensado e continue a bater mais um pouco. À parte, amoleça a gelatina num pouco de água e leve-a ao lume muito brando (ou mesmo em banho maria) mexendo sempre até derreter bem. Junte a gelatina ao preparado das natas e leite condensado. De seguida, bata as claras em castelo e envolva-as no preparado do doce de natas. Disponha o Doce da Avó numa taça grande ou pequenas taças individuais, alternando as camadas. Primeiro coloque o creme, de seguida os palitos esfarelados, e assim sucessivamente, acabando com uma camada de palitos (ver fotografia).',377,21,39,9,'Sobremesa',10);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita` (`id`,`nome`,`imagem`,`preparacao`,`calorias`,`gorduras`,`hidratos`,`proteina`,`tipo`,`nPessoas`) VALUES (11,'Fusilli com Salmão Fumado','http://www.1001receitasfaceis.net/wp-content/uploads/2017/02/1086x562_fusilli_salmao_fumado-1-e1487164470101.jpg','Coza a massa com um fio de azeite e sal. Escorra e reserve. Coloque a Manteiga Mimosa sem sal e as curgetes num tacho com duas colheres de sopa de água e deixe estufar as curgetes. Junte o Molho de Nata Mimosa para Bacalhau e Outros Peixes e o salmão cortado em tiras. Deixe levantar fervura, misture a massa e junte a salsa picada. Sirva com salada.',696,20,90,34,'Geral',3);").executeUpdate();
	}
		
	public String megaInit(){
		initRct();
		initIng();
		initIng_R();
		return "receitas";
	}
	
	public void initIng() {
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (1,25,0.5,4.4,'cozido','bróculos',3.2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (2,101,0.6,19.9,'cozido','esparguete',3.4);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (3,17,0.2,3.1,'cru','cebola',0.9);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (4,67,0.6,11.3,'frito','alho',3.8);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (5,11,0.1,1.5,'frito','aipo',1.1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (6,900,99.9,0,'cru','azeite',0);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (7,230,1.4,9,'frito','tomate',3.3);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (8,302,0,1.2,'cru','vinho branco',0.1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (9,204,11.1,0,'cozido','frango',26.1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (10,137,14.2,4.1,'cru','azeitonas pretas',0.9);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (11,40,0,7,'frito','funcho',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (12,60,4.79,8,'frito','cenoura',0.74);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (13,149,10.8,0,'cozido','ovo de galinha',13);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (14,0,0,0,'cru','cebolinho',0);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (15,387,0,100,'cru','açucar',0);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (16,166,7.6,0,'frito','borrego (pá)',24.5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (17,210,0,9.5,'cru','vinagre de sidra',0);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (18,152,4.8,23.7,'assado','batata',3);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (19,70,0.3,2.9,'frito','alho francês',1.8);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (20,66,0.5,15.9,'frito','maçã',0.3);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (21,44,0.6,1.8,'frito','coentros',2.4);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (22,26,0.3,1.9,'cru','limão',0.5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (23,122,3.2,0,'frito','frango',21.6);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (24,270,27,6.2,'cru','maionese',0.7);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (25,739,81.8,0.7,'cru','manteiga',0.1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (26,85,0.1,19,'cru','ketchup',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (27,270,25,9,'cru','mostarda',1.5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (28,12,0.4,0,'cru','cogumelos',2.1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (29,306,32,2.7,'cru','natas',1.9);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (30,556,36,51.2,'frito','batata palha',6.4);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (31,127,0.2,28,'cozido','arroz',2.5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (32,22,0.6,2.7,'cru','pimento',1.6);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (33,121,2.1,16.7,'cozido','grão',8.4);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (34,0,0,0,'cru','água',0);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (35,94,0.6,14,'cozido','feijão',7.8);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (36,408,34.5,0,'cru','chouriço',24.5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (37,21,0.5,2.5,'cru','massa de pimentão',0.9);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (38,128,4,1.7,'assado','frango inteiro',21.2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (39,80,0.4,0,'cru','bacalhau',19);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (40,367,34.6,0,'cru','bacon',13.8);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (41,18,0,2.7,'cru','espargos',2.1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (42,89,0,19.2,'cru','batata',2.5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (43,14,0,0.4,'cru','salsa',3.1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (44,57,0.5,13.4,'cru','maçã com casca',0.2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (45,469,30.5,44,'cru','chocolate de culinária',5.4);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (46,334,9,56.4,'cru','leite condensado',7.8);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (47,359,7.2,66.6,'cru','palitos La Reine',11.5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (48,349,0.1,0,'cru','folha de gelatina',87);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (49,47,0.3,0,'cru','claras de ovo',11);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (50,233,21,7,'cru','natas para peixe',2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (51,358,1.5,72,'cru','fusilli tricolor',12);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (52,117,4.32,0,'cru','salmão fumado',18.28);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Ingrediente` (`id`,`calorias`,`gorduras`,`hidratos`,`modoPreparacao`,`nome`,`proteina`) VALUES (53,16,0.18,3.35,'cru','courgette',1.21);").executeUpdate();
	}
	
	public void initIng_R() {
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (1,300,1,2,'g',300);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (2,200,2,2,'g',200);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (3,150,3,2,'g',150);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (4,12,4,2,'uni',4);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (5,30,5,2,'uni',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (6,45,6,2,'csp',3);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (7,600,7,2,'uni',600);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (8,100,8,2,'ml',100);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (9,350,9,2,'g',350);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (10,50,10,2,'g',50);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (11,150,3,3,'uni',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (12,300,11,3,'uni',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (13,100,12,3,'uni',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (14,30,6,3,'csp',2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (15,1000,7,3,'kg',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (16,47,13,3,'uni',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (17,2.5,15,3,'chá',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (18,15,14,3,'csp',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (19,400,16,4,'g',400);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (20,30,17,4,'csp',2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (21,500,18,4,'uni',4);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (22,45,6,4,'csp',3);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (23,100,3,4,'g',100);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (24,100,19,4,'g',100);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (25,500,7,4,'uni',6);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (26,80,20,4,'uni',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (27,30,21,4,'csp',2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (28,500,23,5,'g',500);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (29,30,24,5,'csp',2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (30,15,25,5,'csp',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (31,100,26,5,'cp',0.5);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (32,66.6,27,5,'cp',0.333);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (33,200,28,5,'cp',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (34,200,29,5,'cp',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (35,200,31,5,'cp',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (36,50,22,6,'g',50);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (37,400,34,6,'ml',400);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (38,30,6,6,'csp',2);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (39,150,3,6,'uni',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (40,300,32,6,'g',300);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (41,150,28,6,'g',150);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (42,200,31,6,'g',200);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (43,250,33,6,'g',250);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (44,300,23,6,'g',300);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (45,500,35,7,'g',500);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (46,1000,38,7,'g',1000);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (47,15,37,7,'csp',1);").executeUpdate();
		em.createNativeQuery("INSERT INTO `Receita_Ingrediente` (`id`,`Quantidade`,`ingrediente_id`,`receita_id`,`medidas`,`quantidadeCliente`) VALUES (48,150,3,7,'g',150);").executeUpdate();
	}

}
