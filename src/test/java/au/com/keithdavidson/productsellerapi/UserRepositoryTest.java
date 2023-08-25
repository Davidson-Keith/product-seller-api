package au.com.keithdavidson.productsellerapi;

import au.com.keithdavidson.productsellerapi.model.Role;
import au.com.keithdavidson.productsellerapi.model.User;
import au.com.keithdavidson.productsellerapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    // With ID @GeneratedValue strategy =
    // GenerationType.AUTO - Hibernate: select next_val as id_val from users_seq for update
    //                     - Hibernate: update users_seq set next_val= ? where next_val=?
    //                     - db_product.users.id = 1,2...; db_product.users_seq.next_val = 51,101...
    // GenerationType.IDENTITY - Just works, no id generation table created???
    //                         - db_product.users.id = 1,2...
    // GenerationType.SEQUENCE - Hibernate: select next_val as id_val from users_seq for update
    //                         - Hibernate: update users_seq set next_val= ? where next_val=?
    //                         - db_product.users.id = 1,2...; db_product.users_seq.next_val = 51,101...
    // GenerationType.TABLE - Hibernate: select tbl.next_val from hibernate_sequences tbl where tbl.sequence_name=? for update
    //                      - Hibernate: update hibernate_sequences set next_val=?  where next_val=? and sequence_name=?
    //                      - db_product.users.id = 1,2...; db_product.hibernate_sequences.default = 50,100...
    @Test
    public void generatedValueStrategyTest(){
        User user = new User();
        user.setName("Test Name");
        user.setPassword("pw");
        user.setRole(Role.USER);
        user.setUserName("Test Username2");
        user.setCreateTime(LocalDateTime.now());
        userRepository.save(user);
    }
}
