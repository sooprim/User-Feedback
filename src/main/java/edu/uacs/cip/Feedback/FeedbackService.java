package edu.uacs.cip.Feedback;

import edu.uacs.cip.User.User;
import edu.uacs.cip.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class FeedbackService {

    String INVALID_LENGTH = "Invalid Feedback rating";

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Transactional
    public Feedback createFeedback(Feedback feedback) throws Exception {
        if (feedback.getRating() < 0 || feedback.getRating() > 10) {
            throw new Exception(INVALID_LENGTH);
        }
        User user = feedback.getUser();
        if (user != null) {
            Optional<User> existingUser = userRepository.findById(user.getId());
            if (existingUser.isPresent()) {
                feedback.setUser(existingUser.get());
            } else {
                userRepository.save(user);
            }
        }
        return feedbackRepository.save(feedback);
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackRepository.findById(id);
    }

    public Feedback updateFeedback(Long id, Feedback feedbackDetails) throws Exception {
        AtomicBoolean ratingInvalid = new AtomicBoolean(false);
        return feedbackRepository.findById(id)
                .map(feedback -> {
                    feedback.setDescription(feedbackDetails.getDescription());
                    feedback.setUser(feedbackDetails.getUser());
                    int rating = feedbackDetails.getRating();
                    if (rating < 0 || rating > 10) {
                        ratingInvalid.set(true);
                        throw new IllegalArgumentException(INVALID_LENGTH);
                    }
                    feedback.setRating(rating);
                    return feedbackRepository.save(feedback);
                })
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found"));
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
