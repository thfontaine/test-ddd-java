package com.example.demo.queries;

import ddd.query.Query;
import lombok.Getter;

@Getter
public class GetOrdersQuery implements Query {
    public Class<GetOrdersQueryResponse> hasResponse() { return GetOrdersQueryResponse.class; }
}
