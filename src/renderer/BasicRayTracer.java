package renderer;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * A basic class for ray tracer
 */
public class BasicRayTracer extends RayTracerBase {

    private static final double DELTA = 0.1;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     *
     * @param scene Basic scene with objects, light, etc.
     */
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    /**
     *
     * @param light A source of light
     * @param l Vector with the direction of the light
     * @param n A normal vector of the geometry at the point
     * @param geopoint The intersection point of geometry with the light
     * @return
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : - DELTA);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().getkT();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }

    /**
     * @param ray A specific ray
     * @return The color of the closest intersected point with the ray
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? Color.BLACK : calcColor(closestPoint, ray);
    }

    /**
     *
     * @param ray Some ray
     * @return The closest intersection point to the start of the ray
     */
    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> geoPoints = _scene.geometries.findGeoIntersections(ray);
        if (geoPoints == null){
            return null;
        }

        Point3D P0 = ray.getP0();
        double smallestDistance = Double.POSITIVE_INFINITY;
        GeoPoint closestPoint = null;
        for (GeoPoint geoPoint: geoPoints) {
            double newDistance = geoPoint.point.distance(P0);
            if (newDistance < smallestDistance){
                smallestDistance = newDistance;
                closestPoint = geoPoint;
            }
        }
        return closestPoint;
    }

    /**
     *
     * @param intersection The intersection point of geometry with the ray
     * @param ray Some ray
     * @param k A factor of the transparency level
     * @return The color received from the local area
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        Vector v = ray.getDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getnShininess();
        Color color = Color.BLACK;

        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(material.kD, l, n, lightIntensity),
                            calcSpecular(material.kS, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     *
     * @param geopoint The intersection point of geometry with the ray
     * @param ray Some ray
     * @param level The amount of transparent objects possible to see through them
     * @param k factor of the transparency and reflection level
     * @return The color received from the global environment (including transparency and reflectance)
     */
    private Color calcGlobalEffects(GeoPoint geopoint, Ray ray, int level, double k) {
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        Color color = Color.BLACK;
        Material material = geopoint.geometry.getMaterial();
        double kr = material.kR, kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geopoint.point, ray);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
        }
        double kt = material.kT, kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(n, geopoint.point, ray);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            color = color.add(calcColor(refractedPoint, refractedRay, level-1, kkt).scale(kt));
        }
        return color;
    }

    /**
     *
     * @param n The normal vector of the object at the point
     * @param point The intersection point of the ray with the object
     * @param ray Some ray
     * @return The Reflected ray - that has been created from the ray and the object
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray ray) {
        //𝒓 = 𝒗 − 𝟐 ∙ (𝒗 ∙ 𝒏) ∙ n
        Vector v = ray.getDir();
        Vector r_vec = v.subtract(n.scale(2*(v.dotProduct(n))));
        Vector delta = n.scale(DELTA);
        if (n.dotProduct(r_vec) < 0){
            delta = delta.scale(-1);
        }
        Point3D p = point.add(delta);
        Ray r = new Ray(p, r_vec);
        return r;
    }

    /**
     *
     * @param point The intersected point of the ray with the geometry
     * @param ray Some ray
     * @return The refracted ray at the intersection point
     */
    private Ray constructRefractedRay(Vector n, Point3D point, Ray ray) {
        Vector delta = n.scale(DELTA);
        if (n.dotProduct(ray.getDir()) < 0){
            delta = delta.scale(-1);
        }
        Point3D p = point.add(delta);
        Ray r = new Ray(p, ray.getDir());
        return r;
    }

    /**
     *
     * @param kd The factor of the intensity of the diffusive
     * @param l The light vector
     * @param n The normal vector
     * @param lightIntensity The intensity of the light
     * @return The diffusive level that has been created from the light
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double abs_l_n = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd*abs_l_n);
    }

    /**
     *
     * @param ks The factor of the intensity of the specular
     * @param l The light vector
     * @param n The normal vector
     * @param v The direction of the camera
     * @param nShininess The level of shininess of the object
     * @param lightIntensity The intensity of the light
     * @return The specular that has been created from the light and the angle
     */
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
     *
     * @param geopoint The intersected point of the ray with some object
     * @param ray Some ray
     * @return The final (calculated) color of the object
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    /**
     *
     * @param intersection The intersected point of the ray with some object
     * @param ray Some ray
     * @param level The amount of transparent objects possible to see through them
     * @param k The factor of some parameters like transparency and reflectance
     * @return The calculated color of the object
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        if (intersection == null){
            return Color.BLACK;
        }
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }
}
