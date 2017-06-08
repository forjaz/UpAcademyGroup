package io.altar.upacademy.service;

import io.altar.upacademy.model.Receita;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named("receitaService")
@RequestScoped
@Transactional
public class ReceitaService extends EntityService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Constructor
	public ReceitaService() {

	}

	// DB Methods
	public String newReceita(Receita receita) {
		em.persist(receita);
		return "receitas";
	}

	// Getters and Setters
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/*@PostConstruct
	public void novaReceita() {
		
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

	}*/
}
