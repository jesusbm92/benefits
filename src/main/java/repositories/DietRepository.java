package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet,Integer>{

}

