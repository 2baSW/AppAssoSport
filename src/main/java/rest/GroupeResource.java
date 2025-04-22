package rest;

import dao.GroupeDAO;
import entity.Groupe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/groupes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupeResource {

    private EntityManager em = Persistence.createEntityManagerFactory("AppAssoSport-PU").createEntityManager();
    private GroupeDAO groupeDAO = new GroupeDAO(em);

    @GET
    public List<Groupe> getAllGroupsWithAdherents() {
        return groupeDAO.findGroupsWithAdherents();
    }
}
