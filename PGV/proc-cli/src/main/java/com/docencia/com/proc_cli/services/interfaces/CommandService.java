package com.docencia.com.proc_cli.services.interfaces;

public interface CommandService {
    boolean processLine(String command);

    boolean validate(String command, String[] allowed_cmds);
}
