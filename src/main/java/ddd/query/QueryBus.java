package ddd.query;

public interface QueryBus {
    QueryResponse dispatch (Query query);
}
