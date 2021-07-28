package com.cubetiqs.fusion.frontend.views.test

import com.cubetiqs.cui.component.dialog.ConfirmDialog
import com.cubetiqs.cui.component.element.CDiv
import com.cubetiqs.cui.component.element.CSizeBox
import com.cubetiqs.cui.dsl.*
import com.cubetiqs.cui.inject.tinymce.TinyMceInjectable
import com.cubetiqs.cui.notification.Alert
import com.cubetiqs.cui.style.BoxShadow
import com.cubetiqs.cui.style.Height
import com.cubetiqs.cui.style.Padding
import com.vaadin.flow.component.AttachEvent
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.router.Route
import com.vaadin.flow.server.auth.AnonymousAllowed
import java.util.*

@Route("/test")
@AnonymousAllowed
class TestView : CDiv(), TinyMceInjectable {
    private val container = createDiv { }

    private fun addAnswer() {
        val editorId = "editor-${UUID.randomUUID()}"
        val editor = createDiv {
            setId(editorId)
            addDiv {

                setStyle(BoxShadow())
                setStyle(Padding.All("10px"))

                addHorizontalLayout {
                    addButton {
                        width = "10%"
                        var checked = false
                        addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE)
                        icon = createIcon(VaadinIcon.CHECK_CIRCLE_O) { }

                        addClickListener {
                            this.icon = if (checked) {
                                checked = false
                                createIcon(VaadinIcon.CHECK_CIRCLE_O) { }
                            } else {
                                checked = true
                                createIcon(VaadinIcon.CHECK_CIRCLE) { }
                            }
                        }
                    }

                    val editorContent = createTinyMceEditor {
                        width = "80%"
                        setId("$editorId-content")
                        enabledMathMode()
                        isInlineMode(true)
                    }

                    add(editorContent)

                    addButton {
                        width = "10%"
                        addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE)
                        icon = createIcon(VaadinIcon.TRASH) {
                            color = "red"
                        }

                        addClickListener {
                            var isConfirm = false
                            val ct = editorContent.currentValue
                            if (ct.isNotBlank()) {
                                val confirm = ConfirmDialog(
                                    "Are you sure?",
                                    "Really want to delete the answering content...",
                                    "Okay"
                                ) {
                                    removeElement(editorId)
                                }

                                add(confirm)

                                confirm.open()

                                isConfirm = true
                            }

                            if (!isConfirm) {
                                removeElement(editorId)
                            }
                        }
                    }
                }
            }

            add(CSizeBox(height = 10.0))
        }

        container.add(editor)
    }

    override fun onAttach(attachEvent: AttachEvent?) {
        super.onAttach(attachEvent)

        init()
    }

    private fun init() {
        addBr(3)

        addHorizontalLayout {
            setWidthFull()

            addDiv {
                addDiv {
                    withStyle {
                        BoxShadow()
                    }

                    withStyle {
                        Padding.All("10px")
                    }

                    minHeight = "150px"
                    width = "500px"
                    addTinyMceEditor {
                        enabledMathMode()
                        isInlineMode(true)
                        getEditorElement().setStyle(Height.Min("150px"))
                    }
                }

                addBr()

                addDiv {
                    width = "500px"
                    setStyle(Padding.All("10px"))

                    addHorizontalLayout {
                        addButton {
                            text = "Add new answer"

                            addClickListener {
                                addAnswer()
                            }
                        }

                        addSizedBox {
                            width = "10px"
                        }

                        addButton {
                            text = "See Answer Count"

                            addClickListener {
                                Alert.Companion.show("You clicked see answer...")
                            }
                        }
                    }

                    addBr(2)

                    add(container)
                }

            }

            justifyContentMode = FlexComponent.JustifyContentMode.CENTER
        }
    }
}