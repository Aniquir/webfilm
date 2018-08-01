package restAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restAPI.dao.Film;
import restAPI.dao.Review;
import restAPI.service.command.FilmCommantService;
import restAPI.service.exception.FilmNotFoundException;
import restAPI.service.query.FilmQueryService;


//third(sql) on paper
@RestController
@RequestMapping(path = "/film")
public class FilmController {

    private final FilmCommantService filmCommandService;
    private final FilmQueryService filmQueryService;

    @Autowired
    public FilmController(FilmCommantService filmCommandService, FilmQueryService filmQueryService) {
        this.filmCommandService = filmCommandService;
        this.filmQueryService = filmQueryService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Film getFilmById(@PathVariable("id") Long id) {

        return filmQueryService.findByID(id);
    }

    @RequestMapping(value = "/getAllFilms", method = RequestMethod.GET)
    public Iterable<Film> getAllFilms() {

        return filmQueryService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Film> addFilm(@RequestBody Film film) {

        filmCommandService.create(film);

        return ResponseEntity <>(film, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateFilm(@PathVariable("id") Long id,
                                           @RequestBody Film film) {
        try {
            filmCommandService.update(film, id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFilm(@PathVariable("id") Long id) {

        try {
            filmCommandService.delete(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getReviewFIlmById/{id}", method = RequestMethod.GET)
    public Iterable<Film> getReviewFIlmById(@PathVariable("id") Long id) {

        return filmQueryService.findAllRevievwFIlmById(id);
    }

    @RequestMapping(value = "/getReviewFIlmById/{id}", method = RequestMethod.POST)
    public ResponseEntity<Review> addReviewFilmById(@PathVariable("id") Long id, Review review) {

        filmCommandService.createReviewOfFilmById(id);

        return ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteReviewOfFilmById/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteFilm(@PathVariable("id") Long id) {

        try {
            filmCommandService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.GONE);
        } catch (FilmNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/getReviewFIlmById/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> addRatingFilmById(@PathVariable("id") Long id) {

        filmCommandService.addRatingOfFilmById(id);

        return ResponseEntity <>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/likeFilmById/{id}", method = RequestMethod.PUT)
            public Boolean addLikeFilmById(@PathVariable("id") Long id){

            boolean isLike = false;
            filmCommandService.likeOfFilmById(id);

            return isLike;
            }

            @RequestMapping(value = "/likeFilmById/{id}", method = RequestMethod.PUT)
                    public Boolean addReviewFilmyById(@PathVariable("id") Long id){

                    boolean isLike = false;
                    filmCommandService.likeReviewFilmyById(id);

                    return isLike;
                    }
}
