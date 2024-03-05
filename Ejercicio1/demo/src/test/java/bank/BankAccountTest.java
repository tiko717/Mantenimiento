package bank;

import static org.junit.Assert.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    
    @Test
    @DisplayName("Test withdraw debe dar true si datos correctos")
    public void withdraw_suficienteDinero_returnTrue(){
        BankAccount b1 = new BankAccount(10);
        assertTrue(b1.withdraw(9));
    }

    @Test
    @DisplayName("Test withdraw debe dar false si el dinero a sacar es mayor al dinero en la cuenta")
    public void withdraw_insuficienteDinero_returnFalse(){
        BankAccount b1 = new BankAccount(10);
        assertFalse(b1.withdraw(11));
    }

    @Test
    @DisplayName("Test deposit debe saltar excepcion si la cantidad a introducir es menor a 0")
    public void deposit_cantidadNegativa_throwIllegalArgumentException(){
        BankAccount b1 = new BankAccount(10);
        assertThrows(IllegalArgumentException.class, () -> b1.deposit(-1));
    }

    @Test
    @DisplayName("Test deposit debe devolver balance sumado con la cantidad introducida")
    public void deposit_cantidadPositiva_returnTrue(){
        BankAccount b1 = new BankAccount(10);
        int cantidad = b1.getBalance();
        b1.deposit(10);
        assertEquals(b1.getBalance(), cantidad+10);
    }

    @Test
    @DisplayName("Test payment debe devolver el resultado de la formula al introducir los datos")
    public void payment_formulaCorrecta_returnTrue(){
        BankAccount b1 = new BankAccount(10);
        double cantidad = 5*(0.1*Math.pow((1+0.1), 2)/(Math.pow((1+0.1), 2)-1));
        assertEquals(b1.payment(5, 0.1, 2), cantidad, 0);
    }

    @Test
    @DisplayName("Test pending debe devolver la cantidad inicial si el mes es 0")
    public void pending_mes0_returnTrue(){
        BankAccount b1 = new BankAccount(10);
        double r = 10;
        assertEquals(b1.pending(10, 0.1, 2, 0), r, 0);
    }

    @Test
    @DisplayName("Test pending debe devolver la cantidad restante en un mes específico")
    public void pending_mesMayorA0_returnTrue(){
        BankAccount b1 = new BankAccount(10);
        double total_amount = 100;
        double interest = 0.001;
        int months = 12;
        int month = 2;
        double res = total_amount;
        for(int i=1; i<=month; i++){
            double ant=res;
            res = ant - (total_amount*(interest*Math.pow((1+interest), months)/(Math.pow((1+interest), months)-1)) - interest*ant);
        }
        assertEquals(res, b1.pending(total_amount, interest, months, month), 0);
    }
}
