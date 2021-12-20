package demo.repository;

import demo.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PartyRepository extends JpaRepository<Party, Integer> {

}
