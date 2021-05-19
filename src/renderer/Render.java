package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

/**
 *  A class to render the scene
 */
public class Render {
    ImageWriter _imageWriter;
    Scene _scene;
    Camera _camera;
    BasicRayTracer _basicRayTracer;

    /**
     *
     * @param imageWriter A specific imageWriter
     * @return This render object for multiple Implementation
     */
    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    /**
     *
     * @param scene A specific scene
     * @return This render object for multiple Implementation
     */
    public Render setScene(Scene scene) {
        _scene = scene;
        return this;
    }

    /**
     *
     * @param camera A specific camera
     * @return This render object for multiple Implementation
     */
    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    /**
     *
     * @param basicRayTracer A specific rayTracerBase
     * @return This render object for multiple Implementation
     */
    public Render setRayTracer(BasicRayTracer basicRayTracer) {
        _basicRayTracer = basicRayTracer;
        return this;
    }

    /**
     * A function to render the image, with validation checking
     */
    public void renderImage() {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", _imageWriter.getClass().getName(), "");
            }
            if (_scene == null) {
                throw new MissingResourceException("missing resource", _scene.getClass().getName(), "");
            }
            if (_camera == null) {
                throw new MissingResourceException("missing resource", _camera.getClass().getName(), "");
            }
            if (_basicRayTracer == null) {
                throw new MissingResourceException("missing resource", _basicRayTracer.getClass().getName(), "");
            }
            //rendering the image
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    // For each pixel - build a ray
                    Ray ray = _camera.constructRayThroughPixel(nX, nY, j, i);
                    // For each ray - find the color of the closest intersected point of the ray and the objects
                    if (j == 148 && i == 148){
                        i += 1;
                        i -= 1;
                    }
                    Color pixelColor = _basicRayTracer.traceRay(ray);
                    // Write this pixel with the found color
                    if (j == 90 && i == 90){
                        i += 1;
                        i -= 1;
                    }
                    _imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    public void printGrid(int interval, Color color) {
        if (_imageWriter == null) {
            throw new MissingResourceException("missing resource", _imageWriter.getClass().getName(), "");
        }

        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    public void writeToImage() {
        if (_imageWriter == null) {
            throw new MissingResourceException("missing resource", _imageWriter.getClass().getName(), "");
        }

        _imageWriter.writeToImage();
    }
}
