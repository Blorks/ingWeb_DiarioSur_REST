/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Evento;
import entity.Tag;
import entity.Tagevento;
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
@Path("entity.tagevento")
public class TageventoFacadeREST extends AbstractFacade<Tagevento> {

    @PersistenceContext(unitName = "diario_Sur_RESTPU")
    private EntityManager em;

    public TageventoFacadeREST() {
        super(Tagevento.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tagevento entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Tagevento entity) {
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
    public Tagevento find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagevento> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagevento> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("tagEvento/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagevento> encontrarTagEvPorID(@PathParam("id") int id){
        Query q;
        
        q = em.createQuery("select t from Tagevento t where t.tagId.id = :id");
        q.setParameter("id",  id);
        return q.getResultList();
    }
    
    @GET
    @Path("tagEvento/{tag}/{ev}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagevento> encontrarTagEvPorTagyEvento(@PathParam("tag") int idTag, @PathParam("ev") int idEvento){
        Query q;
        
        q = em.createQuery("select t from Tagevento t where t.tagId.id = :tag AND t.eventoId.id = :ev");
        q.setParameter("tag",  idTag);
        q.setParameter("ev",  idEvento);
        return q.getResultList();
    }
    
    @GET
    @Path("tagEventoEv/{idEvento}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagevento> encontrarTagEv(@PathParam("idEvento") int idEvento){
        Query q;
        
        q = em.createQuery("select t from Tagevento t where t.eventoId.id = :idEvento");
        q.setParameter("idEvento",  idEvento);
        return q.getResultList();
    }
    
    @GET
    @Path("tagEventoTag/{tagID}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagevento> encontrarTagEvPorTag(@PathParam("tag") int tagID) {
        Query q;
        
        q = em.createQuery("select t from Tagevento t where t.tagId.id = :tagID");
        q.setParameter("tagID",  tagID);
        return q.getResultList();
    }
}
