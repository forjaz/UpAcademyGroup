package io.altar.upacademy.model;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named("scheduleView")
@RequestScoped

public class ScheduleView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Date date1;
    private Date date2;
    ArrayList<String> quartos = new ArrayList<String>();
    
    @PostConstruct
    public void init() {
    	quartos.add("uno");
        quartos.add("dos");
        quartos.add("tres");
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
 
    public Date getDate1() {
        return date1;
    }
 
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
 
    public Date getDate2() {
        return date2;
    }
 
    public void setDate2(Date date2) {
        this.date2 = date2;
    }

	public ArrayList<String> getQuartos() {
		return quartos;
	}

	public void setQuartos(ArrayList<String> quartos) {
		this.quartos = quartos;
	}
}
