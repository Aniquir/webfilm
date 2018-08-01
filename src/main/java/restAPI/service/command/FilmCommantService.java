package restAPI.service.command;

import org.springframework.stereotype.Service;
import restAPI.dao.Film;

@Service
public class FilmCommantService {
    public void create(Film film) {
        //        ...a lot of implementation...
    }

    public void update(Film film, Long id) {
        //        ...a lot of implementation...
    }

    public void delete(Long id) {
        //        ...a lot of implementation...
    }

    public void createReviewOfFilmById(Long id) {
        //        ...a lot of implementation...
    }

    public void deleteReview(Long id) {
        //        ...a lot of implementation...
    }

    public void addRatingOfFilmById(Long id) {
        //        ...a lot of implementation...
    }

    public void likeOfFilmById(Long id) {
        //        ...a lot of implementation...
    }

    public void likeReviewFilmyById(Long id) {
        //        ...a lot of implementation...
    }
}
