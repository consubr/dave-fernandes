package br.com.davefernandes.controller;

import br.com.davefernandes.model.Ator;
import br.com.davefernandes.ejb.AtorFacade;
import br.com.davefernandes.model.LazyDataModelBase;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "atorController")
@ViewScoped
public class AtorController extends AbstractController<Ator> implements Serializable {

    @EJB
    private AtorFacade ejbFacade;

    public AtorController() {
        super(Ator.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }
    
    private LazyDataModelBase<Ator> lazyModel = new LazyDataModelBase<Ator>(ejbFacade);

    public void setLazyModel(LazyDataModelBase<Ator> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public LazyDataModelBase<Ator> getLazyModel() {
        lazyModel.setFacade(ejbFacade);
        return lazyModel;
    }

    public AtorFacade getAtorFacade() {
        return ejbFacade;
    }

    public void setAtorFacade(AtorFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
        lazyModel.setFacade(ejbFacade);
    }
}
