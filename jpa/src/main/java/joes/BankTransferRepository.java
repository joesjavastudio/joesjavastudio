package joes;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface BankTransferRepository extends ListCrudRepository<BankTransfer, String> {
    default BankTransfer findByIdOrThrow(String id) {
        return findById(id).orElseThrow();
    }

    @EntityGraph(attributePaths = {"sender", "receiver"})
    List<BankTransfer> findBySenderId(String senderId);
}
