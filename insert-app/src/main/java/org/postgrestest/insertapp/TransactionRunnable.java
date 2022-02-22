package org.postgrestest.insertapp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionRunnable implements Runnable {

    private final RandomGenerator randomGenerator;
    private final Merchant merchant;
    private final PaymentMethod paymentMethod;
    private final TransactionRepository transactionRepository;

    public TransactionRunnable(RandomGenerator randomGenerator, Merchant merchant, PaymentMethod paymentMethod, TransactionRepository transactionRepository) {
        this.randomGenerator = randomGenerator;
        this.merchant = merchant;
        this.paymentMethod = paymentMethod;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void run() {

        List<Transaction> transactions = new ArrayList<>(550);

        for (int i = 1; i <= 1000000; i++) {
            Transaction transaction = new Transaction();
            transaction.setStatus(randomGenerator.getStatus());
            transaction.setAmount(randomGenerator.getAmount());
            transaction.setMerchantId(merchant.getId());
            transaction.setPaymentMethodId(paymentMethod.getId());
            transaction.setTimestamp(LocalDateTime.now());

            transactions.add(transaction);

            if (i % 400 == 0) {
                transactionRepository.saveAll(transactions);
                transactions.clear();
            }
        }
    }
}
