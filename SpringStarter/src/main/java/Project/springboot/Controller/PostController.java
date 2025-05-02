package Project.springboot.Controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import Project.springboot.models.Post;
import Project.springboot.services.PostService;

@Controller
public class PostController {
    
    @Autowired
    private PostService postService;

    
    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id,Model model,Principal principal){
        Optional<Post> optionalpost = postService.getById(id);
        String authuser="email";

        if(optionalpost.isPresent()){
            Post post=optionalpost.get();
           model.addAttribute("post", post);
           if(principal != null){
            authuser=principal.getName(); //principal.getName return username
           }
           if(authuser.equals(post.getAccount().getEmail())){
            model.addAttribute("isOwner", true);
           }
           else{
            model.addAttribute("isOwner",false);
           }
            return "post_views/post";
        }
        else {
            return "404";
        }
   
    }
    
    @GetMapping("/posts/add")
    public String addPost(Model model){
        return "post_views/post_add";
    }


}
