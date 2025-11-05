package com.docencia.com.proc_cli.services.impl;

import com.docencia.com.proc_cli.repositories.file.FileJobRepository;
import org.springframework.stereotype.Component;

import com.docencia.com.proc_cli.domain.Job;
import com.docencia.com.proc_cli.services.impl.abstracts.CommandServiceAbstract;

@Component
public class TopServiceImpl extends CommandServiceAbstract {
    private final FileJobRepository jobRepository = new FileJobRepository();

    public TopServiceImpl() {
        String[] allowedCmds = new String[]{"top"};
        this.setJobRepository(jobRepository);
        this.setJobType(Job.TOP);
        this.setAllowedCmds(allowedCmds);
        this.setCmdOverride("top -b -n 1");
    }
}
