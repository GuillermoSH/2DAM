package com.docencia.com.proc_cli.services.impl;

import com.docencia.com.proc_cli.repositories.file.FileJobRepository;
import org.springframework.stereotype.Component;

import com.docencia.com.proc_cli.domain.Job;
import com.docencia.com.proc_cli.services.impl.abstracts.CommandServiceAbstract;

@Component
public class LsofServiceImpl extends CommandServiceAbstract {
    private final FileJobRepository jobRepository = new FileJobRepository();

    public LsofServiceImpl() {
        String[] allowedCmds = {"lsof -i"};
        this.setJobRepository(jobRepository);
        this.setJobType(Job.LSOF);
        this.setAllowedCmds(allowedCmds);
    }
}
