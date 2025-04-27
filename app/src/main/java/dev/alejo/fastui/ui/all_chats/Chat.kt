package dev.alejo.fastui.ui.all_chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.fastui.R
import dev.alejo.fastui.ui.chat.conversation.Message
import dev.alejo.fastui.ui.chat.conversation.MessageProvider
import dev.alejo.fastui.ui.chat.conversation.MessageType
import dev.alejo.fastui.ui.chat.conversation.Sender
import dev.alejo.fastui.ui.theme.ChatButtonPurpleLight
import dev.alejo.fastui.ui.theme.ChatOnline
import dev.alejo.fastui.ui.theme.ChatPurple
import dev.alejo.fastui.ui.theme.ChatPurpleLight
import dev.alejo.fastui.ui.theme.ChatTabsBackground

@Composable
fun Chat() {
    Scaffold(
        topBar = { ChatTopBar(Modifier.fillMaxWidth()) },
        bottomBar = {
            TextInput(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        },
        containerColor = ChatPurple
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(ChatPurple, RoundedCornerShape(topEnd = 32.dp))
        ) {
            val messages = MessageProvider.getMessages()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                reverseLayout = true
            ) {
                items(messages.size) { index ->
                    MessageItem(message = messages[index])
                }
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Box(Modifier.fillMaxWidth()) {
        when (message.messageType) {
            is MessageType.File -> {
                Row(
                    modifier = message.sender.getSenderShape(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_file),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Column {
                        Text(message.messageType.fileName, color = Color.White, fontSize = 18.sp)
                        Text(message.messageType.size, color = Color.White.copy(alpha = 0.6f))
                    }
                    IconButton(
                        modifier = Modifier.background(ChatButtonPurpleLight, CircleShape),
                        onClick = { }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_download),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }

            is MessageType.Text -> {
                Box(
                    modifier = message.sender
                        .getSenderShape()
                        .align(
                            if (message.sender is Sender.User) Alignment.CenterEnd else Alignment.CenterStart
                        )
                ) {
                    Text(
                        message.messageType.message,
                        fontSize = 18.sp,
                        color = if (message.sender is Sender.User) Color.Black else Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun TextInput(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier
                .height(56.dp)
                .weight(1f)
                .background(Color.White, CircleShape)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_mic),
                contentDescription = null,
                tint = ChatPurpleLight
            )
            Spacer(
                Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxHeight()
                    .width(1.dp)
                    .background(ChatPurpleLight)
            )
            Text(text = "Hey bro", modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_clip),
                contentDescription = null,
                tint = ChatPurpleLight
            )
        }
        IconButton(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(Color.White),
            onClick = { }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_play),
                contentDescription = null,
                tint = ChatPurple,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun ChatTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    0f to Color.White,
                    0.5f to Color.White,
                    0.5f to ChatPurple,
                    1f to ChatPurple,
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UserData(
            Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(ChatPurple, RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
        )
        Box(Modifier.fillMaxHeight()) {
            ChatOptions(
                Modifier
                    .fillMaxHeight()
                    .background(Color.White, RoundedCornerShape(bottomStart = 32.dp))
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

@Composable
fun UserData(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier.clip(CircleShape),
            onClick = { }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null,
                tint = ChatTabsBackground
            )
        }
        Box(modifier = Modifier.padding(end = 8.dp), contentAlignment = Alignment.TopEnd) {
            Image(
                painter = painterResource(R.drawable.img0),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
            Box(
                Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(
                        ChatOnline
                    )
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = CircleShape
                    )
            )
        }
        Column {
            Text("Larry Machigo", fontWeight = FontWeight.SemiBold, color = Color.White)
            Text("Online", fontSize = 10.sp, color = Color.White)
        }
    }
}

@Composable
fun ChatOptions(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape
                ),
            onClick = { }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_video),
                contentDescription = null,
                tint = ChatPurpleLight
            )
        }
        Spacer(Modifier.width(8.dp))
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape
                ),
            onClick = { }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_phone),
                contentDescription = null,
                tint = ChatPurpleLight
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversationScreenPreview() {
    Chat()
}