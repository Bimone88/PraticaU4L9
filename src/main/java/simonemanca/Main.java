package simonemanca;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Product> productList = new ArrayList<>();
        // Leggere l'input dell'utente e aggiungere i prodotti alla productList
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

        // Creare qui le liste di esempio dei prodotti e ordini
        List<Order> orderList = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();

        // ((aggiungeee lista )

        // Esercizio #1: Lista di prodotti della categoria "Books" con prezzo > 100
        List<Product> expensiveBooks = productList.stream()
                .filter(product -> "Books".equals(product.getCategory()) && product.getPrice() > 100)
                .collect(Collectors.toList());

        // Stampa i risultati di libriCostosi
        expensiveBooks.forEach(System.out::println);

        // Esercizio #2: Lista di ordini con prodotti della categoria "Baby"
        List<Order> ordersWithBabyProducts = orderList.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(product -> "Baby".equals(product.getCategory())))
                .collect(Collectors.toList());

        // Stampa i risultati di ordiniConProdottiPerBambini
        ordersWithBabyProducts.forEach(System.out::println);

        // Esercizio #3: Lista di prodotti della categoria "Boys" con uno sconto del 10% applicato
        List<Product> discountedBoysProducts = productList.stream()
                .filter(product -> "Boys".equals(product.getCategory()))
                .map(product -> new Product(product.getId(), product.getName(), product.getCategory(), product.getPrice() * 0.9))
                .collect(Collectors.toList());

        // Stampa i risultati di prodottiScontatiPerRagazzi
        discountedBoysProducts.forEach(System.out::println);

        // Esercizio #4: Lista di prodotti ordinati da clienti di livello (tier) 2 tra l’01-Feb-2021 e l’01-Apr-2021
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);
        List<Product> productsOrderedByTier2Customers = orderList.stream()
                .filter(order -> order.getOrderDate().compareTo(startDate) >= 0 &&
                        order.getOrderDate().compareTo(endDate) <= 0 &&
                        order.getCustomer().getTier() == 2)
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());

        // Stampa i risultati di prodottiOrdinatiDaTier2
        productsOrderedByTier2Customers.forEach(System.out::println);
    }
}

class Product {
    private Long id;
    private String name;
    private String category;
    private Double price;

    public Product(Long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}

class Order {
    private LocalDate orderDate;
    private List<Product> products;
    private Customer customer;

    public Order(LocalDate orderDate, List<Product> products, Customer customer) {
        this.orderDate = orderDate;
        this.products = products;
        this.customer = customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", products=" + products +
                ", customer=" + customer +
                '}';
    }
}

class Customer {
    private int tier;

    public Customer(int tier) {
        this.tier = tier;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "tier=" + tier +
                '}';
    }
}



