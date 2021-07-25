package com.cubetiqs.fusion.frontend.views.test

import com.vaadin.flow.component.html.Div
import com.vaadin.flow.router.Route

@Route("/views/test")
class TestView : Div() {
    init {
        add("Hello World")
    }
}