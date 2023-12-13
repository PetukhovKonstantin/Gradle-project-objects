import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NotesServiceTest {
    @Before
    fun clearBeforeTest() {
        NotesService.clear()
        NotesService.clearComments()
    }

    @Test
    fun testAdd() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        Assert.assertEquals(1, note.id)
    }

    @Test
    fun testDelete() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        val result = NotesService.delete(note.id)
        Assert.assertEquals(true, result)
    }

    @Test
    fun testEdit() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        NotesService.edit(note.copy(text = "Test 2"))
        Assert.assertEquals("Test 2", NotesService.getById(note.id).text)
    }

    @Test(expected = NoteNotFoundException::class)
    fun shouldThrowNoteNotFoundException() {
        NotesService.getById(1)
    }

    @Test(expected = NoteDeletedException::class)
    fun shouldThrowNoteDeletedException() {
        val note = NotesService.add(Note(1, "Test", "Test 1", isDeleted = true))
        NotesService.delete(1)
    }

    @Test
    fun testCreateComment() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        val comment = NotesService.createComment(note.id, Comment(1, note.id, 1, 1, "Test"))
        Assert.assertEquals(1, comment.id)
    }

    @Test
    fun testDeleteComment() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        val comment = NotesService.createComment(note.id, Comment(2, note.id, 1, 1, "Test"))
        val result = NotesService.deleteComment(comment.id)
        Assert.assertEquals(true, result)
    }

    @Test
    fun testEditComment() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        val comment = NotesService.createComment(note.id, Comment(2, note.id, 1, 1, "Test"))
        NotesService.editComment(comment.copy(text = "Test 2"))
        Assert.assertEquals("Test 2", NotesService.getCommentById(comment.id).text)
    }

    @Test
    fun testRestoreComment() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        val comment = NotesService.createComment(note.id, Comment(2, note.id, 1, 1, "Test"))
        val result = if (NotesService.deleteComment(comment.id)) NotesService.restoreComment(comment.id) else false
        Assert.assertEquals(true, result)
    }


    @Test(expected = CommentNotFoundException::class)
    fun shouldThrowCommentNotFoundException() {
        NotesService.getCommentById(1)
    }

    @Test(expected = CommentDeletedException::class)
    fun shouldThrowCommentDeletedException() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        val comment = NotesService.createComment(note.id, Comment(1, note.id, 1, 1, "Test", isDeleted = true))
        NotesService.deleteComment(1)
    }

    @Test(expected = CommentRestoreException::class)
    fun shouldThrowCommentRestoreException() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        val comment = NotesService.createComment(note.id, Comment(2, note.id, 1, 1, "Test"))
        NotesService.restoreComment(comment.id)
    }

    @Test
    fun testGet() {
        NotesService.add(Note(1, "Test", "Test 1"))
        NotesService.add(Note(2, "Test", "Test 1"))
        Assert.assertEquals(2, NotesService.get().count())
    }

    @Test
    fun testGetById() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        Assert.assertEquals(NotesService.getById(note.id), note)
    }

    @Test
    fun testGetComments() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        NotesService.createComment(note.id, Comment(1, note.id, 1, 1, "Test"))
        NotesService.createComment(note.id, Comment(2, note.id, 1, 1, "Test 2"))
        Assert.assertEquals(2, NotesService.getComments().count())
    }

    @Test
    fun testGetCommentById() {
        val note = NotesService.add(Note(1, "Test", "Test 1"))
        val comment = NotesService.createComment(note.id, Comment(1, note.id, 1, 1, "Test"))
        Assert.assertEquals(NotesService.getCommentById(comment.id), comment)
    }
}