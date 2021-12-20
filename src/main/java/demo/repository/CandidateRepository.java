package demo.repository;

import demo.model.Candidate;
import demo.model.Party;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  CandidateRepository extends JpaRepository<Candidate,Integer> {

    List<Candidate> findByParty(Party party);

}
