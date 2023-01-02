package net.chrissearle.testsecurity

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserDetailsService : ReactiveUserDetailsService {
    override fun findByUsername(username: String?): Mono<UserDetails> {
        if (username == null) {
            throw BadCredentialsException("Missing Credentials")
        }

        val roles = when (username) {
            "admin" -> arrayOf("ADMIN", "USER")
            "user" -> arrayOf("USER")
            else -> emptyArray()
        }

        return Mono.just(User.withUsername(username).password(BCryptPasswordEncoder().encode("password")).roles(*roles).build())
    }
}
