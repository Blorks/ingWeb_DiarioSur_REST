/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Tag;
import entity.Tagusuario;
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
    
    //añadido
    
    /* ya implementado??
    public List<Tagusuario> encontrarTagUserPorID(int id){
        Query q;
        
        q = em.createQuery("select t from Tagusuario t where t.tagId.id = :id");
        q.setParameter("id",  id);
        return q.getResultList();
    }
    */
    
    @GET
    @Path("tagUsuario/{user}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagusuario> encontrarTagUser(@PathParam("user") Usuario user){
        Query q;
        
        q = em.createQuery("select t from Tagusuario t where t.usuarioId = :user");
        q.setParameter("user",  user);
        return q.getResultList();
    }
    
    @GET
    @Path("tagUsuario/{tag}/{user}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tagusuario> encontrarTagUserPorTagyUsuario(@PathParam("tag") Tag tag, @PathParam("user") Usuario user){
        Query q;
        
        q = em.createQuery("select t from Tagusuario t where t.tagId = :tag AND t.usuarioId = :user");
        q.setParameter("tag",  tag);
        q.setParameter("user",  user);
        return q.getResultList();
    }
}
