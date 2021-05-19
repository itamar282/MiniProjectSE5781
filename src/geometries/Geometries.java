package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;


/**
 * A class representing a list of geometries
 */
public class Geometries implements Intersectable{

    List<Intersectable> _intersectables;

    /**
     *
     * @param geometries Representing a list of the added geometries to the list
     */
    public void add(Intersectable... geometries){
        for (Intersectable item: geometries) {
            if (item != null)
            _intersectables.add(item);
        }

    }

    /**
     * Empty constructor
     */
    public Geometries(){
        _intersectables = new LinkedList<Intersectable>();
    }

    /**
     *
     * @param intersectable Representing a list of the added geometries to the list
     */
    public Geometries(Intersectable... intersectable){
        _intersectables = new LinkedList<Intersectable>();
        add(intersectable);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result = null;
        for (Intersectable item: _intersectables ) {
            List<GeoPoint> intersections = item.findGeoIntersections(ray);
            if (intersections != null) {
                if (result == null)
                    result = new LinkedList<GeoPoint>();
                result.addAll(item.findGeoIntersections(ray));
            }
        }
        return result;
    }
}
