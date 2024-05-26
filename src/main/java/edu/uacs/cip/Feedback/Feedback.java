package edu.uacs.cip.Feedback;

import edu.uacs.cip.User.User;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name="feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private int rating;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
