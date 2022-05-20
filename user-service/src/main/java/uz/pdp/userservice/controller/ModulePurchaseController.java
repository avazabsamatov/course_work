package uz.pdp.userservice.controller;

import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.userservice.entitiy.User;
import uz.pdp.userservice.repository.UserRepository;
import uz.pdp.userservice.service.PurchaseService;
import uz.pdp.userservice.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/purchase")
public class ModulePurchaseController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PurchaseService purchaseService;

    @GetMapping
    public ResponseEntity<?> createStripeSession() throws StripeException {
       User ali =  userRepository.findByUserName("ali");
        return purchaseService.createStripeSession(ali.getId());
    }
}
