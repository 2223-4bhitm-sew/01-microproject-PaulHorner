package at.htl.entity;

public class Customer {

    private Long customerNumber;

    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name  = name;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}

