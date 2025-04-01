package Project.springboot.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import Project.springboot.models.Account;
import Project.springboot.models.Authority;
import Project.springboot.models.Post;
import Project.springboot.services.AccountService;
import Project.springboot.services.AuthorityService;
import Project.springboot.services.PostService;
import Project.springboot.util.constants.Privillages;
import Project.springboot.util.constants.Roles;

@Component
public class SeedData implements CommandLineRunner{
     
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private AuthorityService authorityService;


    @Override
    public void run(String... args) throws Exception {

        for(Privillages auth:Privillages.values()){
            Authority authority=new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getprivillage());
            authorityService.save(authority);
        }
        Account account01=new Account();
        Account account02=new Account();
        Account account03=new Account();
        Account account04=new Account();

       account01.setEmail("user0101@gmail.com");
       account01.setPassword("userpass01");
       account01.setFirstname("User01");
       account01.setLastname("user01ln");

       account02.setEmail("admin0202@gmail.com");
       account02.setPassword("admpass02");
       account02.setFirstname("Admin02");
       account02.setLastname("admin02ln");
       account02.setRole(Roles.ADMIN.getRole());

       account03.setEmail("editor0303@gmail.com");
       account03.setPassword("edipass03");
       account03.setFirstname("Editor03");
       account03.setLastname("editor03ln");
       account03.setRole(Roles.EDITOR.getRole());

       account04.setEmail("supereditor0404@gmail.com");
       account04.setPassword("sedipass04");
       account04.setFirstname("SuperEditor04");
       account04.setLastname("seditor04ln");
       account04.setRole(Roles.EDITOR.getRole());

       Set<Authority> authorities=new HashSet<>();
       authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);;
       authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
       account04.setAuthorities(authorities);
       
       accountService.save(account01);
       accountService.save(account02);
       accountService.save(account03);
       accountService.save(account04);


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
