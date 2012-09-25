package za.co.imqs.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import za.co.imqs.example.Movie;

@Controller
@RequestMapping("/movieDB")
public class MovieController {

	List<Movie> movies = new ArrayList<Movie>();
	public MovieController(){
		// Just add a movie so we can test if we get a list out
		movies.add(new Movie("Alien", "pg16", "Ridley Scott"));
	}

	@RequestMapping(value="/get", method = RequestMethod.GET)
	public @ResponseBody Movie getMovie(@RequestParam String name) {
		Iterator<Movie> it = movies.iterator();
		while (it.hasNext())
		{
			Movie m = it.next();
			System.out.println(name+ " " + m.getName());
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return new Movie("","","");
	}
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.CREATED)
	public void addMovie(@RequestParam String name, @RequestParam String rating, @RequestParam String director) {
		movies.add(new Movie(name,rating,director));
	}
	
	// Return a list of all movies in the database
	@RequestMapping(value="/list", method = RequestMethod.GET) 
	public @ResponseBody List<Movie> listMovies(){
		return movies;
	}	
}