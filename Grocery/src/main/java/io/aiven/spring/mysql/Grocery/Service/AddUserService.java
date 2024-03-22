package io.aiven.spring.mysql.Grocery.Service;

import io.aiven.spring.mysql.Grocery.Model.UserAcc;
import io.aiven.spring.mysql.Grocery.Repository.UserAccRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddUserService {

    private final UserAccRepository userAccRepository;
    @Autowired
    public AddUserService(UserAccRepository userAccRepository) {
        this.userAccRepository = userAccRepository;
    }


    public void adduser(String FirstName, String LastName, String Email, String Gender, String DOB, String Password){
        UserAcc UA=new UserAcc();
        UA.setFirstname(FirstName);
        UA.setLastname(LastName);
        UA.setEmail(Email);
        UA.setGender(Gender);
        UA.setDOB(DOB);
        UA.setPassword(Password);
        userAccRepository.save(UA);
    }

    public boolean update(Integer Id,String FirstName, String LastName, String Email, String Gender, String DOB, String Password){

        Optional<UserAcc> op=userAccRepository.findById(Id);
        if(op.isPresent()) {
            UserAcc UA=op.get();
            UA.setFirstname(FirstName);
            UA.setLastname(LastName);
            UA.setEmail(Email);
            UA.setGender(Gender);
            UA.setDOB(DOB);
            UA.setPassword(Password);
            userAccRepository.save(UA);

            return true;
        }
        else{
            return false;
        }
    }
    public boolean deletebyid(Integer Id){
        Optional<UserAcc> op=userAccRepository.findById(Id);
        if(op.isPresent()){
            userAccRepository.deleteById(Id);
            return true;
        }
        else{
           return false;
        }
    }
}
