/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Tagusuario;
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
@Path("entity.tagusuario")
public class TagusuarioFacadeREST extends AbstractFacade<Tagusuario> {

    @PersistenceContext(unitName = "diario_Sur_RESTPU")
    private EntityManager em;

    public TagusuarioFacadeREST() {
        super(Tagusuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tagusuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Tagusuario entity) {
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
    public Tagusuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagusuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagusuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("tagUsuarioID/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagusuario> encontrarTagUserPorID(@PathParam("id") int id){
        Query q;
        
        q = em.createQuery("select t from Tagusuario t where t.tagId.id = :id");
        q.setParameter("id",  id);
        return q.getResultList();
    }
    
    @GET
    @Path("tagUsuario/{idUsuario}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagusuario> encontrarTagUser(@PathParam("idUsuario") int idUsuario){
        Query q;
        
        q = em.createQuery("select t from Tagusuario t where t.usuarioId.id = :idUsuario");
        q.setParameter("idUsuario",  idUsuario);
        return q.getResultList();
    }
    
    @GET
    @Path("tagUsuario/{tag}/{user}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagusuario> encontrarTagUserPorTagyUsuario(@PathParam("tag") int idTag, @PathParam("user") int idUsuario){
        Query q;
        
        q = em.createQuery("select t from Tagusuario t where t.tagId.id = :tag AND t.usuarioId.id = :user");
        q.setParameter("tag",  idTag);
        q.setParameter("user",  idUsuario);
        return q.getResultList();
    }
}