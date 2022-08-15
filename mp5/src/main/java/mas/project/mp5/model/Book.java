package mas.project.mp5.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "ISBN is mandatory")
    @Pattern(regexp = "^\\d{13}$")
    private String ISBN;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Publish Date is mandatory")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Publish Date cannot be from future")
    private Date publishDate;

    @Column(nullable = true)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Creation Date cannot be from future")
    private Date creationDate;

    @NotNull(message = "Cost is mandatory")
    @Embedded
    private Cost cost;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "GENRES", joinColumns = @JoinColumn(name = "GENRE_ID"))
    @Builder.Default
    private Set<String> genres = new HashSet<>();

    @OneToMany(mappedBy = "book", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<OrderedBook> orderedBooks = new HashSet<>();
}

