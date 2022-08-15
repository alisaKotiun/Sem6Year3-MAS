package mas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
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

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Creation Date cannot be from future")
    private Date creationDate;

    @NotNull(message = "Cost is mandatory")
    @Embedded
    private Cost cost;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "GENRE", joinColumns = @JoinColumn(name = "GENRE_ID", nullable = false))
    @Builder.Default
    @Size(min=1)
    private Set<String> genres = new HashSet<>();

    @NotBlank(message = "Picture is mandatory")
    private String picture;


    @ManyToOne
    @JoinColumn(name = "collection_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonBackReference
    private Collection collection;

    @OneToOne(mappedBy = "cover")
    @JsonBackReference
    private Collection coverOfCollection;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonManagedReference
    private Set<Book_Cart> bookCarts = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonManagedReference
    private Set<BookAuthor> bookAuthors = new HashSet<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @JsonManagedReference
    private Set<OrderedBook> orderedBooks = new HashSet<>();

}
