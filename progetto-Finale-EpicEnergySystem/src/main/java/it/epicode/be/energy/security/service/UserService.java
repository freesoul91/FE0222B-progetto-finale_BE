package it.epicode.be.energy.security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.be.energy.exceptions.EnergyException;
import it.epicode.be.energy.security.model.User;
import it.epicode.be.energy.security.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByUserName(String username) {
		return userRepository.findByUserName(username);
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User update(Long id, User utente) {
		Optional<User> utenteResult = userRepository.findById(id);

		if (utenteResult.isPresent()) {
			User utenteUpdate = utenteResult.get();

			utenteUpdate.setNome(utente.getNome());
			utenteUpdate.setCognome(utente.getCognome());
			utenteUpdate.setUserName(utente.getUserName());
			utenteUpdate.setEmail(utente.getEmail());
			utenteUpdate.setPassword(utente.getPassword());
			utenteUpdate.setActive(utente.isActive());
			userRepository.save(utenteUpdate);
			return utenteUpdate;
		} else {
			throw new EnergyException("Utente non aggiornato");
		}
		
	}

	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
