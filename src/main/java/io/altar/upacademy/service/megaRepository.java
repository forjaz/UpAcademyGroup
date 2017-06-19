package io.altar.upacademy.service;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
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

	public void initReceitas() {
		
		Receita receita1 = new Receita();
		receita1.setImagem("https://www.pingodoce.pt/wp-content/uploads/2015/12/1449085327");
		receita1.setNome("Esparguete com Frango do Campo em Molho de Tomate");
		receita1.setPreparacao("Separe os brócolos em raminhos, lave e escorra."+
								"Encha 1 tacho com água e leve ao lume. Quando a água ferver, tempere com sal e introduza os raminhos de brócolos. Deixe cozer destapados até estarem macios."+
				"Retire os brócolos com uma escumadeira e introduza na mesma água o esparguete. Deixe cozer até estar al-dente."+
				"Descasque a cebola e pique-a finamente. Esborrache os dentes de alho e pique-os. Lave o talo de aipo, tire-lhe os fios e corte em fatias finas. Deite o azeite num tacho,"+
				"leve ao lume e adicione os dentes de alho, depois a cebola picada, o aipo e a folha de louro. Deixe cozinhar sobre lume moderado, mexendo de vez em quando. "+
				"Limpe o tomate de pele e sementes, pique-o em pedaços e junte ao cozinhado. Deixe o tomate cozinhar até estar macio e regue com o vinho branco. "+
				"Tempere com sal e pimenta e deixe ferver um pouco até apurar. Adicione 4 a 5 folhas de manjericão fresco, retire do lume e triture com a varinha mágica. "+
				"Adicione o frango desfiado e leve de novo ao lume até levantar fervura."+
				"Escora o esparguete e sirva-o com o frango em molho de tomate, enfeitado com as azeitonas pretas e folhas frescas de manjericão. Acompanhe com os brócolos cozidos.");
		receita1.setCalorias(3356);
		receita1.setProteina(131.736);
		receita1.setHidratos(121.356);
		receita1.setGorduras(118.907);
		em.persist(receita1);
	
		Receita receita2 = new Receita();
		receita2.setImagem("https://www.google.pt/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0ahUKEwj5762t_rDUAhXLVRoKHZ0ADf4QjRwIBw&url=http%3A%2F%2Fwww.pittuka.com%2Fculinaria%2Fsopas-caldos-e-cremes%2Fcreme-de-tomate%2F&psig=AFQjCNGkpMQXAKjdR1YJmQl1GiOmce9fPw&ust=1497105384243358");
		receita2.setNome("Creme de Tomate");
		receita2.setPreparacao("Descasque a cebola e pique-a finamente, elimine os talos rijos do bolbo de funcho, reserve as folhinhas verdes e pique a parte restante. Pele a cenoura e corte-a em cubinhos pequenos."+
								"Deite o azeite e os legumes numa panela, tape e leve a cozinhar sobre lume muito brando."+
				"Entretanto, pele o tomate, corte-o ao meio e limpe-o de sementes. Pique o tomate em pedaços e junte-o aos restantes legumes. Salpique com cerca de 1 colher de sobremesa de sal, volte a tapar e deixe cozinhar durante cerca de 10 minutos, mexendo de vez em quando."+
				"Ao mesmo tempo coza o ovo em água temperada com sal durante 10 minutos."+
				"Adicione o açúcar e a água a ferver. Quando os legumes estiverem macios triture-os com a varinha mágica e retifique os temperos se necessário."+
				"Descasque o ovo e pique-o finamente."+
				"Deite a sopa nos pratos de serviço, salpique com o ovo picado e as folhinhas de funcho e o cebolinho.");
		receita2.setCalorias(3028);
		receita2.setProteina(45.7);
		receita2.setHidratos(130.8);
		receita2.setGorduras(70.636);		
		em.persist(receita2);
	
		
		Receita receita3 = new Receita();
		receita3.setImagem("http://www.vaqueiro.pt/upload/v4_0/receitas/6000/5163/oa0v2n3c.3vt_b9bdf24a_listing.jpeg");
		receita3.setNome("Borrego com Especiarias");
		receita3.setPreparacao("Limpe muito bem a pá de borrego de peles e gorduras. Junte o caril, a noz-moscada, a canela em pó, a paprica, a folha de louro cortada em pedaços pequenos e o vinagre e misture tudo muito bem. Tempere a carne com esta mistura."+
								"Pré-aqueça o forno a 170ºC. Lave as batatas, corte-as em gomos e coloque-as num tabuleiro de forno. Tempere com uma colher de sobremesa de sal, o tomilho, o mel e duas colheres de sopa de azeite. Misture bem. Leve ao forno durante cerca de 50 minutos ou até as batatas estarem tenras. Mexa-as de vez em quando."+
				"Ao mesmo tempo, frite os pedaços de borrego no restante azeite bem quente até a carne alourar. Reduza o lume e junte a cebola picada e as rodelas de alho-francês. Tape e deixe cozinhar sobre lume brando cerca de 40 minutos."+
				"Lave o tomate, limpe-o de sementes e pique-o em pedaços. Junte o tomate e a maçã reineta, previamente pelada e ralada, ao borrego e tempere com uma colher de chá de sal. Volte a tapar e cozinhe sobre lume muito brando mais 20 minutos ou até a carne estar tenra. Retire do lume, salpique com os coentros picados e sirva com as batatas assadas.");
		receita3.setCalorias(3310);
		receita3.setProteina(134.16);
		receita3.setHidratos(188.71);
		receita3.setGorduras(118.435);		
		em.persist(receita3);		

	}
	
	public String megaInit(){
		initReceitas();
		initIng1();
		initIng2();
		initIng3();
		initIng_R1();
		initIng_R2();
		initIng_R3();
		return "receitas";
	}
	
	public void initIng1() {
		Ingrediente ing1 = new Ingrediente();
		ing1.setNome("bróculos");
		ing1.setModoPreparacao("cozido");
		ing1.setCalorias(25);
		ing1.setGorduras(0.5);
		ing1.setHidratos(4.4);
		ing1.setProteina(3.2);
		em.persist(ing1);
		
		Ingrediente ing2 = new Ingrediente();
		ing2.setNome("esparguete");
		ing2.setModoPreparacao("cozido");
		ing2.setCalorias(101);
		ing2.setGorduras(0.6);
		ing2.setHidratos(19.9);
		ing2.setProteina(3.4);
		em.persist(ing2);
		
		Ingrediente ing3 = new Ingrediente();
		ing3.setNome("cebola");
		ing3.setModoPreparacao("frito");
		ing3.setCalorias(132);
		ing3.setGorduras(11.2);
		ing3.setHidratos(6.2);
		ing3.setProteina(1.9);
		em.persist(ing3);
		
		Ingrediente ing4 = new Ingrediente();
		ing4.setNome("alho");
		ing4.setModoPreparacao("frito");
		ing4.setCalorias(67);
		ing4.setGorduras(0.6);
		ing4.setHidratos(11.3);
		ing4.setProteina(3.8);
		em.persist(ing4);
		
		Ingrediente ing5 = new Ingrediente();
		ing5.setNome("aipo");
		ing5.setModoPreparacao("frito");
		ing5.setCalorias(11);
		ing5.setGorduras(0.1);
		ing5.setHidratos(1.5);
		ing5.setProteina(1.1);
		em.persist(ing5);
		
		Ingrediente ing6 = new Ingrediente();
		ing6.setNome("azeite");
		ing6.setCalorias(900);
		ing6.setGorduras(99.9);
		ing6.setHidratos(0);
		ing6.setProteina(0);
		em.persist(ing6);
		
		Ingrediente ing7 = new Ingrediente();
		ing7.setNome("tomate");
		ing7.setModoPreparacao("frito");
		ing7.setCalorias(309);
		ing7.setGorduras(3.3);
		ing7.setHidratos(9.0);
		ing7.setProteina(1.4);
		em.persist(ing7);
		
		Ingrediente ing8 = new Ingrediente();
		ing8.setNome("vinho branco");
		ing8.setCalorias(302);
		ing8.setGorduras(0);
		ing8.setHidratos(1.2);
		ing8.setProteina(0.1);
		em.persist(ing8);
		
		Ingrediente ing9 = new Ingrediente();
		ing9.setNome("frango");
		ing9.setModoPreparacao("cozido");
		ing9.setCalorias(204);
		ing9.setGorduras(11.1);
		ing9.setHidratos(0);
		ing9.setProteina(26.1);
		em.persist(ing9);
		
		Ingrediente ing10 = new Ingrediente();
		ing10.setNome("azeitonas pretas");
		ing10.setCalorias(137);
		ing10.setGorduras(14.2);
		ing10.setHidratos(4.1);
		ing10.setProteina(0.9);
		em.persist(ing10);
	}
	
	public void initIng2() {
		Ingrediente ing1 = new Ingrediente();
		ing1.setNome("funcho Bolbo");
		ing1.setModoPreparacao("frito");
		ing1.setCalorias(40);
		ing1.setGorduras(0);
		ing1.setHidratos(7);
		ing1.setProteina(1);
		em.persist(ing1);
		
		Ingrediente ing2 = new Ingrediente();
		ing2.setNome("cenoura");
		ing2.setModoPreparacao("frita");
		ing2.setCalorias(60);
		ing2.setGorduras(4.79);
		ing2.setHidratos(8);
		ing2.setProteina(0.74);
		em.persist(ing2);
		
		Ingrediente ing3 = new Ingrediente();
		ing3.setNome("ovos de galinha");
		ing3.setModoPreparacao("cozido");
		ing3.setCalorias(149);
		ing3.setGorduras(10.8);
		ing3.setHidratos(0);
		ing3.setProteina(13);
		em.persist(ing3);
		
		Ingrediente ing4 = new Ingrediente();
		ing4.setNome("cebolinho");
		ing4.setModoPreparacao("cru");
		ing4.setCalorias(0);
		ing4.setGorduras(0);
		ing4.setHidratos(0);
		ing4.setProteina(0);
		em.persist(ing4);
		
		Ingrediente ing5 = new Ingrediente();
		ing5.setNome("açucar");
		ing5.setCalorias(387);
		ing5.setGorduras(0);
		ing5.setHidratos(100);
		ing5.setProteina(0);
		em.persist(ing5);
		
	}
	
	public void initIng3() {
		Ingrediente ing1 = new Ingrediente();
		ing1.setNome("borrgo (pá)");
		ing1.setModoPreparacao("frito");
		ing1.setCalorias(166);
		ing1.setGorduras(7.6);
		ing1.setHidratos(0);
		ing1.setProteina(24.5);
		em.persist(ing1);
		
		Ingrediente ing2 = new Ingrediente();
		ing2.setNome("vinagre de sidra");
		ing2.setCalorias(210);
		ing2.setGorduras(0);
		ing2.setHidratos(9.5);
		ing2.setProteina(0);
		em.persist(ing2);
		
		Ingrediente ing3 = new Ingrediente();
		ing3.setNome("batata");
		ing3.setModoPreparacao("assado");
		ing3.setCalorias(152);
		ing3.setGorduras(4.8);
		ing3.setHidratos(23.7);
		ing3.setProteina(3);
		em.persist(ing3);
		
		Ingrediente ing4 = new Ingrediente();
		ing4.setNome("alho francês");
		ing4.setCalorias(0);
		ing4.setGorduras(0.0);
		ing4.setHidratos(0);
		ing4.setProteina(0);
		em.persist(ing4);
		
		Ingrediente ing5 = new Ingrediente();
		ing5.setNome("maçã reineta");
		ing5.setModoPreparacao("frito");
		ing5.setCalorias(66);
		ing5.setGorduras(0.5);
		ing5.setHidratos(15.9);
		ing5.setProteina(0.3);
		em.persist(ing5);
		
		Ingrediente ing6 = new Ingrediente();
		ing6.setNome("coentros");
		ing6.setCalorias(66);
		ing6.setGorduras(0.5);
		ing6.setHidratos(15.9);
		ing6.setProteina(0.3);
		em.persist(ing6);
	}
	
	public void initIng_R1() {
		Receita_Ingrediente ir1 = new Receita_Ingrediente();
		ir1.setReceita(em.find(Receita.class, 1L));
		ir1.setIngrediente(em.find(Ingrediente.class, 1L));
		ir1.setQuantidade(200);
		em.persist(ir1);
		
		Receita_Ingrediente ir2 = new Receita_Ingrediente();
		ir2.setReceita(em.find(Receita.class, 1L));
		ir2.setIngrediente(em.find(Ingrediente.class, 2L));
		ir2.setQuantidade(200);
		em.persist(ir2);
		
		Receita_Ingrediente ir3 = new Receita_Ingrediente();
		ir3.setReceita(em.find(Receita.class, 1L));
		ir3.setIngrediente(em.find(Ingrediente.class, 3L));
		ir3.setQuantidade(200);
		em.persist(ir3);
		
		Receita_Ingrediente ir4 = new Receita_Ingrediente();
		ir4.setReceita(em.find(Receita.class, 1L));
		ir4.setIngrediente(em.find(Ingrediente.class, 4L));
		ir4.setQuantidade(200);
		em.persist(ir4);
		
		Receita_Ingrediente ir5 = new Receita_Ingrediente();
		ir5.setReceita(em.find(Receita.class, 1L));
		ir5.setIngrediente(em.find(Ingrediente.class, 5L));
		ir5.setQuantidade(200);
		em.persist(ir5);
		
		Receita_Ingrediente ir6 = new Receita_Ingrediente();
		ir6.setReceita(em.find(Receita.class, 1L));
		ir6.setIngrediente(em.find(Ingrediente.class, 6L));
		ir6.setQuantidade(200);
		em.persist(ir6);
		
		Receita_Ingrediente ir7 = new Receita_Ingrediente();
		ir7.setReceita(em.find(Receita.class, 1L));
		ir7.setIngrediente(em.find(Ingrediente.class, 7L));
		ir7.setQuantidade(200);
		em.persist(ir7);
		
		Receita_Ingrediente ir8 = new Receita_Ingrediente();
		ir8.setReceita(em.find(Receita.class, 1L));
		ir8.setIngrediente(em.find(Ingrediente.class, 8L));
		ir8.setQuantidade(200);
		em.persist(ir8);
		
		Receita_Ingrediente ir9 = new Receita_Ingrediente();
		ir9.setReceita(em.find(Receita.class, 1L));
		ir9.setIngrediente(em.find(Ingrediente.class, 9L));
		ir9.setQuantidade(200);
		em.persist(ir9);
		
		Receita_Ingrediente ir10 = new Receita_Ingrediente();
		ir10.setReceita(em.find(Receita.class, 1L));
		ir10.setIngrediente(em.find(Ingrediente.class, 10L));
		ir10.setQuantidade(200);
		em.persist(ir10);
	}
	
	public void initIng_R2() {
		Receita_Ingrediente ir1 = new Receita_Ingrediente();//bolbo
		ir1.setReceita(em.find(Receita.class, 2L));
		ir1.setIngrediente(em.find(Ingrediente.class, 11L));
		ir1.setQuantidade(200);
		em.persist(ir1);
		
		Receita_Ingrediente ir2 = new Receita_Ingrediente();//cenoura
		ir2.setReceita(em.find(Receita.class, 2L));
		ir2.setIngrediente(em.find(Ingrediente.class, 12L));
		ir2.setQuantidade(200);
		em.persist(ir2);
		
		Receita_Ingrediente ir3 = new Receita_Ingrediente();//ovos
		ir3.setReceita(em.find(Receita.class, 2L));
		ir3.setIngrediente(em.find(Ingrediente.class, 13L));
		ir3.setQuantidade(200);
		em.persist(ir3);
		
		Receita_Ingrediente ir4 = new Receita_Ingrediente();//cebolinho
		ir4.setReceita(em.find(Receita.class, 2L));
		ir4.setIngrediente(em.find(Ingrediente.class, 14L));
		ir4.setQuantidade(200);
		em.persist(ir4);
		
		Receita_Ingrediente ir5 = new Receita_Ingrediente();//acucar
		ir5.setReceita(em.find(Receita.class, 2L));
		ir5.setIngrediente(em.find(Ingrediente.class, 15L));
		ir5.setQuantidade(200);
		em.persist(ir5);
		
		Receita_Ingrediente ir6 = new Receita_Ingrediente();//cebola
		ir6.setReceita(em.find(Receita.class, 2L));
		ir6.setIngrediente(em.find(Ingrediente.class, 3L));
		ir6.setQuantidade(200);
		em.persist(ir6);
		
		Receita_Ingrediente ir7 = new Receita_Ingrediente();//azeite
		ir7.setReceita(em.find(Receita.class, 2L));
		ir7.setIngrediente(em.find(Ingrediente.class, 6L));
		ir7.setQuantidade(200);
		em.persist(ir7);
	
		Receita_Ingrediente ir8 = new Receita_Ingrediente();//tomates fritos
		ir8.setReceita(em.find(Receita.class, 2L));
		ir8.setIngrediente(em.find(Ingrediente.class, 7L));
		ir8.setQuantidade(200);
		em.persist(ir8);
				
	}
	
	public void initIng_R3() {
		Receita_Ingrediente ir1 = new Receita_Ingrediente(); //borrego pa
		ir1.setReceita(em.find(Receita.class, 3L));
		ir1.setIngrediente(em.find(Ingrediente.class, 16L));
		ir1.setQuantidade(200);
		em.persist(ir1);
		
		Receita_Ingrediente ir2 = new Receita_Ingrediente(); //vinagre sidra
		ir2.setReceita(em.find(Receita.class, 3L));
		ir2.setIngrediente(em.find(Ingrediente.class, 17L));
		ir2.setQuantidade(200);
		em.persist(ir2);
		
		Receita_Ingrediente ir3 = new Receita_Ingrediente(); //batata assada
		ir3.setReceita(em.find(Receita.class, 3L));
		ir3.setIngrediente(em.find(Ingrediente.class, 18L));
		ir3.setQuantidade(200);
		em.persist(ir3);
		
		Receita_Ingrediente ir4 = new Receita_Ingrediente(); //alho fances
		ir4.setReceita(em.find(Receita.class, 3L));
		ir4.setIngrediente(em.find(Ingrediente.class, 19L));
		ir4.setQuantidade(200);
		em.persist(ir4);
		
		Receita_Ingrediente ir5 = new Receita_Ingrediente(); //maca reinata
		ir5.setReceita(em.find(Receita.class, 3L));
		ir5.setIngrediente(em.find(Ingrediente.class, 20L));
		ir5.setQuantidade(200);
		em.persist(ir5);
		
		Receita_Ingrediente ir6 = new Receita_Ingrediente(); //coentro
		ir6.setReceita(em.find(Receita.class, 3L));
		ir6.setIngrediente(em.find(Ingrediente.class, 21L));
		ir6.setQuantidade(200);
		em.persist(ir6);
	}
}
