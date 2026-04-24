package tech.ninecraft.server.controller;

import tech.ninecraft.data.api.Data;
import tech.ninecraft.server.api.exception.ServiceUnavailableException;
import tech.ninecraft.server.model.CountResponse;
import tech.ninecraft.server.model.EnabledResponse;
import tech.ninecraft.server.model.JoinConfig;
import tech.ninecraft.server.model.WhereParameter;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

/**
 * Webserver auth controller.
 *
 * @since 1.0.0
 */
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class AuthController {
    /** (Optional) Data plugin. */
    @Nullable
    private final Data dataPlugin;
    /** Is web authentication enabled? */
    private final boolean isEnabled;
    /** Plugin logger. */
    @NonNull
    private final Logger log;

    /**
     * Constructor.
     *
     * @param dataPlugin (Optional) Data plugin.
     * @param isEnabled  Is authentication enabled?
     * @param log        Plugin logger.
     */
    @Inject
    public AuthController(
        @Nullable final Data dataPlugin,
        final boolean isEnabled,
        @NonNull final Logger log) {
        this.dataPlugin = dataPlugin;
        this.isEnabled = isEnabled;
        this.log = log;
    }

    /**
     * Get record count matching query.
     *
     * @param model The model/table name that the records will be counted in.
     * @param where Where query clauses.
     * @return The record count.
     * @throws ServiceUnavailableException If web authentication is not enabled.
     */
    @GET
    @NonNull
    @Path("/{model}")
    public CountResponse getCount(
        @NotBlank @PathParam("model") final String model,
        @Nullable @QueryParam("where") final List<WhereParameter> where)
        throws ServiceUnavailableException {
        log.trace("Counting records for {}", model);

        if (!isEnabled) {
            throw new ServiceUnavailableException("Web authentication is not enabled");
        }

        return new CountResponse(0);
    }

    /**
     * Create (insert) data in database.
     *
     * @param data   Row data.
     * @param model  The model/table name.
     * @param select Column names to select/return after create.
     * @throws ServiceUnavailableException If web authentication is not enabled.
     */
    @POST
    @Path("/{model}")
    public void create(
        @NonNull final JsonNode data,
        @NotBlank @PathParam("model") final String model,
        @Nullable @QueryParam("select") final List<String> select) throws ServiceUnavailableException {
        log.trace("Creating record for {}", model);

        if (!isEnabled) {
            throw new ServiceUnavailableException("Web authentication is not enabled");
        }
    }

    /**
     * Delete data from the database.
     *
     * @param model The model/table name.
     * @param where Where query clauses.
     * @throws ServiceUnavailableException If web authentication is not enabled.
     */
    @DELETE
    @Path("/{model}")
    public void delete(
        @NotBlank @PathParam("model") final String model,
        @Nullable @QueryParam("where") final List<WhereParameter> where)
        throws ServiceUnavailableException {
        log.trace("Deleting record for {}", model);

        if (!isEnabled) {
            throw new ServiceUnavailableException("Web authentication is not enabled");
        }
    }

    /**
     * Delete data from the database.
     *
     * @param model The model/table name.
     * @param where Where query clauses.
     * @return Number of records deleted.
     * @throws ServiceUnavailableException If web authentication is not enabled.
     */
    @DELETE
    @NonNull
    @Path("/{model}")
    public CountResponse deleteMany(
        @NotBlank @PathParam("model") final String model,
        @Nullable @QueryParam("where") final List<WhereParameter> where)
        throws ServiceUnavailableException {
        log.trace("Deleting records for {}", model);

        if (!isEnabled) {
            throw new ServiceUnavailableException("Web authentication is not enabled");
        }

        return new CountResponse(0);
    }

    /**
     * Find many records in the database.
     *
     * @param model  The model/table name.
     * @param join   Query join configuration.
     * @param limit  Result limit.
     * @param offset Result offset.
     * @param select Column names to select/return after create.
     * @param where  Where query clauses.
     * @return Single row of data that matched the query if it exists, otherwise {@code null}.
     * @throws ServiceUnavailableException If web authentication is not enabled.
     */
    @GET
    @NonNull
    @Path("/{model}")
    public List<Void> findMany(
        @NotBlank @PathParam("model") final String model,
        @Nullable @QueryParam("join") final Map<String, JoinConfig> join,
        @QueryParam("limit") final int limit,
        @QueryParam("offset") final int offset,
        @Nullable @QueryParam("select") final List<String> select,
        @Nullable @QueryParam("where") final List<WhereParameter> where) throws ServiceUnavailableException {
        log.trace("Reading records for {}", model);

        if (!isEnabled) {
            throw new ServiceUnavailableException("Web authentication is not enabled");
        }

        return List.of();
    }

    /**
     * Find one record in the database.
     *
     * @param model  The model/table name.
     * @param join   Query join configuration.
     * @param select Column names to select/return after create.
     * @param where  Where query clauses.
     * @return Single row of data that matched the query if it exists, otherwise {@code null}.
     * @throws ServiceUnavailableException If web authentication is not enabled.
     */
    @GET
    @Nullable
    @Path("/{model}")
    public Void findOne(
        @NotBlank @PathParam("model") final String model,
        @Nullable @QueryParam("join") final Map<String, JoinConfig> join,
        @Nullable @QueryParam("select") final List<String> select,
        @NonNull @QueryParam("where") final List<WhereParameter> where) throws ServiceUnavailableException {
        log.trace("Reading record for {}", model);

        if (!isEnabled) {
            throw new ServiceUnavailableException("Web authentication is not enabled");
        }

        return null;
    }

    /**
     * Check if authentication is enabled.
     *
     * @return Web authentication enabled response.
     */
    @GET
    @Path("/enabled")
    public EnabledResponse isEnabled() {
        return new EnabledResponse(isEnabled);
    }

    /**
     * Update data in database.
     *
     * @param data  Row data.
     * @param model The model/table name.
     * @param where Where query clauses.
     * @return Updated rows.
     * @throws ServiceUnavailableException If web authentication is not enabled.
     */
    @POST
    @NonNull
    @Path("/{model}")
    public List<Void> update(
        @NonNull final List<JsonNode> data,
        @NotBlank @PathParam("model") final String model,
        @NonNull @QueryParam("where") final List<WhereParameter> where)
        throws ServiceUnavailableException {
        log.trace("Updating record for {}", model);

        if (!isEnabled) {
            throw new ServiceUnavailableException("Web authentication is not enabled");
        }

        return List.of();
    }
}
