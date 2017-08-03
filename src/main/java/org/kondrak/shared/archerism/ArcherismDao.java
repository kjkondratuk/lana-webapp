package org.kondrak.shared.archerism;

import org.kondrak.shared.mappers.ArcherismMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2/24/2017.
 */
@Component
public class ArcherismDao {

    public static final Logger LOG = LoggerFactory.getLogger(ArcherismDao.class);

    @Autowired
    private ArcherismMapper archerismMapper;

    public List<Archerism> getArcherisms() {
        return archerismMapper.getAllArcherisms();
    }
}
