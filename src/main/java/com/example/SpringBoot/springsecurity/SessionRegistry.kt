package com.example.springboot.springsecurity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.session.security.SpringSessionBackedSessionRegistry


/**
 * 配置提供 SessionRegistry 的Bean注入
 */

@Configuration
open class SessionRegistry {        //todo   Classes annotated with '@Configuration' could be implicitly subclassed(隐式子类化) and must not be final

    @Bean
    open fun sessionRegistry() = SessionRegistryImpl()

//    @Bean
//    open fun springSessionRegistry() = SpringSessionBackedSessionRegistry(redisOperationsSessionRepository)  //todo 如何使用 SpringSessionBackedSessionRegistry
}