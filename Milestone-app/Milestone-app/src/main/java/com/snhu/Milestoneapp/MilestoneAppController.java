package com.snhu.Milestoneapp;

import javax.xml.bind.*;

import java.io.File;
import java.util.ArrayList;

import com.snhu.Milestoneapp.Models.*;
import com.snhu.Milestoneapp.Services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * Web app controller
 * 
 * A user can search for a flight, sign up, sign in, sign out,
 * and if signed in bookflights. When a flight is booked 
 * and a user is signed in they can view a list of 
 * booked flights.
 * 
 * Flight search uses a flight api using an api key.
 * Sign up and sign in use an XML file to store and get user account.
 * Booking a flight uses the Booking SQL database, to store and get 
 * flight information thats been saved.
 * 
 * Possible updates: remove the XML file function for integrated
 * Spring security, allows better protection, more functionality, and seemless
 * integration with the code.
 */
@Controller
public class MilestoneAppController {
    
    private RestTemplate restTemplate;  //Helps perform HTTP requests

    //xml file with username and password
    private static final String xmlFilePath = "C:\\Users\\Dylan\\Documents\\file.xml";

    //bussiness layer for booking
    @Autowired
    private BookingService bookingService;

    //inject RestTemplate at runtime
    @Autowired
    public MilestoneAppController(RestTemplateBuilder builder){
        this.restTemplate = builder.build();
    }

    //maps to root page index
    @RequestMapping(value ="/", method = {RequestMethod.GET, RequestMethod.POST})
    public String onLoadSeachForm(Model model) {
        model.addAttribute("search", new SearchModel());
        model.addAttribute("listBookings", bookingService.findAllBookings());
        User user = new User();
        model.addAttribute("user", user);

        return "index.html";
    }
   
    //maps as main page (made to mirror index.html) if logged in [used to fix issues with data resetting]
    @GetMapping("/home")
    public String homeForm(@ModelAttribute User user, Model model, @RequestParam(required = false, name = "username") String username) {
        model.addAttribute("search", new SearchModel());
        model.addAttribute("listBookings", bookingService.findAllBookings());

        //keep username in view
        user.setUsername(username);
        model.addAttribute("user", user);

        System.out.println("username is " + user.getUsername());

        return "home.html";
    }

    //maps to results.html if called
    //Purpose: Calls flight api and return flight information depending on user input
    @PostMapping("/performsearch")
    public String onSearch(@ModelAttribute User user, @ModelAttribute SearchModel search, Model model, @RequestParam(required = false, name = "username") String username) {

        //if empty string set username to null for form verfication on results.html
        if (username.isEmpty()) {
            username = null;
        }

        //set username from model equal to username from request param username
        user.setUsername(username);
        
        //add model to view
        model.addAttribute("user", user);

        HttpHeaders headers = new HttpHeaders();                    //custom Http header

        headers.add("apiKey", "q_wY2C7L3jHNa5GyyaPOdryDXk0Q9suE");  //Add API key to header  

        HttpEntity entity = new HttpEntity<>(headers);              //Create Http Entity

        //Url for GET kiwi.com API using custom Query [currently uses fly_from]
        String url = "https://tequila-api.kiwi.com/v2/search?fly_from=" + search.getDeparture(); 

        //Use rest template while using exchange to insert customer header into request
        ResponseEntity<SearchResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            SearchResponse.class
        );
        
        //use getbody() to unwrap response
        //get array list flightData from data model, pass it back to HTML
        //Then use themeleaf to dynamically generate the results page
        model.addAttribute("results", response.getBody().getData());

