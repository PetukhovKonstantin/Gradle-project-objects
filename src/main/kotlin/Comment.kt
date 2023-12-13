class CommentNotFoundException(commentId: Int) : RuntimeException("Comment with id $commentId not found")
class CommentDeletedException(commentId: Int) : RuntimeException("Comment with id $commentId has been deleted")
class CommentRestoreException(commentId: Int) : RuntimeException("Comment with id $commentId hasn't been deleted")

data class Comment(
    val id: Int,
    val parentId: Int,
    val fromId: Int,
    val date: Long,
    val text: String,
    val replyToUser: Int = 0,
    val replyToComment: Int = 0,
    val attachments: Array<Attachment>? = null,
    val parentsStack: Array<Int>? = null,
    val isDeleted : Boolean = false
)