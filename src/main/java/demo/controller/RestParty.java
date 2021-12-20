package demo.controller;

import demo.model.Party;
import demo.repository.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/party")
public class RestParty {

    @Autowired
    PartyRepository partyRepository;

    //GET all parties
    @GetMapping("/get")
    public List<Party> showParties(){
        return partyRepository.findAll();
    }
    //Save a party
    @PostMapping(value = "/save", consumes = "application/json")
    public ResponseEntity<Party> createParty(@RequestBody Party party){

        partyRepository.save(party);

        return new ResponseEntity<>(party, HttpStatus.CREATED);
    }
    //Update a party
    @PutMapping("/edit/{id}")
    public ResponseEntity<Party> editParty(@PathVariable int id, @RequestBody Party newObj){

        Optional<Party> optionalParty = partyRepository.findById(id);

        Party oldObj = optionalParty.get();

        oldObj.setPartyName(newObj.getPartyName());

        partyRepository.save(oldObj);

        return new ResponseEntity<>(oldObj, HttpStatus.OK);
    }
    //Delete a party
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Party> deleteParty(@PathVariable int id){

        partyRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
