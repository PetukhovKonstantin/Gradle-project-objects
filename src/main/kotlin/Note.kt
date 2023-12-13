class NoteNotFoundException(noteId: Int) : RuntimeException("Note with id $noteId not found")
class NoteDeletedException(noteId: Int) : RuntimeException("Note with id $noteId has been deleted")

data class Note(
    val id: Int,
    val title: String,
    val text: String,
    val privacy : Int = 0,
    val commentPrivacy : Int = 0,
    val isDeleted : Boolean = false
)
