package fr.dawan.demomvc.models.repositories;

import fr.dawan.demomvc.models.entities.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne,Long> {
}
