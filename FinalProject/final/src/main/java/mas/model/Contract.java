package mas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Start Date is mandatory")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past(message = "Start Date cannot be from future")
    private Date startDate;

    @NotNull(message = "End Date is mandatory")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;


    @ManyToOne
    @JoinColumn(name = "author_id" , nullable = false)
    @NotNull
    private Author author;

    @ManyToOne
    @JoinColumn(name = "publishing_house_id", nullable = false)
    @NotNull
    private PublishingHouse publishingHouse;
}
