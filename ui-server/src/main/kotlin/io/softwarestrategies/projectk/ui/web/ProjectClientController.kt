package io.softwarestrategies.projectk.ui.web

import io.softwarestrategies.projectk.ui.data.ProjectDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.client.RestClient

@Controller
class ProjectClientController {

    @Value("\${resourceserver.api.project.url:http://localhost:7070/api/projects/}")
    private val projectApiUrl: String? = null

    @Autowired
    private val restClient: RestClient? = null

    @GetMapping("/projects")
    fun getProjects(model: Model): String {
        val projects = restClient!!
            .get()
            .uri(projectApiUrl!!)
            .retrieve().body(object : ParameterizedTypeReference<List<ProjectDto?>?>() {});
        model.addAttribute("projects", projects)
        return "projects"
    }

    @GetMapping("/addproject")
    fun addNewProject(model: Model): String {
        model.addAttribute("project", ProjectDto(0L, ""))
        return "addproject"
    }

    @PostMapping("/projects")
    fun saveProject(project: ProjectDto, model: Model): String {
        return try {
            restClient!!.post()
                .uri(projectApiUrl!!)
                .body(project)
                .retrieve()
                .body(Void::class.java);
            "redirect:/projects"
        } catch (e: Exception) {
            model.addAttribute("msg", e.message)
            "addproject"
        }
    }
}
