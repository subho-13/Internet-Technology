package controller.corporate.add;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.flight.Flight;
import model.flight.FlightDao;
import util.Json;

@WebServlet("/corporate/add")
public class Add extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private List<String> addFlights(List<Flight> flights, String corporateName) throws Exception {
        final FlightDao flightDao = new FlightDao();
        final List<String> ids = new ArrayList<String>();

        for (final Flight flight : flights) {
            flight.setCorporateName(corporateName);
            if (flightDao.addFlight(flight) == true) {
                ids.add(flight.getId());
            }
        }

        return ids;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            final HttpSession session = request.getSession();
            final String token = (String) session.getAttribute("token");
            final String corporateName = (String) session.getAttribute("corporateName");
            final AddRespose addResponse = new AddRespose();

            if (token == null) {
                addResponse.setLoggedIn(false);
            } else {
                final AddRequest addRequest = Json.getObject(request.getReader(), AddRequest.class);

                if (token.equals(addRequest.getToken())) {
                    addResponse.setIds(this.addFlights(addRequest.getFlights(), corporateName));
                    addResponse.setLoggedIn(true);
                } else {
                    addResponse.setLoggedIn(false);
                }
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Json.writeObject(response.getWriter(), addResponse);

        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}
