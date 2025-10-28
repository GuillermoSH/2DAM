package com.docencia.ficheros.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.docencia.ficheros.model.Note;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlServiceNote extends ServiceNoteAbstracta {
    XmlMapper xmlMapper;

    private static Logger logger = LoggerFactory.getLogger(XmlMapper.class);

    public XmlServiceNote() {
        xmlMapper = new XmlMapper();
    }

    @Override
    public boolean exists(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exists'");
    }

    @Override
    public Note findById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }


    @Override
    public Note save(Note n) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean delete(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public String noteToString(Note note) {
        String resultado = null;
        try {
            resultado = xmlMapper.writeValueAsString(note);
        } catch (Exception e) {
            logger.error("Se ha producido un error en la transformacion de note {}",
                    note, e);
        }
        return resultado;
    }

    @Override
    public Note StringToNote(String data) {
        Note resultado = null;
        try {
            resultado = xmlMapper.readValue(data, Note.class);
        } catch (Exception e) {
            logger.error("Se ha producido un error en la transformacion de data {}",
                    data, e);
        }
        return resultado;
    }
}
