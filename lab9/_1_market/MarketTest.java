package lab9._1_market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Customer implements Comparable<Customer> {
    private int in;
    private int out;

    public Customer(int in, int out) {
        this.in = in;
        this.out = out;
    }

    public static Customer createCustomer(String line) {
        String[] parts = line.split("\\s+");
        String[] inParts = parts[0].split(":");
        int in = Integer.parseInt(inParts[0]) * 60 + Integer.parseInt(inParts[1]);
        int out = in + Integer.parseInt(parts[1]);
        if (out > 1439) out = 1439;

        return new Customer(in, out);
    }

    public int getIn() {
        return in;
    }

    public int getOut() {
        return out;
    }

    @Override
    public String toString() {
        return String.format("%d %d", in, out);
    }

    @Override
    public int compareTo(Customer other) {
        return Integer.compare(this.out, other.out);
    }
}

public class MarketTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            customers.add(Customer.createCustomer(br.readLine()));
        }

        customers = customers.stream().sorted().collect(Collectors.toList());
        System.out.println(getMaximumCustomers(customers));
    }

    private static int getMaximumCustomers(List<Customer> customers) {
        int maxCustomers = 1;

        for (int i = 0; i < customers.size() - 1; i++) {
            int customerCount = 1;
            for (int j = i + 1; j < customers.size(); j++) {
                if (customers.get(j).getIn() > customers.get(i).getIn() && customers.get(j).getIn() < customers.get(i).getOut()
                || customers.get(j).getOut() < customers.get(i).getOut() && customers.get(j).getOut() > customers.get(i).getIn()
                || customers.get(j).getIn() < customers.get(i).getIn() && customers.get(j).getOut() > customers.get(i).getOut()) {
                    customerCount++;
                }
            }
            if (customerCount > maxCustomers) {
                maxCustomers = customerCount;
            }
            if (maxCustomers >= customers.size() - i) break;
        }

        return maxCustomers;
    }
}