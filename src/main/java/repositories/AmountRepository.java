package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Amount;

@Repository
public interface AmountRepository extends JpaRepository<Amount,Integer>{

}

