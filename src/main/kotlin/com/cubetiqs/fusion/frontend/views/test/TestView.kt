package com.cubetiqs.fusion.frontend.views.test

import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.auth.AnonymousAllowed

@Route("/test")
@AnonymousAllowed
class TestView : Div() {
    init {
        add(
            Button("Hello World")
        )
    }
}