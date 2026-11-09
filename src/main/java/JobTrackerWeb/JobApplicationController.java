package JobTrackerWeb;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class JobApplicationController {

    private final JobApplicationRepository repository;

    public JobApplicationController(JobApplicationRepository repository) {
        this.repository = repository;
    }

    // Dashboard details
    private void addDashboardDetails(Model model) {

        var applications = repository.findAll();

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
    public String home(Model model) {

        model.addAttribute("application", new JobApplication());

        addDashboardDetails(model);

        return "index";
    }

    // Add new application with validation
    @PostMapping("/applications/add")
    public String addApplication(
            @Valid @ModelAttribute("application") JobApplication application,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            addDashboardDetails(model);

            return "index";
        }

        if ("Not Scheduled".equals(application.getInterviewStatus())) {
            application.setInterviewDate(null);
        }

        repository.save(application);

        redirectAttributes.addFlashAttribute(
                "message", "Application saved successfully!");

        return "redirect:/";
    }

    // Delete application
    @PostMapping("/applications/delete/{id}")
    public String deleteApplication(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        repository.deleteById(id);

        redirectAttributes.addFlashAttribute(
                "message", "Application deleted successfully!");

        return "redirect:/";
    }

    // Open edit page
    @GetMapping("/applications/edit/{id}")
    public String editApplication(
            @PathVariable Long id,
            Model model) {

        JobApplication application = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));

        model.addAttribute("application", application);
        model.addAttribute("applicationId", id);

        return "edit";
    }

    // Update existing application with validation
    @PostMapping("/applications/update/{id}")
    public String updateApplication(
            @PathVariable Long id,
            @Valid @ModelAttribute("application") JobApplication updatedApplication,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            model.addAttribute("applicationId", id);

            return "edit";
        }

        JobApplication existingApplication = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));

        existingApplication.setCompanyName(
                updatedApplication.getCompanyName());

        existingApplication.setJobRole(
                updatedApplication.getJobRole());

        existingApplication.setApplicationDate(
                updatedApplication.getApplicationDate());

        existingApplication.setStatus(
                updatedApplication.getStatus());

        existingApplication.setInterviewStatus(
                updatedApplication.getInterviewStatus());

        if ("Not Scheduled".equals(
                updatedApplication.getInterviewStatus())) {

            existingApplication.setInterviewDate(null);

        } else {

            existingApplication.setInterviewDate(
                    updatedApplication.getInterviewDate());
        }

        repository.save(existingApplication);

        redirectAttributes.addFlashAttribute(
                "message", "Application updated successfully!");

        return "redirect:/";
    }

    // Temporary test URL
    @GetMapping("/test")
    public String testPage() {

        return "index";
    }
}