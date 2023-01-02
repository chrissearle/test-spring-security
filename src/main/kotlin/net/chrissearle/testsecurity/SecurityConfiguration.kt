package net.chrissearle.testsecurity

import mu.KotlinLogging
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.config.web.server.invoke

private val logger = KotlinLogging.logger {}

@EnableWebFluxSecurity
class SecurityConfiguration {

    // Configure security using the ServerHttpSecurityDsl
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
    }.also { logger.debug { "Security configured" } }

}