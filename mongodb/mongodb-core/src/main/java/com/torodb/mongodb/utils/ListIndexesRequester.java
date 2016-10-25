
package com.torodb.mongodb.utils;

import com.eightkdata.mongowp.MongoVersion;
import com.eightkdata.mongowp.client.core.MongoConnection;
import com.eightkdata.mongowp.client.core.MongoConnection.RemoteCommandResponse;
import com.eightkdata.mongowp.exceptions.MongoException;
import com.eightkdata.mongowp.mongoserver.api.safe.library.v3m0.commands.admin.ListIndexesCommand;
import com.eightkdata.mongowp.mongoserver.api.safe.library.v3m0.commands.admin.ListIndexesCommand.ListIndexesResult;
import com.eightkdata.mongowp.mongoserver.api.safe.library.v3m0.pojos.CursorResult;
import com.eightkdata.mongowp.mongoserver.api.safe.library.v3m0.pojos.index.IndexOptions;

/**
 * Utility class to get the indexes metadata in a version independient way.
 * <p/>
 * The command {@link ListIndexesCommand listIndexes} is only available
 * since {@linkplain MongoVersion#V3_0 MongoDB 3.0}. In previous versions, a
 * query to an specific metacollection must be done.
 * <p/>
 * This class is used request for collections metadata in a version independient
 * way.
 */
public class ListIndexesRequester {

    private ListIndexesRequester() {}

    public static CursorResult<IndexOptions> getListCollections(
            MongoConnection connection,
            String database,
            String collection
    ) throws MongoException {
        boolean commandSupported = connection.getClientOwner()
                .getMongoVersion().compareTo(MongoVersion.V3_0) >= 0;
        if (commandSupported) {
            return getFromCommand(connection, database, collection);
        }
        else {
            return getFromQuery(connection, database, collection);
        }
    }

    private static CursorResult<IndexOptions> getFromCommand(
            MongoConnection connection,
            String database,
            String collection) throws MongoException {
        RemoteCommandResponse<ListIndexesResult> reply = connection.execute(
                ListIndexesCommand.INSTANCE,
                database,
                true,
                new ListIndexesCommand.ListIndexesArgument(
                        collection
                )
        );
        if (!reply.isOk()) {
            throw reply.asMongoException();
        }
        return reply.getCommandReply().get().getCursor();
    }

    private static CursorResult<IndexOptions> getFromQuery(
            MongoConnection connection,
            String database,
            String collection) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
