package controller.user.filter;

import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.flight.Flight;
import model.flight.FlightFilter;
import model.flight.FlightsFilterDao;
import util.Json;

@WebServlet("/user/filter")
public class Filter extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            final FlightFilter flightFilter = Json.getObject(request.getReader(),
                            FlightFilter.class);

            final FlightsFilterDao flightsFilterDao = new FlightsFilterDao();
            final List<Flight> flights = flightsFilterDao.getFlights(flightFilter);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Json.writeObject(response.getWriter(), flights);
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}
