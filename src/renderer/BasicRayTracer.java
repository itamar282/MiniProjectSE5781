package renderer;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Material;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A basic class for ray tracer
 */
public class BasicRayTracer extends RayTracerBase {

    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * @param ray A specific ray
     * @return The color of the closest intersected point with the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return _scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint, ray);
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getkD(), ks = material.getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double abs_l_n = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd*abs_l_n);
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector minusV = v.scale(-1);
        Vector R = l.subtract(n.scale(2*l.dotProduct(n)));
        double minusV_R = minusV.dotProduct(R);
        double max = minusV_R;
        if (minusV_R < 0){
            max = 0;
        }
        double max_Exp_Shininess = Math.pow(max, nShininess);
        return lightIntensity.scale(max_Exp_Shininess*ks);
    }

    /**
     * @param intersection A specific point
     * @return The color of the point
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return _scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission())
                .add(calcLocalEffects(intersection, ray));
    }

}
