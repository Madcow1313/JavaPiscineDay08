package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hikari-----------------------------------------------");
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);

        System.out.println(usersRepository.findAll());
        User addedUser = new User(1L, "added@gmail.com");
        usersRepository.save(addedUser);
        System.out.println("After save " + usersRepository.findAll());
        addedUser.setEmail("addedUpdated@gmail.com");
        usersRepository.update(addedUser);
        System.out.println("After update " + usersRepository.findAll());
        usersRepository.delete(addedUser.getIdentifier());
        System.out.println("After delete " + usersRepository.findAll());
        System.out.println("Find by email: " + usersRepository.findByEmail("second@gmail.com"));

        System.out.println("JdbcTemplate-----------------------------------------------");
        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll());
        System.out.println(usersRepository.findById(2L));
        User thirdUser = new User(1L, "third@gmail.com");
        usersRepository.save(thirdUser);
        System.out.println("After save " + usersRepository.findAll());
        thirdUser.setEmail("thirdUpdated@gmail.com");
        usersRepository.update(thirdUser);
        System.out.println("After update " + usersRepository.findAll());
        usersRepository.delete(thirdUser.getIdentifier());
        System.out.println("After delete " + usersRepository.findAll());
        System.out.println("Find by email: " + usersRepository.findByEmail("second@gmail.com"));
        try {
            System.out.println("No object" + usersRepository.findById(100L));
        }
        catch (Exception e){}



    }
}
