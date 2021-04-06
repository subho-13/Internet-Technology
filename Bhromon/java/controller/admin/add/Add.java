package controller.admin.add;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.login.Corporate;
import model.login.LoginDao;
import util.Json;

@WebServlet("/admin/add")
public class Add extends HttpServlet {
    private static final long serialVersionUID = 1L;
    LoginDao loginDao = new LoginDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {

            final HttpSession session = request.getSession();
            final AddResponse addResponse = new AddResponse();
            final String sessionToken = (String) session.getAttribute("token");

            if (sessionToken == null) {
                addResponse.setLoggedIn(false);
            } else {
                final AddCredentials credentials = Json.getObject(request.getReader(),
                                AddCredentials.class);
                if (sessionToken.equals(credentials.getToken())) {
                    final Corporate corporate = new Corporate();
                    corporate.setName(credentials.getcorporateName());

                    this.loginDao.getDetail(corporate);

                    addResponse.setLoggedIn(true);
                    addResponse.setUserid(corporate.getUserid());
                    addResponse.setPassword(corporate.getPassword());
                } else {
                    addResponse.setLoggedIn(false);
                }
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Json.writeObject(response.getWriter(), addResponse);
        } catch (final Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
