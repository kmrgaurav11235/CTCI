/*
https://www.journaldev.com/1617/chain-of-responsibility-design-pattern-in-java

- Chain of responsibility pattern is used to achieve loose coupling in software design where a request 
    from client is passed to a chain of objects to process them. Then the object in the chain will decide 
    themselves who will be processing the request and whether the request is required to be sent to the 
    next object in the chain or not.
- An example of chain of responsibility pattern in JDK are ExceptionHandling in catch-blocks. We know that 
    we can have multiple catch blocks in a try-catch block code. Here every catch block is kind of a processor 
    to process that particular exception.
- So when any exception occurs in the try block, its send to the first catch block to process. If the catch 
    block is not able to process it, it forwards the request to next object in chain i.e next catch block. If 
    even the last catch block is not able to process it, the exception is thrown outside of the chain to the 
    calling program.
- Chain of Responsibility Design Pattern Important Points
    * Client doesn’t know which part of the chain will be processing the request and it will send the request 
        to the first object in the chain. For example, in our program ATMMoneyDispenser is unaware of who is 
        processing the request to dispense the entered amount.
    * Each object in the chain will have it’s own implementation to process the request, either full or partial 
        or to send it to the next object in the chain.
    * Every object in the chain should have reference to the next object in chain to forward the request to, it 
        is achieved by java composition.
    * Creating the chain carefully is very important otherwise there might be a case that the request will never 
        be forwarded to a particular processor or there are no objects in the chain who are able to handle the 
        request.
    * Chain of Responsibility design pattern is good to achieve lose coupling but it comes with the trade-off 
        of having a lot of implementation classes and maintenance problems if most of the code is common in all 
        the implementations.

- Chain of Responsibility Pattern Examples in JDK
    * java.util.logging.Logger#log()
    * javax.servlet.Filter#doFilter()

*/
class Currency {
    private int amount;

    public Currency(int amount) {
        this.amount = amount;
    }
    
    public int getAmount() {
        return amount;
    }
}

interface RupeeDispenser {
    public void setNextDispenser(RupeeDispenser next);
    public void dispense(Currency currentValue);
}
/*
We need to create different processor classes that will implement the RupeeDispenser interface and provide 
implementation of dispense method. Since we are developing our system to work with three types of currency 
bills – 500, 100 and 50, we will create three concrete implementations.
*/
class FiveHundredRupeeDispenser implements RupeeDispenser {

    private static final int AMOUNT = 500;

    private RupeeDispenser next;

    @Override
    public void setNextDispenser(RupeeDispenser next) {
        this.next = next;
    }

    @Override
    public void dispense(Currency currentValue) {
        if (currentValue.getAmount() >= AMOUNT) {
            int num = currentValue.getAmount() / AMOUNT;
            int rem = currentValue.getAmount() % AMOUNT;

            System.out.println("Dispensing " + num + " of Rs." + AMOUNT + " notes.");

            if (rem > 0) {
                if (next != null) {
                    next.dispense(new Currency(rem));
                } else {
                    System.out.println("Cannot dispense remaining Rs." + rem + ". No more denominations left.");
                }
            } else {
                System.out.println("Amount successfully dispensed.");
            }
        } else {
            if (next != null) {
                next.dispense(currentValue);
            } else {
                System.out.println("Cannot dispense remaining Rs." + currentValue.getAmount() + ". No more denominations left.");
            }
        }
    }

}

class HundredRupeeDispenser implements RupeeDispenser {

    private static final int AMOUNT = 100;

    private RupeeDispenser next;

    @Override
    public void setNextDispenser(RupeeDispenser next) {
        this.next = next;
    }

    @Override
    public void dispense(Currency currentValue) {
        if (currentValue.getAmount() >= AMOUNT) {
            int num = currentValue.getAmount() / AMOUNT;
            int rem = currentValue.getAmount() % AMOUNT;

            System.out.println("Dispensing " + num + " of Rs." + AMOUNT + " notes.");

            if (rem > 0) {
                if (next != null) {
                    next.dispense(new Currency(rem));
                } else {
                    System.out.println("Cannot dispense remaining Rs." + rem + ". No more denominations left.");
                }
            } else {
                System.out.println("Amount successfully dispensed.");
            }
        } else {
            if (next != null) {
                next.dispense(currentValue);
            } else {
                System.out.println("Cannot dispense remaining Rs." + currentValue.getAmount() + ". No more denominations left.");
            }
        }
    }

}

class FiftyRupeeDispenser implements RupeeDispenser {

    private static final int AMOUNT = 50;

    private RupeeDispenser next;

    @Override
    public void setNextDispenser(RupeeDispenser next) {
        this.next = next;
    }

    @Override
    public void dispense(Currency currentValue) {
        if (currentValue.getAmount() >= AMOUNT) {
            int num = currentValue.getAmount() / AMOUNT;
            int rem = currentValue.getAmount() % AMOUNT;

            System.out.println("Dispensing " + num + " of Rs." + AMOUNT + " notes.");

            if (rem > 0) {
                if (next != null) {
                    next.dispense(new Currency(rem));
                } else {
                    System.out.println("Cannot dispense remaining Rs." + rem + ". No more denominations left.");
                }
            } else {
                System.out.println("Amount successfully dispensed.");
            }
        } else {
            if (next != null) {
                next.dispense(currentValue);
            } else {
                System.out.println("Cannot dispense remaining Rs." + currentValue.getAmount() + ". No more denominations left.");
            }
        }
    }

}

class ATMMoneyDispenser {

    private RupeeDispenser dispenser;

    ATMMoneyDispenser() {
        RupeeDispenser fiveHundredRupeeDispenser = new FiveHundredRupeeDispenser();
        RupeeDispenser hundredRupeeDispenser = new HundredRupeeDispenser();
        RupeeDispenser fiftyRupeeDispenser = new FiftyRupeeDispenser();

        fiveHundredRupeeDispenser.setNextDispenser(hundredRupeeDispenser);
        hundredRupeeDispenser.setNextDispenser(fiftyRupeeDispenser);

        dispenser = fiveHundredRupeeDispenser;
    }

    public void dispenseCurrency(Currency currency) {
        if (currency.getAmount() % 50 == 0) {
            dispenser.dispense(currency);
        } else {
            System.out.println("Amount to be dispensed must be a multiple of Rs.50.");
        }
    }
}

class COR_01_ChainOfResponsibility {
    public static void main(String[] args) {
        ATMMoneyDispenser atmMoneyDispenser = new ATMMoneyDispenser();
        int amount = 5750;
        System.out.println("Dispensing Rs." + amount + ".");
        atmMoneyDispenser.dispenseCurrency(new Currency(amount));
    }
}