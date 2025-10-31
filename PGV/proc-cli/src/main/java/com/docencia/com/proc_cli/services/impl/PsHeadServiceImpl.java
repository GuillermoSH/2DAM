package com.docencia.com.proc_cli.services.impl;

import com.docencia.com.proc_cli.repositories.file.FileJobRepository;
import org.springframework.stereotype.Component;

import com.docencia.com.proc_cli.domain.Job;
import com.docencia.com.proc_cli.services.impl.abstracts.CommandServiceAbstract;

@Component
public class PsHeadServiceImpl extends CommandServiceAbstract {

    public PsHeadServiceImpl() {
        String[] allowedCmds = {"ps aux | head"};
        FileJobRepository jobRepository = new FileJobRepository();
        this.setJobRepository(jobRepository);
        this.setJobType(Job.PS);
        this.setAllowedCmds(allowedCmds);
    }
}
