package milos.davitkovic.utills.services.impl.Interview.Xsing.XingEvents_PricingService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceService {

    private static final String EURO = "Euro";

    public void calculatePrice(final Ticket ticket) {
        Integer totalSum = ticket.getPrice().getValue();;
        final List<Product> products = ticket.getProducts();
        for(Product product : products) {
            totalSum += product.getPrice().getValue();
        }
        final Integer discount = Optional.ofNullable(ticket.getDiscount())
                .map(Discount::getPercentage)
                .orElse(0);
        totalSum = totalSum - (totalSum / 100) * discount;
        final Price totalPrice = new Price();
        totalPrice.setValue(totalSum);
        totalPrice.setCurrency(EURO);
        ticket.setTotalPrice(totalPrice);
    }

    public void calculatePrice(final Order order) {
        Integer totalSum = 0;
        final List<Ticket> tickets = order.getTickets();
        for(Ticket ticket : tickets) {
            totalSum += Optional.ofNullable(ticket.getTotalPrice())
                    .map(Price::getValue)
                    .orElse(0);
        }
        final Price totalPrice = new Price();
        totalPrice.setValue(totalSum);
        totalPrice.setCurrency(EURO);
        order.setPrice(totalPrice);
    }
}
