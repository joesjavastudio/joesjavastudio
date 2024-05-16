package joes;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankTransferService {
    private BankTransferRepository bankTransferRepository;

    public BankTransferService(BankTransferRepository bankTransferRepository) {
        this.bankTransferRepository = bankTransferRepository;
    }
    @Transactional
    void settleTransfer() {
        BankTransfer bankTransfer = bankTransferRepository.findByIdOrThrow("bank-transfer-id");
        bankTransfer.settle();
        bankTransferRepository.save(bankTransfer);
    }

    @Transactional
    void listReceiverFirstName() {
        List<BankTransfer> bankTransferList = bankTransferRepository.findBySenderId("sender-id");
        for (BankTransfer transfer : bankTransferList) {
            System.out.println(transfer.getReceiver().getFirstName());
        }
    }
}
