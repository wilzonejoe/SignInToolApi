/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClient.Exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author wilzone
 */
@Provider
public class BeanValConstrainViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
    @Override
    public Response toResponse(ConstraintViolationException e) {
        ConstraintViolation cv = (ConstraintViolation) e.getConstraintViolations().toArray()[0];
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorMessage(cv.getMessage()))
                .build();
    }
}