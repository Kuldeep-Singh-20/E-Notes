package com.enote.controller;

import com.enote.entity.Notes;
import com.enote.entity.User;
import com.enote.repository.UserRepository;
import com.enote.service.NotesService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotesService notesService;

    @ModelAttribute
    public User getUser(Principal p , Model m){

     String email = p.getName();
     User user = userRepository.findByEmail(email);
     m.addAttribute("user", user);

     return user;
    }

    @GetMapping("/addNote")
    public String add_note(){
        return "add_notes";
    }


    @GetMapping("/viewNote")
    public String view_note(Model m , Principal p ,@RequestParam(defaultValue = "0") Integer pageNo){

       User user = getUser(p, m);
       Page<Notes> notes = notesService.getNotesByUser(user,pageNo);

        m.addAttribute("currentPage",pageNo);
        m.addAttribute("totalElement",notes.getTotalElements());
        m.addAttribute("totalPages",notes.getTotalPages());
        m.addAttribute("notesList", notes.getContent());
        return "view_notes";
    }


    @GetMapping("/editNote/{id}")
    public String edit_note(@PathVariable int id , Model m){
       Notes notes = notesService.getNotesById(id);
       m.addAttribute("n", notes);
        return "edit_notes";
    }


    @PostMapping("/saveNotes")
    public String saveNotes(@ModelAttribute Notes notes , HttpSession session,
                            Principal p , Model m){

        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p,m));

      Notes saveNotes = notesService.saveNotes(notes);

      if(saveNotes!=null){
          session.setAttribute("msg","Notes Save Successfully");
      }
      else{
          session.setAttribute("msg","Notes Save Failed");
      }

      return "redirect:/user/addNote";
    }


    @PostMapping("/updatesNotes")
    public String updatesNotes(@ModelAttribute Notes notes , HttpSession session,
                            Principal p , Model m){

        notes.setDate(LocalDate.now());
        notes.setUser(getUser(p,m));

        Notes saveNotes = notesService.saveNotes(notes);

        if(saveNotes!=null){
            session.setAttribute("msg","Notes Updates Successfully");
        }
        else{
            session.setAttribute("msg","Notes Updates Failed");
        }

        return "redirect:/user/viewNote";
    }


    @GetMapping("/deleteNote/{id}")
    public String deleteNote(@PathVariable int id , HttpSession session){
        boolean f = notesService.deleteNotes(id);

        if(f){
            session.setAttribute("msg","Notes Deleted Successfully");
        }
        else{
            session.setAttribute("msg","Notes not Deleted Failed");
        }

        return "redirect:/user/viewNote";
    }
}
