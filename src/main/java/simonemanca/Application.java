package simonemanca;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Product> productList = new ArrayList<>();
        System.out.println("Inserire il numero di prodotti:");
        int numberOfProducts = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfProducts; i++) {
            System.out.println("Inserire l'ID del prodotto:");
            Long id = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Inserire il nome del prodotto:");
            String name = scanner.nextLine();

            System.out.println("Inserire la categoria del prodotto:");
            String category = scanner.nextLine();

            System.out.println("Inserire il prezzo del prodotto:");
            Double price = scanner.nextDouble();
            scanner.nextLine();

            productList.add(new Product(id, name, category, price));
        }

        scanner.close();

        // Creazione di liste di esempio per ordini e clienti
        List<Order> orderList = createDummyOrders(productList); // Implementa questo metodo
        List<Customer> customerList = createDummyCustomers(); // Implementa questo metodo

        // Esercizio #1: Raggruppare gli ordini per cliente
        System.out.println("Esercizio #1: Raggruppare gli ordini per cliente");
        raggruppaOrdiniPerCliente(orderList).forEach((customer, orders) -> System.out.println("Cliente: " + customer + ", Ordini: " + orders));

        // Esercizio #2: Calcolare il totale delle vendite per ogni cliente
        System.out.println("Esercizio #2: Calcolare il totale delle vendite per ogni cliente");
        calcolaTotaleVenditePerCliente(orderList).forEach((customer, total) -> System.out.println("Cliente: " + customer + ", Totale Vendite: " + total));

        // Esercizio #3: Trovare i prodotti più costosi
        System.out.println("Esercizio #3: Trovare i prodotti più costosi");
        System.out.println("Prodotto più costoso: " + trovaProdottoPiuCostoso(productList).orElse(null));

        // Esercizio #4: Calcolare la media degli importi degli ordini
        System.out.println("Esercizio #4: Calcolare la media degli importi degli ordini");
        System.out.println("Media importi ordini: " + calcolaMediaImportiOrdini(orderList));
    }

    private static double calcolaMediaImportiOrdini(List<Order> orderList) {
        return orderList.stream()
                .flatMapToDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice))
                .average()
                .orElse(0);
    }

    private static Optional<Product> trovaProdottoPiuCostoso(List<Product> productList) {
        return productList.stream()
                .max(Comparator.comparingDouble(Product::getPrice));
    }

    private static Map<Customer, Double> calcolaTotaleVenditePerCliente(List<Order> orderList) {
        return orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer,
                        Collectors.summingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum())));
    }

    private static Map<Customer, List<Order>> raggruppaOrdiniPerCliente(List<Order> orderList) {
        return orderList.stream()
                .collect(Collectors.groupingBy(Order::getCustomer));
    }

    private static List<Customer> createDummyCustomers() {
        // Esempio: ritorna una lista vuota o modifica questo per generare clienti fittizi
        return Collections.emptyList();
    }

    private static List<Order> createDummyOrders(List<Product> productList) {
        // Esempio: ritorna una lista vuota o modifica questo per creare ordini fittizi basati su productList
        return Collections.emptyList();
    }
}


