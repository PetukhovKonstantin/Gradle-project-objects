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
        val note = getById(noteId) ?: throw NoteNotFoundException(noteId)
        if (note.isDeleted) {
            throw NoteDeletedException(noteId)
        }
        val index = notes.indexOf(note)
        notes.remove(note)
        notes.add(index, note.copy(isDeleted = true))
        return true
    }

    fun edit(note: Note): Boolean {
        if (note.isDeleted) {
            throw NoteDeletedException(note.id)
        }
        val index = notes.indexOf(note)
        notes.remove(note)
        notes.add(index, note.copy())
        return true
    }

    fun createComment(noteId: Int, comment: Comment): Comment {
        val note = getById(noteId) ?: throw CommentNotFoundException(noteId)
        comments.add(comment.copy(id = ++lastIdComment, parentId = noteId))
        return comment
    }

    fun deleteComment(commentId: Int): Boolean {
        val comment = getCommentById(commentId) ?: throw CommentNotFoundException(commentId)
        if (comment.isDeleted) {
            throw CommentDeletedException(commentId)
        }
        val index = comments.indexOf(comment)
        comments.remove(comment)
        comments.add(index, comment.copy(isDeleted = true))
        return true
    }

    fun editComment(comment: Comment): Boolean {
        if (comment.isDeleted) {
            throw CommentDeletedException(comment.id)
        }
        val index = comments.indexOf(comment)
        comments.remove(comment)
        comments.add(index, comment.copy())
        return true
    }

    fun restoreComment(commentId: Int): Boolean {
        val comment = getCommentById(commentId) ?: throw CommentNotFoundException(commentId)
        if (!comment.isDeleted) {
            throw CommentRestoreException(commentId)
        }
        val index = comments.indexOf(comment)
        comments.remove(comment)
        comments.add(index, comment.copy(isDeleted = false))
        return true
    }

    fun get(): List<Note> = notes.filter { !it.isDeleted };

    fun getById(noteId: Int): Note? = notes.find { it.id == noteId }

    fun getComments(): List<Comment> = comments.filter { !it.isDeleted };

    fun getCommentById(commentId: Int): Comment? = comments.find { it.id == commentId }

    fun clear() {
        notes.clear()
        lastId = 0
    }

    fun clearComments() {
        comments.clear()
        lastIdComment = 0
    }
}