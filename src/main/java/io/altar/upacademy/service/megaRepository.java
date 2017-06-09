package io.altar.upacademy.service;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

import io.altar.upacademy.model.Receita;



@Named("megaBean")
@ApplicationScoped
@Transactional
public class megaRepository extends EntityService implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	public void InitReceitas() {
		
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
				
		em.persist(receita2);
	
		
		Receita receita3 = new Receita();
		receita3.setImagem("http://www.vaqueiro.pt/upload/v4_0/receitas/6000/5163/oa0v2n3c.3vt_b9bdf24a_listing.jpeg");
		receita3.setNome("Borrego com Especiarias");
		receita3.setPreparacao("Limpe muito bem a pá de borrego de peles e gorduras. Junte o caril, a noz-moscada, a canela em pó, a paprica, a folha de louro cortada em pedaços pequenos e o vinagre e misture tudo muito bem. Tempere a carne com esta mistura."+
								"Pré-aqueça o forno a 170ºC. Lave as batatas, corte-as em gomos e coloque-as num tabuleiro de forno. Tempere com uma colher de sobremesa de sal, o tomilho, o mel e duas colheres de sopa de azeite. Misture bem. Leve ao forno durante cerca de 50 minutos ou até as batatas estarem tenras. Mexa-as de vez em quando."+
				"Ao mesmo tempo, frite os pedaços de borrego no restante azeite bem quente até a carne alourar. Reduza o lume e junte a cebola picada e as rodelas de alho-francês. Tape e deixe cozinhar sobre lume brando cerca de 40 minutos."+
				"Lave o tomate, limpe-o de sementes e pique-o em pedaços. Junte o tomate e a maçã reineta, previamente pelada e ralada, ao borrego e tempere com uma colher de chá de sal. Volte a tapar e cozinhe sobre lume muito brando mais 20 minutos ou até a carne estar tenra. Retire do lume, salpique com os coentros picados e sirva com as batatas assadas.");
				
		em.persist(receita3);

	}
	
	public String megaInit(){
		InitReceitas();
		return "receitas";
	}
}
