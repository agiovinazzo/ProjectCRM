package projectCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projectCRM.model.QuotationEntity;

@Repository
public interface QuotationRepository extends JpaRepository<QuotationEntity, Integer> {

}
