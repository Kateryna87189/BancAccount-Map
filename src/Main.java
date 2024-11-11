import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;




/*
Дан список  BankAccount {Person owner, String IBAN, double balance).
Класс Person состоит из {String fName, String lName String email).
Используя stream необходимо получить:

map где ключом будет owner (Person), а значением будет баланс (Double)
map где ключом будет IBAN (String), а значением будет owner (Person)
map где ключом будет IBAN (String), а значением будет строка  J.Jack
(т.e. первая буква fName и lName)

В рамках данной задачи person и account  уникальны и у каждого owner a
единственный счет.

 */
public class Main {
    public static void main(String[] args) {
        List<BankAccount> accounts = List.of(
                new BankAccount(new Person("John", "Smith", "smith@mail.com"), "DE1000000002", 90.53),
                new BankAccount(new Person("Jack", "Vorobej", "vorobej@mail.com"), "DE1000000001", 100500.70),
                new BankAccount(new Person("Svetlana", "Ivanova", "ivanova@mail.com"), "DE1000000003", 1005.04),
                new BankAccount(new Person("Sem", "Simpson", "simpson@mail.com"), "DE1000000004", 2000.16)
        );
        //map где ключом будет owner (Person), а значением будет баланс (Double)
        Map<Person, Double> ownerToBalanceMap= accounts.stream()
                .collect(Collectors.toMap(BankAccount::getOwner,BankAccount::getBalance));
        System.out.println("-----Map of owner to balance:----- ");
       ownerToBalanceMap.forEach((owner, balance)-> System.out.println(owner + "=" + balance));

       //map где ключом будет IBAN (String), а значением будет owner (Person)
        Map<String, Person> ibanToOwnerMap = accounts.stream()
                .collect(Collectors.toMap(BankAccount::getIBAN, BankAccount::getOwner));
        System.out.println("------Map of IBAN to owner:------- ");
        ibanToOwnerMap.forEach((iban, owner)-> System.out.println(iban+ "=" +owner));

        //map где ключом будет IBAN (String), а значением будет строка  J.Jack
        //(т.e. первая буква fName и lName)
        Map<String, String> ibanToInitialMap= accounts.stream()
                .collect(Collectors.toMap(BankAccount::getIBAN,
                        account->account.getOwner().getfName().charAt(0)+"."+
                        account.getOwner().getlName()));
        System.out.println("--------------Map of IBAN to initials---------------");
        ibanToInitialMap.forEach((iban, initials)-> System.out.println(iban+"="+ initials));

        //Map где ключ email, а значение Bankaccount
        Map<String, BankAccount> emailToAccountsMap= accounts.stream()
                .collect(Collectors.toMap(
                        account->account.getOwner().getEmail(),
                        account->account
                ));

        //получение 3 клиентов с наибольшим балансом
        List<String> top3Clients =emailToAccountsMap.values().stream()
                .sorted(Comparator.comparingDouble(BankAccount::getBalance).reversed())
                .limit(3)
                .map(account->account.getOwner().getfName()+" " + account.getOwner().getlName())
                .collect(Collectors.toList());

        System.out.println("--------Top 3 clients by balance--------------");
        top3Clients.forEach(System.out::println);

    }
}