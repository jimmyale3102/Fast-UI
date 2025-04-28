package dev.alejo.fastui.ui.chat.conversation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.alejo.fastui.ui.theme.ChatPurpleLight
import java.sql.Timestamp

object Provider {

    private val messages = listOf<Message>(
        Message(
            sender = Sender.Friend,
            date = Timestamp(System.currentTimeMillis().minus(700)),
            messageType = MessageType.Text("Hey \uD83D\uDC4B")
        ),
        Message(
            sender = Sender.Friend,
            date = Timestamp(System.currentTimeMillis().minus(600)),
            messageType = MessageType.Text("Are you available for a new UI Project")
        ),
        Message(
            sender = Sender.User,
            date = Timestamp(System.currentTimeMillis().minus(500)),
            messageType = MessageType.Text("Hello")
        ),
        Message(
            sender = Sender.User,
            date = Timestamp(System.currentTimeMillis().minus(400)),
            messageType = MessageType.Text("Yes, have some space for the new task")
        ),
        Message(
            sender = Sender.Friend,
            date = Timestamp(System.currentTimeMillis().minus(300)),
            messageType = MessageType.Text("Cool, should I share the details now?")
        ),
        Message(
            sender = Sender.User,
            date = Timestamp(System.currentTimeMillis().minus(200)),
            messageType = MessageType.Text("Yes Sure, please")
        ),
        Message(
            sender = Sender.Friend,
            date = Timestamp(System.currentTimeMillis().minus(100)),
            messageType = MessageType.Text("Great, here is the SOW of the project")
        ),
        Message(
            sender = Sender.Friend,
            date = Timestamp(System.currentTimeMillis()),
            messageType = MessageType.File("UI Brief.docx", "269.18 KB")
        ),

        )

    fun getMessages() = messages.sortedBy { message -> message.date }.reversed()
}

data class Message(
    val sender: Sender,
    val date: Timestamp,
    val messageType: MessageType
)

sealed class MessageType {
    data class Text(val message: String) : MessageType()
    data class File(val fileName: String, val size: String) : MessageType()
}

sealed class Sender {
    data object User : Sender()
    data object Friend : Sender()

    @SuppressLint("ModifierFactoryExtensionFunction")
    fun getSenderShape(): Modifier {
        return when (this@Sender) {
            is User -> {
                Modifier
                    .padding(top = 8.dp, bottom = 8.dp, start = 80.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 25.dp,
                            topEnd = 0.dp,
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        )
                    )
            }

            is Friend -> {
                Modifier
                    .padding(top = 8.dp, bottom = 8.dp, end = 80.dp)
                    .background(
                        color = ChatPurpleLight,
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 25.dp,
                            bottomStart = 25.dp,
                            bottomEnd = 25.dp
                        )
                    )
            }
        }
    }
}