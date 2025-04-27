package dev.alejo.fastui.ui.chat.all

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.alejo.fastui.R
import dev.alejo.fastui.ui.theme.ChatPurple
import dev.alejo.fastui.ui.theme.ChatPurpleLight
import dev.alejo.fastui.ui.theme.ChatTabsBackground

@Composable
fun AllChatsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp),
        topBar = { ChatsTopAppBar(modifier = Modifier.fillMaxWidth()) },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = ChatPurple,
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_circle_dash),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) { innerPadding ->
        AllChatsContent(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
fun AllChatsContent(modifier: Modifier = Modifier) {
    val chatUsers = listOf<ChatUser>(
        ChatUser(
            name = "Maria",
            image = R.drawable.img0,
            contentType = ChatContentType.Message("Hola"),
            lastTime = "11:30 am",
            unreadMessages = 4,
            pinned = true
        ),
        ChatUser(
            name = "Ana",
            image = R.drawable.img1,
            contentType = ChatContentType.Message("Cómo vas?"),
            lastTime = "10:50 am",
            unreadMessages = 2
        ),
        ChatUser(
            name = "Luz",
            image = R.drawable.img2,
            contentType = ChatContentType.Message("Ya... \uD83D\uDE09"),
            lastTime = "10:40 am"
        ),
        ChatUser(
            name = "Lili",
            image = R.drawable.img3,
            contentType = ChatContentType.Typing("Jimmy"),
            lastTime = "10:30 am"
        ),
        ChatUser(
            name = "Vale",
            image = R.drawable.img4,
            contentType = ChatContentType.Voice,
            lastTime = "10:25 am"
        ),
        ChatUser(
            name = "Rick",
            image = R.drawable.img5,
            contentType = ChatContentType.Sticker,
            lastTime = "10:20 am"
        ),
        ChatUser(
            name = "Joy",
            image = R.drawable.img6,
            contentType = ChatContentType.Sticker,
            lastTime = "10:15 am"
        ),
        ChatUser(
            name = "Paula",
            image = R.drawable.img7,
            contentType = ChatContentType.Sticker,
            lastTime = "10:10 am"
        ),
        ChatUser(
            name = "Rick",
            image = R.drawable.img8,
            contentType = ChatContentType.Sticker,
            lastTime = "10:05 am"
        ),
        ChatUser(
            name = "Andy",
            image = R.drawable.img9,
            contentType = ChatContentType.Sticker,
            lastTime = "10:00 am"
        ),
        ChatUser(
            name = "Jaime",
            image = R.drawable.img5,
            contentType = ChatContentType.Sticker,
            lastTime = "09:00 am"
        ),
        ChatUser(
            name = "Luz",
            image = R.drawable.img2,
            contentType = ChatContentType.Message("Listo!!"),
            lastTime = "4 Apr"
        ),
        ChatUser(
            name = "Mark",
            image = R.drawable.img10,
            contentType = ChatContentType.Message("No te preocupes"),
            lastTime = "3 Mar"
        ),
        ChatUser(
            name = "Patty",
            image = R.drawable.img11,
            contentType = ChatContentType.Message("Vale"),
            lastTime = "15 Feb"
        ),
        ChatUser(
            name = "Andrew",
            image = R.drawable.img12,
            contentType = ChatContentType.Message("Ya..."),
            lastTime = "20 Jan"
        ),
        ChatUser(
            name = "Omar",
            image = R.drawable.img13,
            contentType = ChatContentType.Message("Ok ☺\uFE0F"),
            lastTime = "10 Jan"
        ),
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Tabs(Modifier.fillMaxWidth().padding(horizontal = 16.dp))
        LazyColumn {
            items(chatUsers.size) { index ->
                ChatItem(
                    modifier = Modifier.fillMaxWidth(),
                    chatUser = chatUsers[index]
                )
            }
        }
    }
}

@Composable
fun ChatItem(
    modifier: Modifier = Modifier,
    chatUser: ChatUser
) {
    Row(
        modifier = modifier.clickable {  }.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(chatUser.image),
            contentDescription = null,
            modifier = Modifier
                .height(56.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = chatUser.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(Modifier.width(4.dp))
                if (chatUser.pinned) {
                    Icon(
                        painter = painterResource(R.drawable.ic_pin),
                        contentDescription = null,
                        tint = ChatPurple,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            when (chatUser.contentType) {
                is ChatContentType.Message -> {
                    Text(
                        text = chatUser.contentType.message,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }

                ChatContentType.Sticker -> {
                    Text(
                        text = "Sticker",
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                }

                is ChatContentType.Typing -> {
                    Text(
                        text = "${chatUser.contentType.userName} is typing...",
                        fontWeight = FontWeight.SemiBold,
                        color = ChatPurpleLight
                    )
                }

                ChatContentType.Voice -> {
                    Row() {
                        Icon(
                            painter = painterResource(R.drawable.ic_mic),
                            contentDescription = null,
                            tint = ChatPurple,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "Voice message",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
        if(chatUser.unreadMessages > 0) {
            Box(
                modifier = Modifier.size(24.dp).clip(CircleShape).background(ChatPurple).padding(2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = chatUser.unreadMessages.toString(),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Text(
                text = "11:30 am",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun Tabs(modifier: Modifier = Modifier) {
    val tabs = listOf<TabItem>(
        TabItem(label = "All Chats", true),
        TabItem(label = "Groups", false),
        TabItem(label = "Contacts", false)
    )
    Row(
        modifier = modifier
            .padding(horizontal = 4.dp)
            .padding(top = 16.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(ChatTabsBackground)
    ) {
        tabs.forEach { tabData ->
            Text(
                text = tabData.label,
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(50.dp))
                    .background(if (tabData.isSelected) ChatPurple else ChatTabsBackground)
                    .padding(16.dp),
                fontSize = 16.sp,
                color = if (tabData.isSelected) Color.White else Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ChatsTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text("Hello,", color = Color.Gray)
            Spacer(Modifier.height(4.dp))
            Text("Jimmy", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
        Row {
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
                    painter = painterResource(R.drawable.ic_search),
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
                    painter = painterResource(R.drawable.ic_menu_dots),
                    contentDescription = null,
                    tint = ChatPurpleLight
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AllChatsScreenPreview() {
    AllChatsScreen()
}

data class TabItem(
    val label: String,
    val isSelected: Boolean
)

data class ChatUser(
    val name: String,
    @DrawableRes val image: Int,
    val contentType: ChatContentType,
    val lastTime: String,
    val pinned: Boolean = false,
    val unreadMessages: Int = 0
)

sealed class ChatContentType() {
    data class Message(val message: String) : ChatContentType()
    data class Typing(val userName: String) : ChatContentType()
    data object Voice : ChatContentType()
    data object Sticker : ChatContentType()
}