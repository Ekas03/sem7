import java.util.*

class Memento(val state: String)

class Originator {
    var state: String? = null

    fun createMemento(): Memento {
        return Memento(state!!)
    }

    fun setMemento(memento: Memento) {
        state = memento.state
    }
}

class Caretaker {
    val statesList = ArrayList<Memento>()

    fun addMemento(m: Memento) {
        statesList.add(m)
    }

    fun getMemento(index: Int): Memento {
        return statesList[index]
    }
}

class TextEditor {
    private val originator = Originator()
    private val caretaker = Caretaker()

    fun addText(text: String) {
        originator.state = text
        caretaker.addMemento(originator.createMemento())
    }

    fun editText(newText: String) {
        originator.state = newText
        caretaker.addMemento(originator.createMemento())
    }

    fun watchText(): String {
        return "Текст сейчас: ${originator.state}"
    }

    fun cancel(): String {
        if (caretaker.statesList.size > 1) {
            caretaker.statesList.removeAt(caretaker.statesList.size - 1)
            val previousMemento = caretaker.getMemento(caretaker.statesList.size - 1)
            originator.setMemento(previousMemento)
        } else {
            println("Нельзя отменить!")
        }
        return watchText()
    }

    fun runCommand(command: String) {
        val args = command.split(" ")
        when (args[0]) {
            "add" -> addText(args.subList(1, args.size).joinToString(" "))
            "edit" -> editText(args.subList(1, args.size).joinToString(" "))
            "watch" -> println(watchText())
            "cancel" -> println(cancel())
            else -> println("Неизвестная команда: $command")
        }
    }
}

fun main() {
    val textEditor = TextEditor()

    textEditor.runCommand("add Hi")
    textEditor.runCommand("edit Hello")
    textEditor.runCommand("watch")
    textEditor.runCommand("cancel")
    textEditor.runCommand("watch")
}
