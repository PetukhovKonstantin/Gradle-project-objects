fun main(args: Array<String>) {
    val chats = listOf(
        DirectChat(1, 1, "Test"),
        DirectChat(2, 2, "Test"),
        DirectChat(3, 3, "Test")
    )
    val messages = listOf(
        Message(1, 1, "Test", isRead = true),
        Message(2, 1, "Test", isRead = false),
        Message(3, 2, "Test", isRead = false),
        Message(4, 3, "Test", isRead = true),
    )
    ChatService.add(DirectChat(1, 1, "Test"), Message(1, 1, "Test", isRead = true))
    ChatService.createMessage(1, Message(4, 1, "Test4", isRead = true))
    ChatService.add(DirectChat(2, 1, "Test2"), Message(2, 2, "Test2", isRead = true))
    ChatService.add(DirectChat(2, 1, "Test3"), Message(3, 3, "Test3", isRead = true))
    println(ChatService.getLastMessageFromChats())

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
}