package pl.dmcs.brozga.service;

import java.util.List;

import pl.dmcs.brozga.model.Address;

public interface AddressService {

    public void addAddress(Address address);

    public void editAddress(Address address);

    public List<Address> listAddress();

    public void removeAddress(long id);

    public Address getAddress(long id);
}
