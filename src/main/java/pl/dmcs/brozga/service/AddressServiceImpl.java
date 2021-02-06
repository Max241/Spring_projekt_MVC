package pl.dmcs.brozga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dmcs.brozga.repository.AddressRepo;
import pl.dmcs.brozga.model.Address;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    private AddressRepo addressRepo;

    @Autowired
    public AddressServiceImpl(AddressRepo addressRepository) {
        this.addressRepo = addressRepository;
    }

    @Transactional
    public void addAddress(Address address) {
        addressRepo.save(address);
    }

    @Transactional
    public void editAddress(Address address) {
        addressRepo.save(address);
    }

    @Transactional
    public List<Address> listAddress() {
        return addressRepo.findAll();
    }

    @Transactional
    public void removeAddress(long id) {
        addressRepo.delete(id);
    }

    @Transactional
    public Address getAddress(long id) {
        return addressRepo.findById(id);
    }
}

