package com.acorsetti.core.api;

public interface RemoteDataRetriever<E,R,D> {
    APIResponse<E> retrieve(String endPoint, Class<R> responseClass, Class<E> entityClass);
}
