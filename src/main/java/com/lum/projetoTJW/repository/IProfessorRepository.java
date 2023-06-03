package com.lum.projetoTJW.repository;

import com.lum.projetoTJW.entity.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository extends CrudRepository<Professor,Long> {
}
