package net.chrissearle.testsecurity

import mu.KotlinLogging
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}

@Service
class UserDetailsService : ReactiveUserDetailsService {

    // Create a dummy user details service that will take any username with a password of "password"
    // If username is "admin" - set ADMIN and USER roles
    // If username is "user" - set USER role
    override fun findByUsername(username: String?): Mono<UserDetails> {
        logger.debug { "findByUsername $username" }

        if (username == null) {
            throw BadCredentialsException("Missing Credentials")
        }

        val roles = when (username) {
            "admin" -> arrayOf("ADMIN", "USER")
            "user" -> arrayOf("USER")
            else -> emptyArray()
        }

        logger.debug { "roles $roles" }

        return Mono.just(User.withUsername(username).password(BCryptPasswordEncoder().encode("password")).roles(*roles).build())
    }
}
