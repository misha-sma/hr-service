package candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import candidate.data.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

}
