package net.chrissearle.testsecurity

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// Test endpoints
@RestController
class TestController {
    @GetMapping("/api/admin/test")
    fun admin() = "ADMIN"

    @GetMapping("/api/open/test")
    fun open() = "OPEN"

    @GetMapping("/api/test")
    fun api() = "API"

    @GetMapping("/test")
    fun test() = "TEST"

}