package com.docencia.com.examen.procesos.services.impl;

import org.springframework.stereotype.Component;

import com.docencia.com.examen.procesos.domain.Job;
import com.docencia.com.examen.procesos.repositories.file.FileJobRepository;
import com.docencia.com.examen.procesos.services.impl.abstracts.CommandServiceAbstract;


@Component
public class DfServiceImpl extends CommandServiceAbstract {
    private final FileJobRepository jobRepository = new FileJobRepository();

    public DfServiceImpl() {
        String[] allowedCmds = {"df", "df -h", "df -H", "df -h | head"};
        this.setJobRepository(jobRepository);
        this.setJobType(Job.DF);
        this.setAllowedCmds(allowedCmds);
    }
}
