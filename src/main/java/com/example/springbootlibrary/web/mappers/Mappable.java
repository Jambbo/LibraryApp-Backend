package com.example.springbootlibrary.web.mappers;

import java.util.List;

public interface Mappable<E,D> {

    E toEntity(D d);
    D toDto(E e);
    List<E> toEntity(List<D> ds);
    List<D> toDto(List<E> es);
}
