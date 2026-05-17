package br.com.vollun.repository;

import br.com.vollun.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAdminRepository extends JpaRepository<Admin, UUID> {
}
