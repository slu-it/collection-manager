package collectionmanager.business.exceptions;

import collectionmanager.business.types.Id;


public class NotFoundException extends RuntimeException {

    protected NotFoundException(Id id, String subject) {
        super(subject + " not found for ID: " + id);
    }

}
