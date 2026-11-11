
package JobTrackerWeb;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class JobApplicationController {

    private final JobApplicationRepository repository;

    public JobApplicationController(JobApplicationRepository repository) {
        this.repository = repository;
    }

    // Dashboard details for logged-in user
    private void addDashboardDetails(Model model, User currentUser) {

        var applications = repository.findAllByUser(currentUser);

        model.addAttribute("applications", applications);
        model.addAttribute("totalApplications", applications.size());

        model.addAttribute("appliedCount",
                applications.stream()
                        .filter(a -> "Applied".equalsIgnoreCase(a.getStatus()))
                        .count());

        model.addAttribute("interviewCount",
                applications.stream()
                        .filter(a -> "Scheduled".equalsIgnoreCase(a.getInterviewStatus()))
                        .count());

        model.addAttribute("selectedCount",
                applications.stream()
                        .filter(a -> "Selected".equalsIgnoreCase(a.getStatus()))
                        .count());

        model.addAttribute("rejectedCount",
                applications.stream()
                        .filter(a -> "Rejected".equalsIgnoreCase(a.getStatus()))
                        .count());
    }

    // Display homepage
    @GetMapping("/")
    public String home(
            Model model,
            @AuthenticationPrincipal User currentUser) {

        model.addAttribute("application", new JobApplication());

        addDashboardDetails(model, currentUser);

        return "index";
    }

    // Add application
    @PostMapping("/applications/add")
    public String addApplication(
            @Valid @ModelAttribute("application") JobApplication application,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal User currentUser) {

        if (result.hasErrors()) {
            addDashboardDetails(model, currentUser);
            return "index";
        }

        if ("Not Scheduled".equals(application.getInterviewStatus())) {
            application.setInterviewDate(null);
        }

        application.setUser(currentUser);

        repository.save(application);

        redirectAttributes.addFlashAttribute(
                "message", "Application saved successfully!");

        return "redirect:/";
    }

    // Delete application
    @PostMapping("/applications/delete/{id}")
    public String deleteApplication(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser,
            RedirectAttributes redirectAttributes) {

        JobApplication application = repository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Application not found"));

        repository.delete(application);

        redirectAttributes.addFlashAttribute(
                "message", "Application deleted successfully!");

        return "redirect:/";
    }

    // Open edit page
    @GetMapping("/applications/edit/{id}")
    public String editApplication(
            @PathVariable Long id,
            Model model,
            @AuthenticationPrincipal User currentUser) {

        JobApplication application = repository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Application not found"));

        model.addAttribute("application", application);
        model.addAttribute("applicationId", id);

        return "edit";
    }

    // Update application
    @PostMapping("/applications/update/{id}")
    public String updateApplication(
            @PathVariable Long id,
            @Valid @ModelAttribute("application") JobApplication updatedApplication,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal User currentUser) {

        JobApplication existingApplication = repository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Application not found"));

        if (result.hasErrors()) {
            model.addAttribute("applicationId", id);
            return "edit";
        }

        existingApplication.setCompanyName(updatedApplication.getCompanyName());
        existingApplication.setJobRole(updatedApplication.getJobRole());
        existingApplication.setApplicationDate(updatedApplication.getApplicationDate());
        existingApplication.setStatus(updatedApplication.getStatus());
        existingApplication.setInterviewStatus(updatedApplication.getInterviewStatus());

        if ("Not Scheduled".equals(updatedApplication.getInterviewStatus())) {
            existingApplication.setInterviewDate(null);
        } else {
            existingApplication.setInterviewDate(updatedApplication.getInterviewDate());
        }

        repository.save(existingApplication);

        redirectAttributes.addFlashAttribute(
                "message", "Application updated successfully!");

        return "redirect:/";
    }
}
