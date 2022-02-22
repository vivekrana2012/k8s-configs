package org.postgrestest.insertapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class InsertAppApplication implements CommandLineRunner {

    private final MerchantRepository merchantRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final TransactionRepository transactionRepository;

    public InsertAppApplication(MerchantRepository merchantRepository, PaymentMethodRepository paymentMethodRepository, TransactionRepository transactionRepository) {
        this.merchantRepository = merchantRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.transactionRepository = transactionRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(InsertAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting Inserts!");

        List<Merchant> merchants = merchantRepository.findAll();
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();

        ExecutorService executor = Executors.newFixedThreadPool(16);

        for (Merchant merchant : merchants) {

            for (PaymentMethod paymentMethod : paymentMethods) {

                executor.submit(new TransactionRunnable(new RandomGenerator(), merchant, paymentMethod, transactionRepository));
            }
        }

        executor.shutdown();
        System.out.println("Done With Inserts!");
    }
}
