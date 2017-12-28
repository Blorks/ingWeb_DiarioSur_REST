/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Evento;
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
@Path("entity.evento")
public class EventoFacadeREST extends AbstractFacade<Evento> {

    @PersistenceContext(unitName = "diario_Sur_RESTPU")
    private EntityManager em;

    public EventoFacadeREST() {
        super(Evento.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Evento entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Evento entity) {
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
    public Evento find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("evento/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> encontrarEventoByID(@PathParam("id") int id) {
        Query q; 
        
        q = em.createQuery("select e from Evento e where e.id = :id");
        q.setParameter("id",  id);
        return q.getResultList();
    }
    
    
    @GET
    @Path("revisado")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> encontrarEventosRevisados() {
        Query q; 
        
        int id=1;
        
        q = em.createQuery("select e from Evento e where e.estarevisado = :id");
        q.setParameter("id",  id);
        return q.getResultList();
    }
    
    @GET
    @Path("eventoUsuario/{user}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> encontrarEventoByUsuario(@PathParam("user") int idUsuario) {
        Query q; 
        
        q = em.createQuery("select e from Evento e where e.usuarioId.id = :user");
        q.setParameter("user",  idUsuario);
        return q.getResultList();
    }
    
    @GET
    @Path("eventoPrecio/{precioMax}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> encontrarEventoByPrecioMax(@PathParam("precioMax") double precioMax) {
        Query q; 
        
        q = em.createQuery("select e from Evento e where e.precio <= :precioMax");
        q.setParameter("precioMax", precioMax);
        return q.getResultList();
    }
    
    @GET
    @Path("noRevisado")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> encontrarEventosNoRevisados() {
        Query q;
        int estaRevisadoTemp = 0;
        
        q = em.createQuery("select e from Evento e where e.estarevisado = :estaRevisadoTemp");
        q.setParameter("estaRevisadoTemp", estaRevisadoTemp);
        return q.getResultList();
    }
    
    @DELETE
    @Path("borrar/{id}")
    public void eliminarEventoPorID(@PathParam("id") int id) {
        Query q; 
        
        q = em.createQuery("DELETE FROM Evento e where e.id = :id");
        q.setParameter("id",  id);
    }
    
    
    @GET
    @Path("ultimo")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> ultimoIDInsertado(){
        Query q;
        
        q = em.createQuery("SELECT e FROM Evento e ORDER BY e.id DESC");
        return q.getResultList();
    }
    
    @GET
    @Path("ordenAlfabetico")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> ordenarEventosAlfabeticamente(){
        Query q;
        
        q = em.createQuery("SELECT e FROM Evento e ORDER BY e.titulo");
        return q.getResultList();
    }
    
    @GET
    @Path("ordenPrecio")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> ordenarEventosPrecio(){
        Query q;
        
        q = em.createQuery("SELECT e FROM Evento e ORDER BY e.precio");
        return q.getResultList();
    }
    
    @GET
    @Path("ordenAlfabeticoDESC")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> ordenarEventosAlfabeticamenteDESC(){
        Query q;
        
        q = em.createQuery("SELECT e FROM Evento e ORDER BY e.titulo DESC");
        return q.getResultList();
    }
    
    @GET
    @Path("ordenPrecioDESC")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> ordenarEventosPrecioDESC(){
        Query q;
        
        q = em.createQuery("SELECT e FROM Evento e ORDER BY e.precio DESC");
        return q.getResultList();
    }
    
    @GET
    @Path("eventoFechaDesde/{idFecha}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Evento> encontrarEventosPorFecha(@PathParam("idFecha") int idFecha) { //duda sobre si tratará el date bien o hacerlo con String y en ese caso, lo pilla bien?
        Query q; 
        
        q = em.createQuery("select e from Evento e WHERE e.dateevId.id = :idFecha");
        q.setParameter("idFecha", idFecha);
        return q.getResultList();
    }
}