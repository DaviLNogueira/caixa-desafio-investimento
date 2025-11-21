package org.acme.exceptions.providers;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;
import org.acme.exceptions.Problem;

@Provider
public class GlobalExceptionProvider implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {

        if (e instanceof NotFoundException) {
            return build(404, "Recurso não encontrado", e.getMessage(), e, false);
        }

        if (e instanceof NotAuthorizedException) {
            return build(401, "Não autorizado", e.getMessage(), e, false);
        }

        if (e instanceof ForbiddenException) {
            return build(403, "Acesso negado", e.getMessage(), e, false);
        }

        if (e instanceof WebApplicationException wae) {
            int status = wae.getResponse().getStatus();
            return build(status, "Erro de aplicação", e.getMessage(), e, status >= 500);
        }

        // Fallback: erro inesperado (500)
        return build(500, "Erro interno do servidor", e.getMessage(), e, true);
    }

    private Response build(int status, String title, String detail, Throwable e, boolean includeDebug) {

        Problem p = new Problem(status, title, detail);

        // Se for erro 500, inclui informações adicionais úteis de debug
        if (includeDebug) {
            StackTraceElement element = e.getStackTrace()[0];
            p.errorClass = e.getClass().getName();
            p.errorLine = element.getLineNumber();
            p.errorFile = element.getFileName();
        }

        return Response.status(status)
                .entity(p)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
