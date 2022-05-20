package uz.pdp.userservice.controller;

import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.userservice.service.PurchaseService;

import java.util.Objects;

@RestController
public class StripePaymentController {
    @Autowired
    PurchaseService purchaseService;
    @Value("${STRIPE_SECRET_KEY}")
    String stripeApiKey;

    String endpointSecret = "whsec_df1271d3d60ec401328be97c94ef9879fd52cd2520267f0f355b0450bc9bcb11";

    @RequestMapping(value = "payment/success", method = RequestMethod.GET)
    public ResponseEntity<?> paymentSuccess(){
        return ResponseEntity.ok().body("Successfully Payment");
    }
    @RequestMapping(value = "payment/failed",method = RequestMethod.GET)
    public ResponseEntity<?> paymentFailed(){
        return ResponseEntity.ok().body("Payment failed");
    }
    @RequestMapping( value = "stripe-webhook",method = RequestMethod.POST)
    public ResponseEntity<?> handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String sigHeader) {
        Stripe.apiKey=stripeApiKey;

        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (Exception e){
            e.printStackTrace();
        }

//        if ("checkout.session.completed".equals(Objects.requireNonNull(event).getType())) {
//            Session session = (Session) event.getDataObjectDeserializer().getObject().get();
//            return purchaseService.fulfillOrder(session);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR");

        }
}