        return "results.html";
    }

    //Purpose: save flight information into database by calling bussiness layer
    @PostMapping("/saveBooking")
    public String saveBooking(@ModelAttribute User user, @ModelAttribute("b") book b) {
        //save flight into database
        bookingService.saveBooking(b);
        
        return "/booking";
    }

    //maps to booking page
    //Purpose: displays all flights a user booked
    @GetMapping("/booking")
    public String showBooking(@ModelAttribute User user, Model model, @RequestParam(required = false, name = "username") String username) {
        //get flight information from bussiness layer
        model.addAttribute("listBookings", bookingService.findAllBookings());

        //continue passing username to next view
        user.setUsername(username);
        model.addAttribute("user", user);

        return "booking.html";
    }

    //maps create account page
    @GetMapping("/create") 
    public String onLoadCreate() {

        return "create.html";
    }

    //create account action
    //gets username and password as input
    //Purpose: create a user account with a username and password
    //checks if account file exists, create it if it doesnt with username and password input by user
    //if file exists load file check if username isnt in use, if its not create account with username and password
    //if failed to create account return to account creation screen
    //if success, go to success page
    @PostMapping("/createaccount")
    public String create(@ModelAttribute User user, Model model, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {

        //if either the username or password field was empty
        if(username.equals("") || password.equals("")){
            //return back to account creation screen
            return "create.html";
        }

        //test for file errors
        try {

            //get file path
            File file = new File(xmlFilePath);
            
            //if file at path doesnt exist
            if (!file.exists()) {

                //create new users xml class
                Users users = new Users();

                //create new user xml class
                // User user = new User();
    
                //create array list of user
                users.setUser(new ArrayList<User>());

                //set username for new user
                user.setUsername(username);

                //set password for new user
                user.setPassword(password);

                //add new user to users array list
                users.getUser().add(user);

                //Initialize 
                JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                //make nicer output
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                //Marshal users into file at file path
                jaxbMarshaller.marshal(users, file);

                //displayed marshalled ouput for debugging
                jaxbMarshaller.marshal(users, System.out);
            }
            //if file exists
            else {

                //get file at file path
                File xmlFile = new File(xmlFilePath);

                //Initialize
                JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                //Unmarshall file at path as users 
                Users users = (Users) jaxbUnmarshaller.unmarshal(xmlFile);

                System.out.println("user: " + users.getUser().toString());

                //if username doesnt exist already in users
                if (!users.getUser().stream().anyMatch(o -> o.getUsername().equals(username)))  {
        
                    //set username as input username
                    user.setUsername(username);

                    //set password as input password
                    user.setPassword(password);
            
                    //add user to users array list
                    users.getUser().add(user);

                    System.out.println("user: " + users.getUser().toString());
    
                    //Initialize marshaller
                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
    
                    //make nicer output
                    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    
                    //Marshal users into file at file path
                    jaxbMarshaller.marshal(users, file);

                    //displayed marshalled ouput for debugging
                    jaxbMarshaller.marshal(users, System.out);
                }
                //if username exists
                else {
                    //return to account creation screen
                    return "create.html";
                }
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //if completed without error go to success screen
        return "redirect:/home";
    }

    //map login screen
    @GetMapping("/login")
    public String onLoadLogin() {

        return "login.html";
    }

    //login action
    //gets username and password as input
    //Purpose: attempt to login into account using username and password input by user
    //try to load account file, check every user in file for matching username and password
    //if success return success page
    //if failure return to login page 
    @PostMapping("/attemptlogin")
    public ModelAndView login(@ModelAttribute User user, Model model, @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {

        //test for file errors
        try {
            //make file at file path
            File xmlFile = new File(xmlFilePath);

            //Initialize
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            //unmarshal file as users
            Users users = (Users) jaxbUnmarshaller.unmarshal(xmlFile);

            //used to check if password and user match
            boolean valid = false;

            //for every user in users
            for(int i = 0; i < users.getUser().size(); i++){

                //check if username and password match input username and password return
                valid = users.getUser().get(i).match(password, username);

                //if username and password match break loop
                if (valid == true){
                    break;
                }
            }

            //if username and password matched
            if (valid == true)  {
    
                //create new user model
                // User user = new User();
                user.setPassword(password);
                user.setUsername(username);

                users.getUser().add(user);

                model.addAttribute("user", user);
                model.addAttribute("search", new SearchModel());

                //go to success page
                return new ModelAndView("redirect:/home", "username", username);
            } 
                   
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        //if failure return to login page
        // return "login.html";
        return new ModelAndView("login", "user", new User());
    }
    
    //main page resets the model
    //so redierection to root will clear model
    //in the future can be used to sign out of a user account
    @GetMapping("/logout")
    public String logout(Model model){

        return "redirect:/";
    }
}
