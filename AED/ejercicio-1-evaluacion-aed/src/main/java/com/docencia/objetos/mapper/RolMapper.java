package com.docencia.objetos.mapper;

import com.docencia.objetos.domain.Rol;
import com.docencia.objetos.repo.jpa.RolEntity;

public class RolMapper {
    public static Rol to(RolEntity entity) {
        return new Rol(entity.getId(), entity.getNombre());
    }

    public static RolEntity to(Rol rol) {
        return new RolEntity(rol.getId(), rol.getNombre());
    }
}
