package servlet;

import dao.GroupeDAO;
import entity.Groupe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.util.List;

@WebServlet("/groupes")
public class GroupeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private EntityManager em;
    private GroupeDAO groupeDAO;

    @Override
    public void init() {
        em = Persistence.createEntityManagerFactory("AppAssoSport-PU").createEntityManager();
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
            throw new ServletException("Erreur dans GroupeServlet : " + e.getMessage(), e);
        }
    }

    private void handleEditRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        Groupe groupe = null;
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            groupe = groupeDAO.findById(id);
        }
        req.setAttribute("groupe", groupe);
        req.getRequestDispatcher("/WEB-INF/groupes/form.jsp").forward(req, resp);
    }

    private void handleDetailsRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            Groupe groupe = groupeDAO.findById(id);
            req.setAttribute("groupe", groupe);
        }
        req.getRequestDispatcher("/WEB-INF/groupes/details.jsp").forward(req, resp);
    }

    private void handleListRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Groupe> groupes = groupeDAO.findAll();
        req.setAttribute("groupes", groupes);
        req.getRequestDispatcher("/WEB-INF/groupes/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("save".equals(action)) {
                handleSaveRequest(req, resp);
            } else if ("delete".equals(action)) {
                handleDeleteRequest(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Erreur dans GroupeServlet : " + e.getMessage(), e);
        }
    }

    private void handleSaveRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = req.getParameter("id") != null && !req.getParameter("id").isEmpty()
                ? Long.parseLong(req.getParameter("id")) : null;
        String nom = req.getParameter("nom");

        Groupe groupe = (id != null) ? groupeDAO.findById(id) : new Groupe();
        groupe.setNom(nom);

        if (id == null) {
            groupeDAO.create(groupe);
        } else {
            groupeDAO.update(groupe);
        }
        resp.sendRedirect(req.getContextPath() + "/groupes");
    }

    private void handleDeleteRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            Long id = Long.parseLong(idParam);
            groupeDAO.delete(id);
        }
        resp.sendRedirect(req.getContextPath() + "/groupes");
    }

    @Override
    public void destroy() {
        if (em != null) {
            em.close();
        }
    }
}
