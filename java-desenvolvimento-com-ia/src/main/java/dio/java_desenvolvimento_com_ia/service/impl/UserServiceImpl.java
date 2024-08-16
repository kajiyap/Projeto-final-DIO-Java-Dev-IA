package dio.java_desenvolvimento_com_ia.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import dio.java_desenvolvimento_com_ia.domain.model.User;
import dio.java_desenvolvimento_com_ia.domain.repo.UserRepo;
import dio.java_desenvolvimento_com_ia.service.UserService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepository) {
        this.userRepo = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<User> findAll() {
        if(userRepo.count() > 0){
            return userRepo.findAll();
        }else{
            throw new IllegalArgumentException("Not found User");
        }
    }

    @Override
    public User create(User userToCreate) {
        if(userRepo.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This account number already exists. ");
        }else if(userRepo.existsByCardNumber(userToCreate.getCard().getNumber())){
            throw new IllegalArgumentException("This card number already exists.");
        }else{
            return userRepo.save(userToCreate);
        }
    }

    @Override
    public User update(Long id, User userToUpdate) {
        // Verifica se o usuário existe no banco de dados
        User dbUser = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        System.out.println(dbUser.getId());
        System.out.println(userToUpdate.getId());
        // Verifica se o ID fornecido corresponde ao ID do banco de dados
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new IllegalArgumentException("Update IDs must be the same");
        }

        // Atualiza os campos do usuário
        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setNews(userToUpdate.getNews());
        dbUser.setFeatures(userToUpdate.getFeatures());

        // Salva o usuário atualizado e retorna-o
        return userRepo.save(dbUser);
    }


    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
    
}
