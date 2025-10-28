package com.docencia.ficheros.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.docencia.ficheros.model.Note;

class XmlServiceNoteTest {
    XmlServiceNote xmlServiceNote;
    Note note;

    @BeforeEach
    void beforeEach() {
        xmlServiceNote = new XmlServiceNote();
        note = new Note();
        note.setId("1");
        note.setTitle("Titulo");
        note.setContent("Contenido");
    }

    @Test
    void seriablizarNote() {
        String noteStr = xmlServiceNote.noteToString(note);
        Note noteTest = xmlServiceNote.StringToNote(noteStr);
        Assertions.assertEquals(note, noteTest, "Los elementos deben ser iguales");
    }

}
