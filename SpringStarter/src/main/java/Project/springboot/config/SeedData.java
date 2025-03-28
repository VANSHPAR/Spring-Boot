package Project.springboot.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import Project.springboot.models.Account;
import Project.springboot.models.Post;
import Project.springboot.services.AccountService;
import Project.springboot.services.PostService;

@Component
public class SeedData implements CommandLineRunner{
     
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        Account account01=new Account();
        Account account02=new Account();

       account01.setEmail("account0101@gmail.com");
       account01.setPassword("accpass01");
       account01.setFirstname("User01");

       account02.setEmail("account0202@gmail.com");
       account02.setPassword("accpass012");
       account02.setFirstname("User02");
       
       accountService.save(account01);
       accountService.save(account02);


        List<Post> posts=postService.getAll();
        if(posts.size()==0){
            Post post01=new Post();
            post01.setTitle("Post 01");
            post01.setBody("post01's Body..........");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02=new Post();
            post02.setTitle("Post 02");
            post02.setBody("post02's Body..........");
            post02.setAccount(account02);
            postService.save(post02);

        }
    }
    
    
}
