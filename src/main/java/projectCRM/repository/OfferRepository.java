package projectCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projectCRM.model.OfferEntity;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Integer> {

}
