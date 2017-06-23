package io.altar.upacademy.service;

import java.util.List;

import javax.inject.Named;

import io.altar.upacademy.model.Receita;

@Named("paginator")
public class Paginator {

	/*-----------------------------------------------------------------------------------*/
	/* FIELDS */
	/*-----------------------------------------------------------------------------------*/

	// RECEITAS PER PAGE
	private static final int DEFAULT_RECEITAS_NUMBER = 4;
	// PAGE INDEX START
	private static final int DEFAULT_PAGE_INDEX = 1;

	private int receitas;
	private int receitasTotal;
	private int pageIndex;
	private int pages;
	private List<Receita> origReceitaResult;
	private List<Receita> receitaResult;

	/*-----------------------------------------------------------------------------------*/
	/* CONSTRUCTOR */
	/*-----------------------------------------------------------------------------------*/

	public Paginator(List<Receita> receitaResult) {
		this.origReceitaResult = receitaResult;
		this.receitas = DEFAULT_RECEITAS_NUMBER;
		this.pageIndex = DEFAULT_PAGE_INDEX;
		this.receitasTotal = receitaResult.size();

		if (receitas > 0) {
			pages = receitas <= 0 ? 1 : receitasTotal / receitas;

			if (receitasTotal % receitas > 0) {
				pages++;
			}

			if (pages == 0) {
				pages = 1;
			}

		} else {
			receitas = 1;
			pages = 1;
		}

		updateReceitaResult();
	}

	/*-----------------------------------------------------------------------------------*/
	/* METHODS */
	/*-----------------------------------------------------------------------------------*/

	public void updateReceitaResult() {
		int fromIndex = getFirst();
		int toIndex = getFirst() + receitas;

		if (toIndex > this.receitasTotal) {
			toIndex = this.receitasTotal;
		}

		this.receitaResult = origReceitaResult.subList(fromIndex, toIndex);
	}

	public void next() {
		if (this.pageIndex < pages) {
			this.pageIndex++;
		}

		updateReceitaResult();
	}

	public void prev() {
		if (this.pageIndex > 1) {
			this.pageIndex--;
		}

		updateReceitaResult();
	}

	// Generates an Array From 1 to n
	public int[] arrayFrom1ToN(int n) {
		int[] a = new int[n];
		for (int i = 1; i < n; ++i) {
			a[i] = i;
		}
		return a;
	}

	/*-----------------------------------------------------------------------------------*/
	/* GETTERS AND SETTERS */
	/*-----------------------------------------------------------------------------------*/

	public int getFirst() {
		return (pageIndex * receitas) - receitas;
	}

	public int getReceitas() {
		return receitas;
	}

	public void setReceitas(int receitas) {
		this.receitas = receitas;
	}

	public int getReceitasTotal() {
		return receitasTotal;
	}

	public void setReceitasTotal(int receitasTotal) {
		this.receitasTotal = receitasTotal;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<Receita> getOrigReceitaResult() {
		return origReceitaResult;
	}

	public void setOrigReceitaResult(List<Receita> origReceitaResult) {
		this.origReceitaResult = origReceitaResult;
	}

	public List<Receita> getReceitaResult() {
		return receitaResult;
	}

	public void setReceitaResult(List<Receita> receitaResult) {
		this.receitaResult = receitaResult;
	}

	public static int getDefaultReceitasNumber() {
		return DEFAULT_RECEITAS_NUMBER;
	}

	public static int getDefaultPageIndex() {
		return DEFAULT_PAGE_INDEX;
	}

}
