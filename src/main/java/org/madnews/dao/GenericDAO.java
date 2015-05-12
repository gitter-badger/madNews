package org.madnews.dao;

import java.io.Serializable;

public interface GenericDAO <T, PK extends Serializable> {
    void create(T entry);
    T read(PK id);
    void update(T entry);
    void delete(T entry);
}
