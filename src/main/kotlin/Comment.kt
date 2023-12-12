data class Comment(
    val id: Int,
    val postId: Int,
    val fromId: Int,
    val date: Long,
    val text: String,
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: Array<Attachment>? = null,
    val parentsStack: Array<Int>? = null
)