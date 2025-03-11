package Project.springb.SpringStarter.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue

    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT") //this column stores longer form of string
    private String body;

    private LocalDateTime createdAt;
}
