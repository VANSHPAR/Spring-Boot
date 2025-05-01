package Project.springboot.Controller;

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

    
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id,Model model){
        Optional<Post> optionalpost = postService.getById(id);

        if(optionalpost.isPresent()){
            Post post=optionalpost.get();
           model.addAttribute("post", post);
            return "post_views/post";
        }
        else {
            return "404";
        }
   
    }




}
