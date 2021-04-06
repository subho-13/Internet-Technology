package controller.admin.login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.Json;
import util.Token;

@WebServlet("/admin/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final int TOKEN_SIZE = 10;
    private static final String USERID = "admin";
    private static final String PASSWORD = "admin123";

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            final LoginResponse loginResponse = new LoginResponse();
            final LoginCredentials credentials = Json
                    .getObject(request.getReader(), LoginCredentials.class);

            if (credentials.getUserid().equals(USERID)
                    && credentials.getPassword().equals(PASSWORD)) {
                final String token = Token.generate(TOKEN_SIZE);

                final HttpSession session = request.getSession();
                session.setAttribute("token", token);

                loginResponse.setLoggedIn(true);
                loginResponse.setToken(token);
            } else {
                loginResponse.setLoggedIn(false);
                loginResponse.setToken(null);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Json.writeObject(response.getWriter(), loginResponse);
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
