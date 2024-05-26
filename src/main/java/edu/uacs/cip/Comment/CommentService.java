package edu.uacs.cip.Comment;

import edu.uacs.cip.Feedback.FeedbackRepository;
import edu.uacs.cip.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Comment> getAllComments() {

        return commentRepository.findAll();

    }

    public Comment createComment(Long feedbackId, Comment comment) {
        return feedbackRepository.findById(feedbackId)
                .map(feedback -> {
                    if (comment.getUser() != null) { // Check if comment's user is not null
                        if (userRepository.existsById(comment.getUser().getId())) {;
                            comment.setFeedback(feedback);
                            comment.setUser(userRepository.getReferenceById(comment.getUser().getId()));
                            return commentRepository.save(comment);
                        } else {
                            throw new RuntimeException("User not found");
                        }
                    } else {
                        throw new RuntimeException("Comment user cannot be null");
                    }
                }).orElseThrow(() -> new RuntimeException("Feedback not found"));
    }


    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        return commentRepository.findById(id)
                .map(comment -> {
                    comment.setText(commentDetails.getText());
                    return commentRepository.save(comment);
                }).orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
