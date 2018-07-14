package com.stackroute.keepnote.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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

public class NoteController {
	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 2. Add a new
	 * note which should contain the note id, title, content and status. 3. Delete
	 * an existing note 4. Update an existing note
	 * 
	 */

	/*
	 * Autowiring should be implemented for the NoteDAO. Create a Note object.
	 * 
	 */
	private static List<Note> noteList = new ArrayList<>();
	public final static String NOTE_ID = "noteId";
	public final static String SAVE_NOTE = "/add";
	public final static String DELETE_NOTE = "/delete";
	public final static String INDEX = "index";
	public final static String NOTE_TITLE = "noteTitle";
	public final static String NOTE_CONTENT = "noteContent";
	public final static String NOTE_STATUS = "noteStatus";
	public final static String NOTE = "note";
	public final static String NOTE_LIST = "noteList";
	public final static String REDIRECT = "redirect:/";
	public final static String UPDATE = "/update";

	@Autowired
	private NoteDAO noteDao;

	@Autowired
	@Qualifier("noteValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	public NoteController() {

	}

	public NoteController(final NoteDAO noteDao) {
		this.noteDao = noteDao;
	}

	/*
	 * Define a handler method to read the existing notes from the database and add
	 * it to the ModelMap which is an implementation of Map, used when building
	 * model data for use with views. it should map to the default URL i.e. "/index"
	 */
	@GetMapping(value = "/")
	public String getAllNotes(final ModelMap model) {
		noteList = noteDao.getAllNotes();
		model.addAttribute(NOTE_LIST, noteList);
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

	@RequestMapping(value = SAVE_NOTE, method = POST)
	public String addNote(@ModelAttribute(NOTE) @Validated Note note, BindingResult bindingResult, ModelMap modelMap) {
		final boolean isError = bindingResult.hasErrors();
		boolean dbFailure = false;
		if (!isError) {
			dbFailure = noteDao.saveNote(note);
		}
		if (!dbFailure) {
			final List<Note> list = noteDao.getAllNotes();
			modelMap.addAttribute(NOTE, note);
			modelMap.addAttribute(NOTE_LIST, list);
			return INDEX;
		}
		final Note newNoteObj = new Note();

		noteList = noteDao.getAllNotes();
		modelMap.addAttribute(NOTE_LIST, noteList);
		modelMap.addAttribute(NOTE, newNoteObj);
		return REDIRECT;
	}

	/*
	 * Define a handler method which will read the NoteId from request parameters
	 * and remove an existing note by calling the deleteNote() method of the
	 * NoteRepository class.This handler method should map to the URL "/delete".
	 */

	@GetMapping(value = DELETE_NOTE)
	public String deleteNote(@RequestParam(NOTE_ID) final int noteId) {
		noteDao.deleteNote(noteId);
		return REDIRECT;
	}

	/*
	 * Define a handler method which will update the existing note. This handler
	 * method should map to the URL "/update".
	 */
	@RequestMapping(value = UPDATE, method = RequestMethod.POST)
	public String updateExistingNote(@ModelAttribute(NOTE) @Validated Note note, BindingResult bindingResult,
			ModelMap modelMap) {
		if (bindingResult.hasErrors()) {
			List<Note> list = noteDao.getAllNotes();
			modelMap.addAttribute(NOTE_LIST, list);
			return INDEX;
		}
		note.setCreatedAt(LocalDateTime.now());
		noteDao.UpdateNote(note);
		List<Note> list = noteDao.getAllNotes();
		modelMap.addAttribute(NOTE_LIST, list);
		final Note newNoteObj = new Note();
		modelMap.addAttribute(NOTE, newNoteObj);
		return REDIRECT;
	}

}
