package co.com.sofka.registrytour.collections;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cyclist {

    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String number;
    private String nationality;
    private String idEquipo;

}
