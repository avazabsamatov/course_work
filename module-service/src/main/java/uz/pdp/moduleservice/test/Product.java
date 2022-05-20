package uz.pdp.moduleservice.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Integer id;
    private  String name;
    private Double price;


    public int compareTo(Product product2){
        if(product2.getPrice()>price){
            return 1;
        } else if(product2.getPrice()==price){
            return 0;
        }
        else return -1;

    }

}
