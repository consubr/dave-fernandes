/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.davefernandes.model;

import br.com.davefernandes.ejb.AbstractFacade;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyDataModelBase<T> extends LazyDataModel<T> {

    private final static Logger log = Logger.getLogger(LazyDataModelBase.class.getName());
    // Always use the classname, this way you can refactor
    private volatile AbstractFacade<T> facade;

    public LazyDataModelBase(AbstractFacade<T> facade) {
        super();
        this.facade = facade;
    }

    public final AbstractFacade<T> getFacade() {
        return facade;
    }

    public final void setFacade(AbstractFacade<T> facade) {
        this.facade = facade;
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        List<T> list = facade.load(first, pageSize, sortField, sortOrder, filters);

        log.log(Level.INFO, "list.size():{0}", String.valueOf(list.size()));

        if (list == null) {
            setRowCount(0);
            //throw new RuntimeException("Problem.");
        } else {
            setRowCount(facade.count());
        }
        // I am using the following line for debugging:
        // throw new RuntimeException(list.toString());
        return list;
    }
}