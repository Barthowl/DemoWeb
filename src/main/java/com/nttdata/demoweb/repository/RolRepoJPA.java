package com.nttdata.demoweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nttdata.demoweb.repository.entity.Rol;
// Integer = PK de rol
public interface RolRepoJPA extends JpaRepository <Rol, Integer>{

}
