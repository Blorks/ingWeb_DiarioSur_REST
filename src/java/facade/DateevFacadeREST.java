/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Dateev;
import entity.Evento;
import java.util.Date;
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
@Path("entity.dateev")
public class DateevFacadeREST extends AbstractFacade<Dateev> {

    @PersistenceContext(unitName = "diario_Sur_RESTPU")
    private EntityManager em;

    public DateevFacadeREST() {
        super(Dateev.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Dateev entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Dateev entity) {
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
    public Dateev find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dateev> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dateev> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("date/{dia}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dateev> encontrarFechaPorDia(@PathParam("dia") Date dia) {
        Query q; 
        
        q = em.createQuery("select f from Dateev f where f.dia = :dia");
        q.setParameter("dia", dia);
        return q.getResultList();
    }
    
    @GET
    @Path("date/{inicio}/{fin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dateev> encontrarFechaPorInicioFin(@PathParam("inicio") Date inicio, @PathParam("fin") Date fin) {
        Query q; 
        
        q = em.createQuery("select f from Dateev f where f.desde = :inicio AND f.hasta = :fin");
        q.setParameter("inicio", inicio);
        q.setParameter("fin", fin);
        return q.getResultList();
    }
    
    @GET
    @Path("dateDias/{listaDias}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dateev> encontrarFechaPorListaDias(@PathParam("listaDias") String listaDias) {
        Query q; 
        
        q = em.createQuery("select f from Dateev f where f.listadias like :listaDias");
        q.setParameter("listaDias", listaDias);
        return q.getResultList();
    }
    
    @GET
    @Path("dateID/{idFecha}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dateev> encontrarFechaPorID(@PathParam("idFecha") int idFecha) {
        Query q; 
        
        q = em.createQuery("select f from Dateev f where f.id = :idFecha");
        q.setParameter("idFecha", idFecha);
        return q.getResultList();
    }
    
    @GET
    @Path("ultimo")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Dateev> ultimoIDInsertado(){
        Query q;
        
        q = em.createQuery("SELECT d FROM Dateev d ORDER BY d.id DESC");
        return q.getResultList();
    }
    
}
