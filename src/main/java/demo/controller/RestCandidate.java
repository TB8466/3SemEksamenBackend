package demo.controller;

import demo.model.Candidate;
import demo.model.Party;
import demo.repository.CandidateRepository;
import demo.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/candidate")
public class RestCandidate {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    PartyRepository partyRepository;

    //GET all candidates
    @GetMapping("/get")
    public List<Candidate> showCandidates(){
        return candidateRepository.findAll(Sort.by(Sort.Direction.ASC,"candidateName"));
    }

    //Save a candidate
    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<Candidate> createCandidate(@RequestBody Candidate candidate){

        candidateRepository.save(candidate);

        return new ResponseEntity<>(candidate, HttpStatus.CREATED);
    }
    //Update a candidate
    @PutMapping("/edit/{id}")
    public ResponseEntity<Candidate> editCandidate(@PathVariable int id, @RequestBody Candidate newObj){

        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);

        Candidate oldObj = optionalCandidate.get();

        oldObj.setCandidateName(newObj.getCandidateName());
        oldObj.setCandidateAge(newObj.getCandidateAge());
        oldObj.setParty(newObj.getParty());

        candidateRepository.save(oldObj);

        return new ResponseEntity<>(oldObj, HttpStatus.OK);
    }
    //Delete a candidate
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Candidate> deleteCandidate(@PathVariable int id){

        candidateRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/{partyId}")
    public List<Candidate> showCandidates(@PathVariable int partyId){
        Party party = partyRepository.findById(partyId).get();
        return candidateRepository.findByParty(party);
    }

}
