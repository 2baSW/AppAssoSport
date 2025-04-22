package servlet;

import dao.AdherentDAO;
import dao.GroupeDAO;
import entity.Adherent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/adherents")
public class AdherentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private EntityManager em;
    private AdherentDAO adherentDAO;
    private GroupeDAO groupeDAO;

    @Override
    public void init() {
        em = Persistence.createEntityManagerFactory("AppAssoSport-PU").createEntityManager();
        adherentDAO = new AdherentDAO(em);
        groupeDAO = new GroupeDAO(em);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("edit".equals(action)) {
                handleEditRequest(req, resp);
            } else if ("details".equals(action)) {
                handleDetailsRequest(req, resp);
            } else {
                handleListRequest(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Erreur dans AdherentServlet : " + e.getMessage(), e);
        }
    }

    private void handleEditRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        Adherent adherent = null;
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            adherent = adherentDAO.findById(id);
        }
        req.setAttribute("adherent", adherent);
        req.setAttribute("groupes", groupeDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/adherents/form.jsp").forward(req, resp);
    }

    private void handleDetailsRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            Adherent adherent = adherentDAO.findById(id);
            req.setAttribute("adherent", adherent);
        }
        req.getRequestDispatcher("/WEB-INF/adherents/details.jsp").forward(req, resp);
    }

    private void handleListRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Adherent> adherents = adherentDAO.findAll();
        req.setAttribute("adherents", adherents);
        req.setAttribute("groupes", groupeDAO.findAll());
        req.getRequestDispatcher("/WEB-INF/adherents/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("save".equals(action)) {
                handleSaveRequest(req, resp);
            } else if ("delete".equals(action)) {
                handleDeleteRequest(req, resp);
            } else if ("assign".equals(action)) {
                handleAssignRequest(req, resp);
            } else if ("removeGroup".equals(action)) {
                handleRemoveGroupRequest(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Erreur dans AdherentServlet : " + e.getMessage(), e);
        }
    }

    private void handleSaveRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = req.getParameter("id") != null && !req.getParameter("id").isEmpty()
                ? Long.parseLong(req.getParameter("id")) : null;
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String adresse = req.getParameter("adresse");
        String codePostal = req.getParameter("codePostal");
        String ville = req.getParameter("ville");
        LocalDate dateNaissance = LocalDate.parse(req.getParameter("dateNaissance"));
        LocalDate datePaiementCotisation = LocalDate.parse(req.getParameter("datePaiementCotisation"));

        Adherent adherent = (id != null) ? adherentDAO.findById(id) : new Adherent();
        adherent.setNom(nom);
        adherent.setPrenom(prenom);
        adherent.setEmail(email);
        adherent.setTelephone(telephone);
        adherent.setAdresse(adresse);
        adherent.setCodePostal(codePostal);
        adherent.setVille(ville);
        adherent.setDateNaissance(dateNaissance);
        adherent.setDatePaiementCotisation(datePaiementCotisation);

        if (id == null) {
            adherentDAO.create(adherent);
        } else {
            adherentDAO.update(adherent);
        }
        resp.sendRedirect(req.getContextPath() + "/adherents");
    }

    private void handleDeleteRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        adherentDAO.delete(id);
        resp.sendRedirect(req.getContextPath() + "/adherents");
    }

    private void handleAssignRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long adherentId = Long.parseLong(req.getParameter("adherentId"));
        Long groupId = Long.parseLong(req.getParameter("groupId"));
        adherentDAO.assignToGroup(adherentId, groupId);
        resp.sendRedirect(req.getContextPath() + "/adherents");
    }

    private void handleRemoveGroupRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long adherentId = Long.parseLong(req.getParameter("adherentId"));
        adherentDAO.removeFromGroup(adherentId);
        resp.sendRedirect(req.getContextPath() + "/adherents");
    }

    @Override
    public void destroy() {
        if (em != null) {
            em.close();
        }
    }
}
