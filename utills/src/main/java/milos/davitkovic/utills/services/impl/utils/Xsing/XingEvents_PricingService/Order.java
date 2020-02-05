package milos.davitkovic.utills.services.impl.Interview.Xsing.XingEvents_PricingService;

import java.util.List;

public class Order {

    private List<Ticket> tickets;
    private Price price;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
