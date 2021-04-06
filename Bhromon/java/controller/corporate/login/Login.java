package controller.corporate.login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.login.Corporate;
import model.login.LoginDao;
import util.Json;
import util.Token;

@WebServlet("/corporate/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int TOKEN_SIZE = 10;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            final LoginRequest loginRequest = Json
                    .getObject(request.getReader(), LoginRequest.class);
            final Corporate corporate = new Corporate();
            corporate.setUserid(loginRequest.getUserid());
            corporate.setPassword(loginRequest.getPassword());

            final LoginResponse loginResponse = new LoginResponse();
            final LoginDao loginDao = new LoginDao();
            if (loginDao.getCompanyName(corporate)) {
                final String token = Token.generate(TOKEN_SIZE);

                final HttpSession session = request.getSession();
                session.setAttribute("token", token);
                session.setAttribute("corporateName", corporate.getName());

                loginResponse.setLoggedIn(true);
                loginResponse.setToken(token);
                loginResponse.setCorporateName(corporate.getName());

            } else {
                loginResponse.setLoggedIn(false);
                loginResponse.setToken(null);
                loginResponse.setCorporateName(null);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Json.writeObject(response.getWriter(), loginResponse);
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}
