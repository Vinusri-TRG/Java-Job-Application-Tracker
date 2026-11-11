
package JobTrackerWeb;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    // Get applications belonging to a particular user
    List<JobApplication> findAllByUser(User user);

    // Find an application only if it belongs to that user
    Optional<JobApplication> findByIdAndUser(Long id, User user);
}
