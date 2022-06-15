package it.uniroma3.siw.catering.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.catering.model.Admin;
import it.uniroma3.siw.catering.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
    protected AdminRepository adminRepository;

    /**
     * This method retrieves an Admin from the DB based on its ID.
     * @param id the id of the Admin to retrieve from the DB
     * @return the retrieved Admin, or null if no Admin with the passed ID could be found in the DB
     */
    @Transactional
    public Admin getAdmin(Long id) {
        Optional<Admin> result = this.adminRepository.findById(id);
        return result.orElse(null);
    }

    /**
     * This method saves an Admin in the DB.
     * @param user the Admin to save into the DB
     * @return the saved Admin
     * @throws DataIntegrityViolationException if a Admin with the same username
     *                              as the passed Admin already exists in the DB
     */
    @Transactional
    public Admin saveAdmin(Admin admin) {
        return this.adminRepository.save(admin);
    }

    /**
     * This method retrieves all Admins from the DB.
     * @return a List with all the retrieved Admins
     */
    @Transactional
    public List<Admin> getAllAdmins() {
        List<Admin> result = new ArrayList<>();
        Iterable<Admin> iterable = this.adminRepository.findAll();
        for(Admin admin : iterable)
            result.add(admin);
        return result;
    }
    
}
