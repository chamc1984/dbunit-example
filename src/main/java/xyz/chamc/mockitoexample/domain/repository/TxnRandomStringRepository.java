package xyz.chamc.mockitoexample.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.chamc.mockitoexample.domain.model.TxnRandomStringEntity;

@Repository
public interface TxnRandomStringRepository extends JpaRepository<TxnRandomStringEntity, Integer> {

}
