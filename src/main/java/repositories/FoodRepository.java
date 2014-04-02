package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,Integer>{

}

