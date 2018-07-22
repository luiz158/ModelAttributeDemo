package com.stackroute.keepnote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;

/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
public class NoteController {
	
	public static final String INDEX = "index";
	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 
	 * 2. Add a new note which should contain the note id, title, content and status. 
	 * 3. Delete an existing note 
	 * 4. Update an existing note
	 * 
	 */
	@Autowired
	private transient final NoteDAO noteDAO;

	/*
	 * Autowiring should be implemented for the NoteDAO.
	 * Create a Note object.
	 * 
	 */
	public NoteController(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}

	/*
	 * Define a handler method to read the existing notes from the database and add
	 * it to the ModelMap which is an implementation of Map, used when building
	 * model data for use with views. it should map to the default URL i.e. "/index"
	 */
	@RequestMapping("/")
	public String getAllNotes(ModelMap model, @ModelAttribute("note") Note note, BindingResult result) {
		final List<Note> notes = this.noteDAO.getAllNotes();
		note = new Note();
		model.addAttribute("notes", notes);
		return INDEX;
	}

	/*
	 * Define a handler method which will read the NoteTitle, NoteContent,
	 * NoteStatus from request parameters and save the note in note table in
	 * database. Please note that the CreatedAt should always be auto populated with
	 * system time and should not be accepted from the user. Also, after saving the
	 * note, it should show the same along with existing messages. Hence, reading
	 * note has to be done here again and the retrieved notes object should be sent
	 * back to the view using ModelMap This handler method should map to the URL
	 * "/add".
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addNote(@ModelAttribute("note") Note note, BindingResult result, ModelMap model) {
		// Validation
		boolean error = false;
		if (note.getNoteTitle().isEmpty()) {
			model.addAttribute("errorMessage", "Note title cannot be empty");
			error = true;
		}
		if (note.getNoteContent().isEmpty()) {
			model.addAttribute("errorMessage1", "Note content cannot be empty");
			error = true;
		}
		if (error) {
			return INDEX;
		}
		
		if (this.noteDAO.saveNote(note)) {
			return "redirect:/";
		}
		return INDEX;
	}

	/*
	 * Define a handler method which will read the NoteId from request parameters
	 * and remove an existing note by calling the deleteNote() method of the
	 * NoteRepository class.This handler method should map to the URL "/delete".
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String removeNote(@RequestParam int noteId, ModelMap model) {
		this.noteDAO.deleteNote(noteId);
		return "redirect:/";
	}

	/*
	 * Define a handler method which will update the existing note. This handler
	 * method should map to the URL "/update".
	 */
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateNote(@ModelAttribute("note") Note note) {
		this.noteDAO.UpdateNote(note);
		return "redirect:/";
	}
	
	
	@GetMapping(path = "update")
	public String updateNoteById(@RequestParam int noteId, ModelMap model) {
		// Get the note by Id to edit
		final Note note = this.noteDAO.getNoteById(noteId);
		if (note != null) {
			// Get all the notes to display in the table below
			final List<Note> notes = this.noteDAO.getAllNotes();
			model.addAttribute("notes", notes);
			// The note to edit is passed to view
			model.addAttribute("editNote", note);
			return INDEX;
		}
		return "redirect:/";
	}

}
