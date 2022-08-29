package co.com.sofka.registrytour.collections;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Team {

    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String code;
    private String country;
    private List<Cyclist> cyclists;

}
