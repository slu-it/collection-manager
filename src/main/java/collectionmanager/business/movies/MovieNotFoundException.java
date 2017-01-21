package collectionmanager.business.movies;

import collectionmanager.business.exceptions.NotFoundException;
import collectionmanager.business.types.Id;


class MovieNotFoundException extends NotFoundException {

    public MovieNotFoundException(Id id) {
        super(id, "Movie");
    }

}
