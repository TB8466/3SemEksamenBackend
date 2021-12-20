package demo.model;

import javax.persistence.*;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidateId;

    private String candidateName;
    private int candidateAge;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;

    public Candidate() {
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public int getCandidateAge() {
        return candidateAge;
    }

    public void setCandidateAge(int candidateAge) {
        this.candidateAge = candidateAge;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }
}
