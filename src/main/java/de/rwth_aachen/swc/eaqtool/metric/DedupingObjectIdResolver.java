package de.rwth_aachen.swc.eaqtool.metric;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;

import java.util.HashMap;

/**
 * This is a workaround for a Jackson shortcoming.
 * An object might want to aggregate one-to-many sub-objects, via an array of Id references.
 * Jackson ObjectIdResolver won't normally allow the same sub-object Id to be referenced from multiple parent object arrays.
 * (in the not uncommon case where the same sub-object is aggregated by different parent objects for re-usability and modularity).
 * Instead of simply referencing the same Id, it tries to create a duplicate with the same Id and then fails because the Id is already in use.
 * This DedupingObjectIdResolver solves the issue.
 * See also unresolved issue: https://github.com/FasterXML/jackson-databind/issues/266
 */
public class DedupingObjectIdResolver extends SimpleObjectIdResolver {

    @Override
    public void bindItem(IdKey id, Object ob) {
        if (_items == null) {
            _items = new HashMap<>();
        }
        _items.put(id, ob);
    }

    @Override
    public ObjectIdResolver newForDeserialization(Object context) {
        return new DedupingObjectIdResolver();
    }

}