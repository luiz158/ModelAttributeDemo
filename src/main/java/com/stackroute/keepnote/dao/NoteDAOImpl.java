package com.stackroute.keepnote.dao;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.stackroute.keepnote.model.Note;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */

public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public NoteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {
		final int id = (int) this.sessionFactory.getCurrentSession().save(note);
		return id > 0 ? true : false;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		final Note note = this.getNoteById(noteId);
		this.sessionFactory.getCurrentSession().delete(note);
		return note != null ? true : false;


	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {
		final Query query = this.sessionFactory.getCurrentSession().createQuery("from Note n order by n.createdAt desc");
		return query.list();
	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {
		return this.sessionFactory.getCurrentSession().find(Note.class, noteId);

	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(note);
		return true;

	}

}
