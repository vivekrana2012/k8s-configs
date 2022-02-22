package org.postgrestest.insertapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class InsertAppApplication implements CommandLineRunner {

    private final MerchantRepository merchantRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final TransactionRepository transactionRepository;
    private final RandomGenerator randomGenerator;

    public InsertAppApplication(MerchantRepository merchantRepository, PaymentMethodRepository paymentMethodRepository, TransactionRepository transactionRepository, RandomGenerator randomGenerator) {
        this.merchantRepository = merchantRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.transactionRepository = transactionRepository;
        this.randomGenerator = randomGenerator;
    }

    public static void main(String[] args) {
        SpringApplication.run(InsertAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting Inserts!");

        List<Merchant> merchants = merchantRepository.findAll();
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();

        List<Transaction> transactions = new ArrayList<>(70);

        for (Merchant merchant : merchants) {

            for (PaymentMethod paymentMethod : paymentMethods) {

                for (int i = 1; i <= 1000000; i++) {
                    Transaction transaction = new Transaction();
                    transaction.setStatus(randomGenerator.getStatus());
                    transaction.setAmount(randomGenerator.getAmount());
                    transaction.setMerchantId(merchant.getId());
                    transaction.setPaymentMethodId(paymentMethod.getId());

                    transactions.add(transaction);

                    if (i % 50 == 0) {
                        transactionRepository.saveAll(transactions);
                        transactions.clear();
                    }
                }
            }
        }

        System.out.println("Done With Inserts!");
    }
}
