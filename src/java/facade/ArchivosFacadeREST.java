/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Archivos;
import entity.Evento;
import entity.Fileev;
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
@Path("entity.archivos")
public class ArchivosFacadeREST extends AbstractFacade<Archivos> {

    @PersistenceContext(unitName = "diario_Sur_RESTPU")
    private EntityManager em;

    public ArchivosFacadeREST() {
        super(Archivos.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Archivos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Archivos entity) {
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
    public Archivos find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Archivos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Archivos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("archivoID/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Archivos> encontrarArchivoPorID(@PathParam("id") int id) {
        Query q;
        
        q = em.createQuery("select a from Archivos a where a.id = :id");
        q.setParameter("id",  id);
        return q.getResultList();
    }
    
    @GET
    @Path("archivo/{evento}/{archivo}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Archivos> encontrarArchivoPorEventoYArchivo(@PathParam("evento") Evento evento, @PathParam("archivo") Fileev archivo) {
        Query q;
        
        q = em.createQuery("select a from Archivos a where a.eventoId = :evento AND a.fileevId = :archivo");
        q.setParameter("evento",  evento);
        q.setParameter("archivo",  archivo);
        return q.getResultList();
    }
    
    @GET
    @Path("archivoEvento/{evento}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Archivos> encontrarArchivoPorEvento(@PathParam("evento") Evento evento) {
        Query q;
        
        q = em.createQuery("select a from Archivos a where a.eventoId = :evento");
        q.setParameter("evento",  evento);
        return q.getResultList();
    }
    
    @GET
    @Path("archivoFile/{archivo}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Archivos> encontrarArchivoPorFile(@PathParam("archivo") Fileev archivo) {
        Query q;
        
        q = em.createQuery("select a from Archivos a where a.fileevId = :archivo");
        q.setParameter("archivo",  archivo);
        return q.getResultList();
    }
}
