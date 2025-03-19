package Project.springboot.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import Project.springboot.models.Post;
import Project.springboot.services.PostService;

@Component
public class SeedData implements CommandLineRunner{
     
    @Autowired
    private PostService postService;
    @Override
    public void run(String... args) throws Exception {
         
        // throw new UnsupportedOperationException("Unimplemented method 'run'");
        List<Post> posts=postService.getAll();
        if(posts.size()==0){
            Post post01=new Post();
            post01.setTitle("Post 01");
            post01.setBody("post01's Body..........");
            postService.save(post01);

            Post post02=new Post();
            post02.setTitle("Post 02");
            post02.setBody("post02's Body..........");
            postService.save(post02);

        }
    }
    
    
}
