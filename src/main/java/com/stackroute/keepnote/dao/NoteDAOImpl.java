package com.stackroute.keepnote.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Repository
@Transactional
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	
	private final SessionFactory sessionFactory;
	private final CriteriaBuilder criteriaBuilder;
	private final CriteriaQuery<Note> noteCriteriaQuery;
	private final Root<Note> noteRoot;

	public NoteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.criteriaBuilder = this.sessionFactory.getCriteriaBuilder();
		this.noteCriteriaQuery = this.criteriaBuilder.createQuery(Note.class);
		this.noteRoot = noteCriteriaQuery.from(Note.class);
		this.noteCriteriaQuery.orderBy(this.criteriaBuilder.desc(noteRoot.get("createdAt")));
	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {
		return getSession().save(note) != null ? true: false;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		final Note note = this.getNoteById(noteId);
		getSession().delete(note);
		return true;

	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {
		return getSession().createQuery(this.noteCriteriaQuery.select(noteRoot)).list();

	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {		
		return getSession().find(Note.class, noteId);

	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {
		return getSession().merge(note) != null ? true : false;

	}
	
	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
