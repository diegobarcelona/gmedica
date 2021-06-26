package gmedica.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gmedica.challenge.model.Data;

public interface DataRepository extends JpaRepository<Data, Long> {

}
