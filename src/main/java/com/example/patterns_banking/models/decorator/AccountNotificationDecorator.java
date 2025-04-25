package com.example.patterns_banking.models.decorator;

import com.example.patterns_banking.models.Account;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class AccountNotificationDecorator extends AccountDecorator {

  private static final double WITHDRAW_EXTRA_MAX = 20000;

  public AccountNotificationDecorator(Account account) {
    super(account);
  }

  @Override
  public void deposit(Double amount) {
    super.deposit(amount);
    System.out.println("Se ha realizado un deposito de: " + amount + " en la cuenta de: " + getAccountNumber());
  }

  @Override
  public void withdraw(double amount) {
    if (amount > (getBalance() + WITHDRAW_EXTRA_MAX)) {
      throw new RuntimeException("El monto excede el límite de retiro permitido. Excedente: " + (amount - getBalance()));
    }

    String msg = "Se ha realizado un retiro de: " + amount + " en la cuenta de: " + getAccountNumber();
    double overdraft = getBalance() - amount;
    if(overdraft < 0) {
      amount = getBalance();
      setBalance(0.0);
      msg += "\n El monto excede el saldo de la cuenta. Excedente: " + Math.abs(overdraft);
    }

    super.withdraw(amount);
    System.out.println(msg);
  }

}
