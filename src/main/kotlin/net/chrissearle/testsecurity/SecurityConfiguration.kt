package net.chrissearle.testsecurity

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.config.web.server.invoke

@EnableWebFluxSecurity
class SecurityConfiguration {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain = http {
        csrf { disable() }
        authorizeExchange {
            authorize("/api/admin/**", hasAuthority("ADMIN"))
            authorize("/api/open/**", permitAll)
            authorize("/api/**", authenticated)
            authorize("/**", permitAll)
        }
        formLogin {}
        logout {}
    }
}