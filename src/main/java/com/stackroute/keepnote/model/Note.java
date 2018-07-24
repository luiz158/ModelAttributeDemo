package com.stackroute.keepnote.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

public class Note {

	private String noteTitle;
	private String noteContent;
	private String noteStatus;
	
	public Note() {
	}
	
	
	public Note(String noteTitle, String noteContent, String noteStatus) {
		super();
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
	}


	public String getNoteTitle() {
		return noteTitle;
	}


	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}


	public String getNoteContent() {
		return noteContent;
	}


	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}


	public String getNoteStatus() {
		return noteStatus;
	}


	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}
	
	

}
