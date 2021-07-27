package com.cubetiqs.fusion.frontend.views.user

import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.auth.AnonymousAllowed

@Route("/users")
@PageTitle("All Users")
@AnonymousAllowed
class UserView : Div() {
    private fun generateUsers(): Collection<User> {
        val users = mutableListOf<User>()
        for (i in 1..1000000) {
            users.add(User(i, "user-${"$i".padStart(6, '0')}", "MY USER - $i", "$i".padEnd(9, '0')))
        }

        return users
    }

    init {
        val grid = Grid(User::class.java)
        grid.setItems(generateUsers())
        add(grid)
    }

    data class User(val id: Int, val username: String, val name: String, val phone: String)
}