package joes;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(BankTransferRepository bankTransferRepository,
										BankTransferService bankTransferService,
										AccountRepository accountRepository) {
		return args -> {
			Account sender = new Account("sender-id", "John", "Doe");
			sender = accountRepository.save(sender);
			var receiver = accountRepository.save(new Account("receiver-id", "John", "Doe"));

			bankTransferRepository.save(new BankTransfer("bank-transfer-id", "reference2", sender, receiver, Amount.of(100, "CHF")));

			for (int i = 0; i < 50; i++) {
				Account account = accountRepository.save(new Account(
						UUID.randomUUID().toString(), "John", "Doe"));
				BankTransfer bankTransfer = new BankTransfer(UUID.randomUUID().toString(), "reference", sender,
						account, Amount.of(100, "CHF"));
				bankTransferRepository.save(bankTransfer);
			}

			for (int i = 0; i < 50; i++) {
				System.out.println();
			}

			bankTransferService.listReceiverFirstName();
		};

	}



}
