package com.cubetiqs.fusion

import org.springframework.boot.autoconfigure.SpringBootApplication
import com.vaadin.flow.server.PWA
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.theme.Theme
import org.springframework.boot.SpringApplication

@SpringBootApplication
@Theme(value = "fusionmanagement")
@PWA(name = "Fusion Management", shortName = "Fusion Management", offlineResources = ["images/logo.png"])
class Application : SpringBootServletInitializer(), AppShellConfigurator

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}