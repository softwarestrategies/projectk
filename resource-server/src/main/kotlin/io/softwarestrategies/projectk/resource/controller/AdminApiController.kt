package io.softwarestrategies.projectk.resource.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminApiController {

    @GetMapping("/heartbeat")
    fun getAdminEndpointHeartbeat(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello Admin")
    }
}