object NotesService {
    private var notes = mutableListOf<Note>()
    private var lastId = 0
    private var comments = mutableListOf<Comment>()
    private var lastIdComment = 0


    fun add(note: Note): Note {
        notes.add(note.copy(id = ++lastId))
        return notes.last()
    }

    fun delete(noteId: Int): Boolean {
        val note = getById(noteId)
        if (note.isDeleted) {
            throw NoteDeletedException(noteId)
        }
        val index = notes.indexOf(note)
        notes.remove(note)
        notes.add(index, note.copy(isDeleted = true))
        return true
    }

    fun edit(note: Note): Boolean {
        val noteInList = getById(note.id)
        if (noteInList.isDeleted) {
            throw NoteDeletedException(noteInList.id)
        }
        val index = notes.indexOf(noteInList)
        notes.removeAt(index)
        notes.add(index, note.copy())
        return true
    }

    fun createComment(noteId: Int, comment: Comment): Comment {
        val note = getById(noteId)
        comments.add(comment.copy(id = ++lastIdComment, parentId = noteId))
        return comments.last()
    }

    fun deleteComment(commentId: Int): Boolean {
        val comment = getCommentById(commentId)
        if (comment.isDeleted) {
            throw CommentDeletedException(commentId)
        }
        val index = comments.indexOf(comment)
        comments.remove(comment)
        comments.add(index, comment.copy(isDeleted = true))
        return true
    }

    fun editComment(comment: Comment): Boolean {
        val commentInList = getCommentById(comment.id)
        if (commentInList.isDeleted) {
            throw CommentDeletedException(commentInList.id)
        }
        val index = comments.indexOf(commentInList)
        comments.removeAt(index)
        comments.add(index, comment.copy())
        return true
    }

    fun restoreComment(commentId: Int): Boolean {
        val comment = getCommentById(commentId)
        if (!comment.isDeleted) {
            throw CommentRestoreException(commentId)
        }
        val index = comments.indexOf(comment)
        comments.removeAt(index)
        comments.add(index, comment.copy(isDeleted = false))
        return true
    }

    fun get(): List<Note> = notes.filter { !it.isDeleted }

    fun getById(noteId: Int): Note = notes.find { it.id == noteId } ?: throw NoteNotFoundException(noteId)

    fun getComments(noteId: Int): List<Comment> = comments.filter { !it.isDeleted && it.parentId == noteId }

    fun getCommentById(commentId: Int): Comment = comments.find { it.id == commentId } ?: throw CommentNotFoundException(commentId)

    fun clear() {
        notes.clear()
        lastId = 0
    }

    fun clearComments() {
        comments.clear()
        lastIdComment = 0
    }
}