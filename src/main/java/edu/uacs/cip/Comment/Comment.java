package edu.uacs.cip.Comment;

import edu.uacs.cip.Feedback.Feedback;
import edu.uacs.cip.User.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;


}