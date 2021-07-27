package com.cubetiqs.fusion

import com.vaadin.flow.component.dependency.NpmPackage
import com.vaadin.flow.component.page.AppShellConfigurator
import com.vaadin.flow.server.PWA
import com.vaadin.flow.theme.Theme
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
@Theme(value = "fusion")
@PWA(name = "CUBETIQ Fusion", shortName = "Fusion", offlineResources = ["images/logo.png"])
@NpmPackage.Container(
    NpmPackage(value = "@fontsource/roboto", version = "4.5.0"),
    NpmPackage(value = "@adobe/lit-mobx", version = "2.0.0-rc.4"),
    NpmPackage(value = "mobx", version = "^6.1.5"),
    NpmPackage(value = "line-awesome", version = "1.3.0"),
)
class Application : SpringBootServletInitializer(), AppShellConfigurator

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}