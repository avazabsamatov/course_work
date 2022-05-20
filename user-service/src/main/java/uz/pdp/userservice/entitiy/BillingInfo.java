package uz.pdp.userservice.entitiy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "billing_infos")
public class BillingInfo  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardHolderName;

    private Long cardNumber;

    private LocalDate experationMoth = LocalDate.now();

    private LocalDate experationYear = LocalDate.now();

    @ManyToOne
    User user;
}
