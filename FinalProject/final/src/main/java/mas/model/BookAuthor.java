package mas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    @NotNull
    @JsonBackReference
    private Author author;

    @ManyToOne
    @JoinColumn(name = "book_id" , nullable = false)
    @NotNull
    @JsonBackReference
    private Book book;
}
