package it.uniroma3.siw.catering.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Admin;
import it.uniroma3.siw.catering.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
    protected AdminRepository adminRepository;

    public Admin getAdmin(Long id) {
        Optional<Admin> result = this.adminRepository.findById(id);
        return result.orElse(null);
    }

    @Transactional
    public Admin saveAdmin(Admin admin) {
        return this.adminRepository.save(admin);
    }

    
}
