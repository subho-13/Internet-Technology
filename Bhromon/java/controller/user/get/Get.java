package controller.user.get;

import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.flight.Flight;
import model.flight.FlightFilter;
import model.flight.FlightParams;
import model.flight.FlightParamsDao;
import model.flight.FlightsFilterDao;
import util.Json;

@WebServlet("/user/get")
public class Get extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            final FlightParamsDao flightParamsDao = new FlightParamsDao();
            final FlightParams flightParams = flightParamsDao.getParams();

            final FlightFilter flightFilter = new FlightFilter();
            flightFilter.setSpecial(true);
            final FlightsFilterDao flightsFilterDao = new FlightsFilterDao();
            final List<Flight> flights = flightsFilterDao.getFlights(flightFilter);

            final GetResponse getResponse = new GetResponse();
            getResponse.setFlightParams(flightParams);
            getResponse.setSpecialFlights(flights);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Json.writeObject(response.getWriter(), getResponse);
        } catch (final Exception exception) {
            exception.printStackTrace();
        }
    }
}
