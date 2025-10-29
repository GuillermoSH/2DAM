package com.docencia.com.proc_cli.repositories.interfaces;

import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository {
    boolean add(String content);
}
