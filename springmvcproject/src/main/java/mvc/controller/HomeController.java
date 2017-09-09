package mvc.controller;

import mvc.dao.UserDaoImpl;
import mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by axmedbek on 9/3/17.
 */


@Controller
public class HomeController {

    @Autowired
    private UserDaoImpl userDaoImpl;


    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        model.addAttribute("userList", userDaoImpl.userList());
        return "home";
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public String helloPage(@PathVariable String name, Model model) {
        User user = userDaoImpl.findUserByName(name);
        model.addAttribute("user", user);
        return "hello";

    }

    @RequestMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userForm", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerProcessing(@ModelAttribute("userForm") @Valid User user, BindingResult result, Model model, HttpServletRequest request) throws IOException {


        if (result.hasErrors()) {
            return "register";
        } else {
            MultipartFile image = user.getFile();
            int random = (1000 + (int) (Math.random() * 9000));
            String imagePath = user.getName() + random;
            String rootDirectory = request.getSession().getServletContext().getRealPath("/images/");
            Path path = Paths.get(rootDirectory + imagePath + ".png");
            image.transferTo(new File(path.toString()));
            user.setImagePath(imagePath);
            userDaoImpl.saveUser(user);
            return "redirect:/";
        }
    }


    @RequestMapping("/user/{name}/delete")
    public String deleteUser(@PathVariable("name") String name, HttpServletRequest request) {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/images/");
        User user = userDaoImpl.findUserByName(name);
        Path path = Paths.get(rootDirectory + user.getImagePath() + ".png");
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("File doesn't deleted ");
            }

        }
        userDaoImpl.deleteUser(name);
        return "redirect:/";
    }


    @RequestMapping("/user/{id}/edit")
    public String editUser(@PathVariable("id") int id, Model model) {
        User user = userDaoImpl.findUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.POST)
    public String editUserProcessing(@ModelAttribute("user") User user, HttpServletRequest request) {
        MultipartFile image = user.getFile();
        String rootDirectory = request.getSession().getServletContext().getRealPath("/images/");
        int random = (1000 + (int) (Math.random() * 9000));
        String imagePath = user.getName() + random;

        Path path = Paths.get(rootDirectory + imagePath + ".png");
        if (image != null && !image.isEmpty()) {
            try {
                image.transferTo(new File(path.toString()));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("File doesn't updated");
            }
        }
        user.setImagePath(imagePath);
        userDaoImpl.updateUser(user);
        return "redirect:/";
    }


}
