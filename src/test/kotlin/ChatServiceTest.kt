import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ChatServiceTest {
    @Before
    fun clearBeforeTest() {
        ChatService.clear()
        ChatService.clearMessages()
    }

    @Test
    fun testAdd() {
        val chat = ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        Assert.assertEquals(1, chat.id)
    }

    @Test
    fun testDelete() {
        val chat = ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        val result = ChatService.delete(chat.id)
        Assert.assertEquals(true, result)
    }

    @Test
    fun testGet() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        ChatService.add(DirectChat(2, 1, "Test2"), Message(2, 2, "Test2", isRead = true))
        Assert.assertEquals(2, ChatService.get().count())
    }

    @Test
    fun testGetById() {
        val chat = ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        Assert.assertEquals(1, chat.id)
    }

    @Test(expected = ChatNotFoundException::class)
    fun shouldThrowChatNotFoundException() {
        ChatService.getById(1)
    }

    @Test
    fun testCreateMessage() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        val msg = ChatService.createMessage(1, Message(4, 1, "Test4", isRead = true))
        Assert.assertEquals(2, msg.id)
    }

    @Test
    fun testEditMessage() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        ChatService.editMessage(Message(1, 1, "Edit test"))
        Assert.assertEquals("Edit test", ChatService.getMessageById(1).text)
    }

    @Test
    fun testDeleteMessage() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        val result = ChatService.deleteMessage(1)
        Assert.assertEquals(true, result)
    }

    @Test
    fun testGetMessageById() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        val msg = ChatService.getMessageById(1)
        Assert.assertEquals(1, msg.id)
    }

    @Test
    fun testGetUnreadChatsCount() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        ChatService.createMessage(1, Message(4, 1, "Test4", isRead = false))
        ChatService.add(DirectChat(2, 1, "Test2"), Message(2, 2, "Test2", isRead = true))
        ChatService.add(DirectChat(2, 1, "Test3"), Message(3, 3, "Test3", isRead = false))
        val unreadCount = ChatService.getUnreadChatsCount()
        Assert.assertEquals(2, unreadCount)
    }

    @Test
    fun testGetChats() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        ChatService.add(DirectChat(2, 1, "Test2"), Message(2, 2, "Test2", isRead = true))
        val result = ChatService.getChats()
        Assert.assertEquals("Список чатов:\n1: Test\n2: Test2", result)
    }

    @Test
    fun testGetLastMessageFromChats() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "TestMsg", isRead = true))
        ChatService.createMessage(1, Message(4, 1, "TestMsg2", isRead = false))
        ChatService.add(DirectChat(2, 1, "Test2"), Message(2, 2, "TestMsg3", isRead = true))
        val result = ChatService.getLastMessageFromChats()
        Assert.assertEquals("Последние сообщения чатов:\n1: Test - TestMsg2\n2: Test2 - TestMsg3", result)
    }

    @Test
    fun testGetMessagesByChat() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "TestMsg", isRead = true))
        ChatService.createMessage(1, Message(4, 1, "TestMsg2", isRead = false))
        val result = ChatService.getMessagesByChat(1)
        Assert.assertEquals("Список сообщений для чата Test:\n1: TestMsg\n2: TestMsg2", result)
    }

    @Test
    fun testGetMessagesByChatFriendIdAndCountMessages() {
        ChatService.add(DirectChat(1, 5, "Test"), Message(1, 1, "TestMsg", isRead = false))
        ChatService.createMessage(1, Message(4, 1, "TestMsg2", isRead = false))
        val result = ChatService.getMessagesByChat(5, 1)
        Assert.assertEquals("Список сообщений (кол-во: 1) для чата Test:\n1: TestMsg", result)
    }

    @Test
    fun testGetLastMessage() {
        ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
        ChatService.createMessage(1, Message(4, 1, "TestMsg2", isRead = false))
        val msg = ChatService.getLastMessage(1)
        Assert.assertEquals(2, msg.id)
    }

    @Test(expected = MessageNotFoundException::class)
    fun shouldThrowMessageNotFoundException() {
        ChatService.getMessageById(1)
    }
}