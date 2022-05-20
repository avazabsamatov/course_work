package uz.pdp.userservice.service;

import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.userservice.entitiy.User;
import uz.pdp.userservice.projection.ModuleProjection;
import uz.pdp.userservice.repository.ModuleRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    ModuleRepository moduleRepository;

    @Value("${STRIPE_SECRET_KEY}")
    String stripeApiKey;

    @Value("http://localhost:8080/")
    String baseUrl;

    @SneakyThrows
    @Transactional
    public ResponseEntity<?> createStripeSession(Long currentId) {
        List<ModuleProjection> moduleByUserid = moduleRepository.getModuleByUserid(currentId);


        String successUrl = baseUrl + "payment/success";
        String failedUrl = baseUrl + "payment/failed";
        Stripe.apiKey = stripeApiKey;

        ArrayList<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (ModuleProjection ticket : moduleByUserid) {
            lineItems.add(createSessionLineItem(ticket));
        }
        SessionCreateParams params = SessionCreateParams
                .builder()
                .addAllLineItem(lineItems)
                .setCancelUrl(failedUrl)
                .setSuccessUrl(successUrl)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setClientReferenceId(currentId.toString())
                .build();

        Session session = Session.create(params);
        String url = session.getUrl();
        return ResponseEntity.ok("SUCCESS");
    }

    private SessionCreateParams.LineItem createSessionLineItem(ModuleProjection ticket) {
        return SessionCreateParams
                .LineItem
                .builder()
                .setPriceData(createPriceData(ticket))
                .setQuantity(1L)
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(ModuleProjection ticket) {
        return SessionCreateParams
                .LineItem
                .PriceData
                .builder()
                .setCurrency("USD")
                .setUnitAmount((long) ((ticket.getPrice() * 100 + 30) / 0.971))
                .setProductData(createProductData(ticket))
                .build();
    }

    private SessionCreateParams.LineItem.PriceData.ProductData createProductData(ModuleProjection module) {
        return SessionCreateParams
                .LineItem
                .PriceData
                .ProductData
                .builder()
                .setName(" | " + module.getName()
                        + " -> " + module.getPrice()
                )
                .build();
    }

}
