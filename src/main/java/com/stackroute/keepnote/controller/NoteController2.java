package com.stackroute.keepnote.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.stackroute.keepnote.model.Note;

@Controller
public class NoteController2 {
/*
	Note note = new Note();
	
	public NoteController2() {
		
	}

	@RequestMapping("/")
	public String getAllNotes(Note note) {
		return "index";
	}

		
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
		
		if (note.getNoteStatus().isEmpty()) {
			model.addAttribute("errorMessage2", "Note Status cannot be empty");
			error = true;
		}
		
		System.out.println(note.getNoteTitle());
		System.out.println(error);
		model.addAttribute("notes", note);

		return "index";

		
	}

		
	
*/	
	
}
