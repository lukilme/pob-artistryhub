package com.artistryhub.dao;

import java.util.List;

import com.artistryhub.model.Artist;
import com.db4o.query.Query;

public class DAOArtist extends DAO<Artist> {

    @Override
    public Artist read(Object key) {
        Integer id = (Integer) key;
        Query query = manager.query();
        query.constrain(Artist.class);
        query.descend("id").constrain(id);
        List<Artist> result = query.execute();
        if (result.size() > 0)
            return result.get(0);
        else
            return null;
    }
}
