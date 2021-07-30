package milos.davitkovic.utills.services.impl.Interview.Xsing.XingEvents_PricingService;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceServiceTest {

    private final Integer TOTAL_TICKET_PRICE = 20;
    private PriceService priceService = new PriceService();
    private final Ticket ticket = new Ticket();

    @Before
    public void setUp() {
        final Price productPrice = new Price();
        productPrice.setValue(50);
        productPrice.setCurrency("Euro");

        final Product product = new Product();
        product.setName("T-Shirt");
        product.setPrice(productPrice);

        final Price ticketPrice = new Price();
        ticketPrice.setValue(50);
        ticketPrice.setCurrency("Euro");

        final Discount ticketDiscount = new Discount();
        ticketDiscount.setPercentage(5);

        ticket.setPrice(ticketPrice);
        ticket.setProducts(Arrays.asList(product));
        ticket.setDiscount(ticketDiscount);
    }

    @Test
    public void calculatePrice_okFlow() {
        priceService.calculatePrice(ticket);
        assertEquals(TOTAL_TICKET_PRICE, Integer.valueOf(ticket.getTotalPrice().getValue()));
    }


}