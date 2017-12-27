/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Dani
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(facade.CalendarioFacadeREST.class);
        resources.add(facade.DateevFacadeREST.class);
        resources.add(facade.EventoFacadeREST.class);
        resources.add(facade.FileevFacadeREST.class);
        resources.add(facade.NotificacionFacadeREST.class);
        resources.add(facade.TagFacadeREST.class);
        resources.add(facade.TageventoFacadeREST.class);
        resources.add(facade.TagusuarioFacadeREST.class);
        resources.add(facade.UsuarioFacadeREST.class);
    }
    
}
