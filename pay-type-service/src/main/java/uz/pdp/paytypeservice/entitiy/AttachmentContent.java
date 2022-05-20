package uz.pdp.paytypeservice.entitiy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "attachment_contents")
public class AttachmentContent  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] data;

    @OneToOne
     Attachment attachment;

    public AttachmentContent(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AttachmentContent{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
