package rest;

import dao.AdherentDAO;
import entity.Adherent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/adherents")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdherentResource {

    private EntityManager em = Persistence.createEntityManagerFactory("AppAssoSport-PU").createEntityManager();
    private AdherentDAO adherentDAO = new AdherentDAO(em);

    @GET
    public List<Adherent> getAllAdherents() {
        return adherentDAO.findAll();
    }

    @POST
    public Response createAdherent(Adherent adherent) {
        adherentDAO.create(adherent);
        return Response.status(Response.Status.CREATED).entity(adherent).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAdherent(@PathParam("id") Long id, Adherent adherent) {
        Adherent existing = adherentDAO.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        adherent.setId(id);
        adherentDAO.update(adherent);
        return Response.ok(adherent).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAdherent(@PathParam("id") Long id) {
        adherentDAO.delete(id);
        return Response.noContent().build();
    }
}
