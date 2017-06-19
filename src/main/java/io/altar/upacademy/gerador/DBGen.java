package io.altar.upacademy.gerador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import javax.inject.Named;


import io.altar.upacademy.service.megaRepository;

@ManagedBean(eager = true)
@Named("dbGen")
@ApplicationScoped

public class DBGen implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private megaRepository megaRep = new megaRepository();
	
	public DBGen(){}
	
	@PostConstruct
	public void initDB(){
		
	}
}
