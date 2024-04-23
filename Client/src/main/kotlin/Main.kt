import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    var login = remember{mutableStateOf("")}
    var password = remember{mutableStateOf("")}

    var client = Client()

    Column(){
        Row {
            Text("Логин: ")
            TextField(value = login.value, onValueChange = {newText -> login.value = newText})
        }
        Row {
            Text("Пароль: ")
            TextField(value = password.value,
                singleLine = true,
                placeholder = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {newText -> password.value = newText})
        }

        Row {
            Button(onClick = {
                client.start()
                client.push("hello from new client")//login and password
                text = client.pull().toString()
                client.finish()
            }) {
                Text("Войти")
            }

            Button(onClick = {
                text = "Логин"
            }) {
                Text("Регистрация")
            }
        }
        Row{
            Text(text)
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
