package app.query.queryBuilder;

import app.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for making query of commands that has no arguments.
 */
public class QueryBuilderForCommandsWithoutArguments extends QueryBuilder {

    /**
     * Makes query for commands without arguments by command name and command type.
     * @param name
     * @param type
     * @param commandList
     * @param arguments
     * @return
     */
    @Override
    public Query buildQuery(String name, String type, List<String> commandList, Map<String, String> arguments) {
        arguments = new HashMap<>();
        return new Query(name, type, arguments );
    }
}