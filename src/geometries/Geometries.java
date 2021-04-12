package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;



public class Geometries implements Intersectable{

    List<Intersectable> _intersectables;

    public void add(Intersectable... geometries){
        for (Intersectable item: geometries) {
            if (item != null)
            _intersectables.add(item);
        }

    }
    public Geometries(){
        _intersectables = new LinkedList<Intersectable>();

    }

    public Geometries(Intersectable... intersectable){
        _intersectables = new LinkedList<Intersectable>();
        add(intersectable);
    }
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result = null;
        for (Intersectable item: _intersectables ) {
            List<Point3D> intersections = item.findIntersections(ray);
            if (intersections != null) {
                if (result == null)
                    result = new LinkedList<Point3D>();
                result.addAll(item.findIntersections(ray));
            }
        }
        return result;
    }
}
