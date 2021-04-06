package controller.corporate.get;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.flight.FlightFilter;
import model.flight.FlightsFilterDao;
import util.Json;

@WebServlet("/corporate/get")
public class Get extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            final HttpSession session = request.getSession();
            final String token = (String) session.getAttribute("token");
            final String corporateName = (String) session.getAttribute("corporateName");
            final GetRequest getRequest = Json.getObject(request.getReader(), GetRequest.class);
            final GetResponse getResponse = new GetResponse();

            if (token == null) {
                getResponse.setLoggedIn(false);
            } else if (token.equals(getRequest.getToken()) == true) {
                getResponse.setLoggedIn(true);

                final FlightsFilterDao flightsFilterDao = new FlightsFilterDao();
                final FlightFilter flightFilter = new FlightFilter();
                flightFilter.setCorporate(corporateName);
                getResponse.setFlights(flightsFilterDao.getFlights(flightFilter));

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                Json.writeObject(response.getWriter(), getResponse);
            } else {
                getResponse.setLoggedIn(false);
            }
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}
