/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Notificacion;
import entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Dani
 */
@Stateless
@Path("entity.notificacion")
public class NotificacionFacadeREST extends AbstractFacade<Notificacion> {

    @PersistenceContext(unitName = "diario_Sur_RESTPU")
    private EntityManager em;

    public NotificacionFacadeREST() {
        super(Notificacion.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Notificacion entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Notificacion entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Notificacion find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notificacion> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notificacion> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("notificacionUsuario/{idUser}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notificacion> encontrarNotificacionByUsuario(@PathParam("idUser") int idUsuario) {
        Query q;
        int leida = 0;
        
        q = em.createQuery("select n from Notificacion n where n.usuarioId.id = :idUser AND n.leida = :leida");
        q.setParameter("idUser",  idUsuario);
        q.setParameter("leida",  leida);
        return q.getResultList();
    }
    
    @GET
    @Path("notificacionTodas/{idUser}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notificacion> encontrarTodasLasNotificacionesDeUsuario(@PathParam("idUser") int idUser) {
        Query q; 
        
        q = em.createQuery("select n from Notificacion n where n.usuarioId.id = :idUser");
        q.setParameter("idUser",  idUser);
        return q.getResultList();
    }
    
    @GET
    @Path("notificacion/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Notificacion> encontrarNotificacionByID(@PathParam("id") int id) {
        Query q; 
        
        q = em.createQuery("select n from Notificacion n where n.id = :id");
        q.setParameter("id",  id);
        return q.getResultList();
    }
}