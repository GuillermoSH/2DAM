package com.docencia.com.proc_cli.services.impl.abstracts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.docencia.com.proc_cli.repositories.interfaces.JobRepository;

@Component
public abstract class CommandServiceAbstract {
    JobRepository jobRepository;
    
    @Autowired
    public CommandServiceAbstract(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
}
